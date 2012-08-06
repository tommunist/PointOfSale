package com.fancypants.pos.calculator;

import com.fancypants.pos.domain.Basket;
import com.fancypants.pos.exception.DiscountNotFoundException;
import com.fancypants.pos.exception.PriceNotFoundException;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

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
        basket.add("A");
        basket.add("A");
        basket.add("A");
        basket.add("C");
        when(productTotalCalculator.calculateTotalFor("A", 3)).thenReturn(new BigDecimal("300.00"));
        when(productTotalCalculator.calculateTotalFor("C", 1)).thenReturn(new BigDecimal("23423.39"));
        basketTotalCalculator.calculateTotalFor(basket);
    }
}
