package com.fancypants.pos.domain;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

public class BasketTest {

    private Basket basket;

    @Before
    public void setUp() throws Exception {
        basket = new Basket();
    }

    @Test
    public void shouldIndicateZeroQuantityWhenProductHasNotBeenAddedToBasket() {
        assertThat(basket.getContents().size(), equalTo(0));

    }

    @Test
    public void shouldIncrementQuantityByOneWhenProductWithSameProductCodeIsAdded() {
        Pricing productA = mock(Pricing.class);
        basket.add(productA);
        assertThat(basket.getContents().get(productA), equalTo(1));
        basket.add(productA);
        assertThat(basket.getContents().get(productA), equalTo(2));

    }

    @Test
    public void shouldAddOneOfProductAAndOneOfProductB() {
        Pricing productA = new Pricing("A", new BigDecimal("1.23"));
        Pricing productB = new Pricing("B", new BigDecimal("1.23"));
        basket.add(productA);
        basket.add(productB);
        assertThat(basket.getContents().get(productA), equalTo(1));
        assertThat(basket.getContents().get(productB), equalTo(1));

    }
}
