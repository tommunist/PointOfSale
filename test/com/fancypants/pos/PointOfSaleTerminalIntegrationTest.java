package com.fancypants.pos;

import com.fancypants.pos.calculator.BasketTotalCalculator;
import com.fancypants.pos.calculator.ProductTotalCalculator;
import com.fancypants.pos.domain.Basket;
import com.fancypants.pos.exception.DiscountNotFoundException;
import com.fancypants.pos.exception.PriceNotFoundException;
import com.fancypants.pos.exception.ProductNotRecognisedException;
import com.fancypants.pos.repository.DiscountRepository;
import com.fancypants.pos.repository.UnitPriceRepository;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PointOfSaleTerminalIntegrationTest {

    private PointOfSaleTerminal terminal;

    @Before
    public void setUp() {
        DiscountRepository discountRepository = new DiscountRepository(createDiscountStructure());
        UnitPriceRepository unitPriceRepository = new UnitPriceRepository(createPricingStructure());

        ProductTotalCalculator productTotalCalculator = new ProductTotalCalculator(discountRepository, unitPriceRepository);
        BasketTotalCalculator basketTotalCalculator = new BasketTotalCalculator(productTotalCalculator);

        terminal = new PointOfSaleTerminal(createProductCodeChecker(), new Basket(), basketTotalCalculator);
    }

    @Test
    public void shouldProvideTotalForBasketWithOneOfEachItem() throws ProductNotRecognisedException, PriceNotFoundException, DiscountNotFoundException {
        terminal.scan("A");
        terminal.scan("B");
        terminal.scan("C");
        terminal.scan("D");

        assertThat(terminal.getTotal(), is(new BigDecimal("15.40")));

    }

    @Test
    public void shouldProvideTotalForBasketWithFiveWithOneOfEachItem() throws ProductNotRecognisedException, PriceNotFoundException, DiscountNotFoundException {
        terminal.scan("C");
        terminal.scan("C");
        terminal.scan("C");
        terminal.scan("C");
        terminal.scan("C");
        terminal.scan("C");
        terminal.scan("C");

        assertThat(terminal.getTotal(), is(new BigDecimal("7.25")));

    }

    @Test
    public void shouldProvideTotalForBasketWithVarietyOfDifferentItems() throws ProductNotRecognisedException, PriceNotFoundException, DiscountNotFoundException {
        terminal.scan("A");
        terminal.scan("B");
        terminal.scan("C");
        terminal.scan("D");
        terminal.scan("A");
        terminal.scan("B");
        terminal.scan("A");
        terminal.scan("A");

        assertThat(terminal.getTotal(), is(new BigDecimal("32.40")));

    }

    private ProductCodeChecker createProductCodeChecker() {
        ProductCodeChecker checker = new ProductCodeChecker();
        checker.add("A");
        checker.add("B");
        checker.add("C");
        checker.add("D");
        return checker;
    }

    private Map<String, QuantityDiscountRule> createDiscountStructure() {
        Map<String, QuantityDiscountRule> productCodeToDiscountMap = new HashMap<String, QuantityDiscountRule>();
        productCodeToDiscountMap.put("A", new QuantityDiscountRule(4, new BigDecimal("7.00")));
        productCodeToDiscountMap.put("C", new QuantityDiscountRule(6, new BigDecimal("6.00")));
        return productCodeToDiscountMap;
    }

    private Map<String, BigDecimal> createPricingStructure() {
        Map<String, BigDecimal> productCodeToPriceMap = new HashMap<String, BigDecimal>();
        productCodeToPriceMap.put("A", new BigDecimal("2.00"));
        productCodeToPriceMap.put("B", new BigDecimal("12.00"));
        productCodeToPriceMap.put("C", new BigDecimal("1.25"));
        productCodeToPriceMap.put("D", new BigDecimal("0.15"));
        return productCodeToPriceMap;
    }

}
