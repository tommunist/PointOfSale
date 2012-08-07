package com.fancypants.pos.calculator;

import com.fancypants.pos.domain.Pricing;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProductTotalCalculatorTest {

    private ProductTotalCalculator productTotalCalculator;

    @Before
    public void setUp() throws Exception {
        productTotalCalculator = new ProductTotalCalculator();
    }

    @Test
    public void shouldCalculateTotalOfOneHundredDollarsForItemWithNoDiscount() {
        Pricing pricingWithNoDiscount = mock(Pricing.class);
        when(pricingWithNoDiscount.getUnitPrice()).thenReturn(new BigDecimal("100.00"));
        when(pricingWithNoDiscount.hasVolumeDiscount()).thenReturn(false);

        assertThat(productTotalCalculator.calculateTotalFor(pricingWithNoDiscount, 1), equalTo(new BigDecimal("100.00")));
    }

    @Test
    public void shouldCalculateTotalForOneProductTwoItemsAndNoDiscount() {
        Pricing pricingWithNoDiscount = mock(Pricing.class);
        when(pricingWithNoDiscount.getProductCode()).thenReturn("D");
        when(pricingWithNoDiscount.getUnitPrice()).thenReturn(new BigDecimal("100.00"));
        when(pricingWithNoDiscount.hasVolumeDiscount()).thenReturn(false);

        assertThat(productTotalCalculator.calculateTotalFor(pricingWithNoDiscount, 2), equalTo(new BigDecimal("200.00")));
    }

    @Test
    public void shouldCalculateTotalForBasketWithTenItemsWhenDiscountIsTenForSevenHundredDollars() {
        Pricing pricingWithDiscount = mock(Pricing.class);
        when(pricingWithDiscount.getProductCode()).thenReturn("Z");
        when(pricingWithDiscount.getUnitPrice()).thenReturn(new BigDecimal("100.00"));
        when(pricingWithDiscount.getVolumePrice()).thenReturn(new BigDecimal("700.00"));
        when(pricingWithDiscount.getVolumeQuantity()).thenReturn(10);
        when(pricingWithDiscount.hasVolumeDiscount()).thenReturn(true);

        assertThat(productTotalCalculator.calculateTotalFor(pricingWithDiscount, 10), equalTo(new BigDecimal("700.00")));
    }


}
