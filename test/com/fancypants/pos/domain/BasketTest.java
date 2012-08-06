package com.fancypants.pos.domain;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

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
        basket.add("A");
        assertThat(basket.getContents().get("A"), equalTo(1));
        basket.add("A");
        assertThat(basket.getContents().get("A"), equalTo(2));

    }
}
