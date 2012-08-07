package com.fancypants.pos;

import com.fancypants.pos.calculator.BasketTotalCalculator;
import com.fancypants.pos.calculator.ProductTotalCalculator;
import com.fancypants.pos.domain.Basket;
import com.fancypants.pos.domain.Pricing;
import com.fancypants.pos.exception.ProductCodeNotRecognisedException;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class PointOfSaleTerminalIntegrationTest {

    private PointOfSaleTerminal terminal;

    @Before
    public void setUp() {
        ProductTotalCalculator productTotalCalculator = new ProductTotalCalculator();
        BasketTotalCalculator basketTotalCalculator = new BasketTotalCalculator(productTotalCalculator);
        Scanner scanner = new Scanner(createPricingStructure());

        terminal = new PointOfSaleTerminal(scanner, new Basket(), basketTotalCalculator);
    }

    @Test
    ////From Specification: ABCD; Verify the total price is $15.40
    public void shouldProvideTotalForBasketWithOneOfEachItem() throws ProductCodeNotRecognisedException {
        terminal.scan("A");
        terminal.scan("B");
        terminal.scan("C");
        terminal.scan("D");

        assertThat(terminal.getTotal(), is(new BigDecimal("15.40")));
    }

    @Test
    //From Specification: CCCCCCC; Verify the total price is $7.25
    public void shouldProvideTotalForBasketWithSevenOfItemC() throws ProductCodeNotRecognisedException {
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
    //From Specification: ABCDABAA; Verify the total price is $32.40.
    public void shouldProvideTotalForBasketWithVarietyOfDifferentItems() throws ProductCodeNotRecognisedException {
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

    @Test
    public void shouldProvideTotalForBasketWithNoItems() throws ProductCodeNotRecognisedException {
        assertThat(terminal.getTotal(), is(new BigDecimal("0.00")));
    }

    @Test
    public void shouldProvideTotalForBasketWithLargeQuantitiesOfDifferentItems() throws ProductCodeNotRecognisedException {
        scanNumberOfItemsWithProductCode("A", 250);
        scanNumberOfItemsWithProductCode("B", 250);
        scanNumberOfItemsWithProductCode("A", 220);
        scanNumberOfItemsWithProductCode("D", 7600);
        scanNumberOfItemsWithProductCode("C", 500);

        assertThat(terminal.getTotal(), is(new BigDecimal("5463.50")));
    }

    @Test
    public void shouldIndicateErrorAndNotProceedIfUnknownProductCodeIsScanned() throws ProductCodeNotRecognisedException {
        try {
            terminal.scan("testy");
            fail("expected exception but none thown");
        } catch (ProductCodeNotRecognisedException e) {
            assertThat(e.getMessage(), is("Error: Could not find price for product ['testy']"));
        }
    }

    private Map<String, Pricing> createPricingStructure() {
        Map<String, Pricing> productCodeToPriceMap = new HashMap<String, Pricing>();
        productCodeToPriceMap.put("A", new Pricing("A", new BigDecimal("2.00"), new BigDecimal("7.00"), 4));
        productCodeToPriceMap.put("B", new Pricing("B", new BigDecimal("12.00")));
        productCodeToPriceMap.put("C", new Pricing("C", new BigDecimal("1.25"), new BigDecimal("6.00"), 6));
        productCodeToPriceMap.put("D", new Pricing("D", new BigDecimal("0.15")));
        return productCodeToPriceMap;
    }

    private void scanNumberOfItemsWithProductCode(String productCode, int numberOfItems) {
        for (int i = 0; i < numberOfItems; i++) {
            terminal.scan(productCode);
        }
    }

}
