package com.fancypants.pos;

import com.fancypants.pos.calculator.BasketTotalCalculator;
import com.fancypants.pos.domain.Basket;
import com.fancypants.pos.domain.Pricing;
import com.fancypants.pos.exception.ProductCodeNotRecognisedException;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class PointOfSaleTerminalTest {
    private PointOfSaleTerminal terminal;
    private Scanner scanner;
    private Basket basket;
    private BasketTotalCalculator basketTotalCalculator;

    @Before
    public void setUp() throws Exception {
        scanner = mock(Scanner.class);
        basketTotalCalculator = mock(BasketTotalCalculator.class);
        basket = mock(Basket.class);
        terminal = new PointOfSaleTerminal(scanner, basket, basketTotalCalculator);

    }

    @Test
    public void shouldCheckProductCodeAndAddProductToBasketEachTimeScanIsCalled() throws ProductCodeNotRecognisedException {
        Pricing pricingProductA = mock(Pricing.class);
        when(scanner.scan("A")).thenReturn(pricingProductA);

        terminal.scan("A");

        verify(basket).add(pricingProductA);

    }

    @Test
    public void shouldCalculateTotal() throws ProductCodeNotRecognisedException {
        when(basketTotalCalculator.calculateTotalFor(basket)).thenReturn(new BigDecimal("2.00"));
        assertThat(terminal.getTotal(), is(new BigDecimal("2.00")));
    }
}
