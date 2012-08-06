package com.fancypants.pos.calculator;

import com.fancypants.pos.VolumeDiscount;
import com.fancypants.pos.exception.DiscountNotFoundException;
import com.fancypants.pos.exception.PriceNotFoundException;
import com.fancypants.pos.repository.DiscountRepository;
import com.fancypants.pos.repository.PricingRepository;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProductTotalCalculatorTest {

    private ProductTotalCalculator productTotalCalculator;
    private DiscountRepository discountRepository;
    private PricingRepository pricingRepository;

    @Before
    public void setUp() throws Exception {
        discountRepository = mock(DiscountRepository.class);
        pricingRepository = mock(PricingRepository.class);
        productTotalCalculator = new ProductTotalCalculator(discountRepository, pricingRepository);
    }

    @Test
    public void shouldCalculateTotalOfOneHundredDollarsForItemWithNoDiscount() throws DiscountNotFoundException, PriceNotFoundException {
        final String productCode = "test";
        when(pricingRepository.getPriceFor(productCode)).thenReturn(new BigDecimal("100.00"));
        when(discountRepository.doesDiscountExistFor(productCode)).thenReturn(false);

        assertThat(productTotalCalculator.calculateTotalFor(productCode, 1), equalTo(new BigDecimal("100.00")));
    }

    @Test
    public void shouldCalculateTotalForProductTwoItemsAndNoDiscount() throws DiscountNotFoundException, PriceNotFoundException {
        final String productCode = "test";

        when(discountRepository.doesDiscountExistFor("test")).thenReturn(false);
        when(pricingRepository.getPriceFor("test")).thenReturn(new BigDecimal("100.00"));

        assertThat(productTotalCalculator.calculateTotalFor(productCode, 2), equalTo(new BigDecimal("200.00")));
    }

    @Test
    public void shouldCalculateTotalForBasketWithTenItemsWhenDiscountIsTenForSevenHundredDollars() throws DiscountNotFoundException, PriceNotFoundException {
        VolumeDiscount discount = new VolumeDiscount(10, new BigDecimal("700.00"));
        final String productCode = "test";

        when(discountRepository.doesDiscountExistFor("test")).thenReturn(true);
        when(pricingRepository.getPriceFor("test")).thenReturn(new BigDecimal("100.00"));
        when(discountRepository.getDiscountRuleFor(productCode)).thenReturn(discount);

        assertThat(productTotalCalculator.calculateTotalFor(productCode, 10), equalTo(new BigDecimal("700.00")));
    }


}
