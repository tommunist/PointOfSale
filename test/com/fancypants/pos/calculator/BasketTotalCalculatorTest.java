package com.fancypants.pos.calculator;

import com.fancypants.pos.domain.Basket;
import com.fancypants.pos.domain.Pricing;
import com.fancypants.pos.exception.DiscountNotFoundException;
import com.fancypants.pos.exception.PriceNotFoundException;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BasketTotalCalculatorTest {
    private ProductTotalCalculator productTotalCalculator;
    private BasketTotalCalculator basketTotalCalculator;

    @Before
    public void setUp() throws Exception {
        productTotalCalculator = mock(ProductTotalCalculator.class);
        basketTotalCalculator = new BasketTotalCalculator(productTotalCalculator);
    }

    @Test
    public void shouldSumTotalsForEachProductForAllProductsInBasket() throws PriceNotFoundException, DiscountNotFoundException {
        Basket basket = new Basket();
        Pricing productA = mock(Pricing.class);
        Pricing productC = mock(Pricing.class);
        basket.add(productA);
        basket.add(productA);
        basket.add(productA);
        basket.add(productC);
        when(productTotalCalculator.calculateTotalFor(productA, 3)).thenReturn(new BigDecimal("300.00"));
        when(productTotalCalculator.calculateTotalFor(productC, 1)).thenReturn(new BigDecimal("2000.39"));
        BigDecimal basketTotal = basketTotalCalculator.calculateTotalFor(basket);
        assertThat(basketTotal, equalTo(new BigDecimal("2300.39")));
    }
}
