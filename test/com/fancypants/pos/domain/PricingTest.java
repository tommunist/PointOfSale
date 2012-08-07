package com.fancypants.pos.domain;

import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PricingTest {

    @Test
    public void shouldIndicateThatPricingHasADiscount() {
        Pricing pricingWithDiscount = new Pricing("A", new BigDecimal("123.00"), new BigDecimal("200"), 2);
        assertThat(pricingWithDiscount.hasVolumeDiscount(), is(true));

    }

    @Test
    public void shouldIndicateThatPricingHasNoDiscount() {
        Pricing pricingWithNoDiscount = new Pricing("A", new BigDecimal("123.00"));
        assertThat(pricingWithNoDiscount.hasVolumeDiscount(), is(false));
        assertThat(pricingWithNoDiscount.getVolumePrice(), is(nullValue()));
        assertThat(pricingWithNoDiscount.getVolumeQuantity(), is(nullValue()));

        pricingWithNoDiscount = new Pricing("A", new BigDecimal("123.00"), null, null);
        assertThat(pricingWithNoDiscount.hasVolumeDiscount(), is(false));
        assertThat(pricingWithNoDiscount.getVolumePrice(), is(nullValue()));
        assertThat(pricingWithNoDiscount.getVolumeQuantity(), is(nullValue()));

    }

    @Test
    public void shoulddIndicateThatPricingHasNoDiscount() {
        Pricing pricingWithNoDiscount = new Pricing("A", new BigDecimal("123.00"));
        assertThat(pricingWithNoDiscount.hasVolumeDiscount(), is(false));
        assertThat(pricingWithNoDiscount.getVolumePrice(), is(nullValue()));
        assertThat(pricingWithNoDiscount.getVolumeQuantity(), is(nullValue()));

        pricingWithNoDiscount = new Pricing("A", new BigDecimal("123.00"), null, null);
        assertThat(pricingWithNoDiscount.hasVolumeDiscount(), is(false));
        assertThat(pricingWithNoDiscount.getVolumePrice(), is(nullValue()));
        assertThat(pricingWithNoDiscount.getVolumeQuantity(), is(nullValue()));

    }

    @Test
    public void shouldIndicateEqualityWhenProductCodeEqual() {
        Pricing pricingWithNoDiscount1 = new Pricing("A", new BigDecimal("123.00"));
        Pricing pricingWithNoDiscount2 = new Pricing("A", new BigDecimal("13.00"));
        assertThat(pricingWithNoDiscount1, equalTo(pricingWithNoDiscount2));
        assertThat(pricingWithNoDiscount1.hashCode(), equalTo(pricingWithNoDiscount2.hashCode()));

    }

    @Test
    public void shouldIndicateInequalityWhenProductCodeNotEqual() {
        Pricing pricingWithNoDiscount1 = new Pricing("A", new BigDecimal("123.00"), new BigDecimal("200.00"), 3);
        Pricing pricingWithNoDiscount2 = new Pricing("B", new BigDecimal("123.00"), new BigDecimal("200.00"), 3);
        assertThat(pricingWithNoDiscount1, not(equalTo(pricingWithNoDiscount2)));
        assertThat(pricingWithNoDiscount1.hashCode(), not(equalTo(pricingWithNoDiscount2.hashCode())));

    }

}
