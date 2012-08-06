package com.fancypants.pos;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TotalCalculatorTest {

    private TotalCalculator totalCalculator;

    @Before
    public void setUp() throws Exception {
        totalCalculator = new TotalCalculator();
    }

    @Test
    public void shouldIndicateTotalOfZeroForEmptyBasket() {
        Basket basket = mock(Basket.class);

        when(basket.getItems()).thenReturn(new HashMap<Product, Integer>());

        assertThat(totalCalculator.calculateTotalFor(basket), equalTo(new BigDecimal("0.00")));
    }

    @Test
    public void shouldIndicateTotalOfForBasketWithOneItemAndNoDiscount() {
        Basket basket = mock(Basket.class);

        Map<Product, Integer> basketItems = new HashMap<Product, Integer>();
        basketItems.put(new Product("test", new BigDecimal("100.00")), 1);
        when(basket.getItems()).thenReturn(basketItems);
        assertThat(totalCalculator.calculateTotalFor(basket), equalTo(new BigDecimal("100.00")));
    }

}
