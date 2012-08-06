package com.fancypants.pos;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PointOfSaleTerminalTest {
    private PointOfSaleTerminal terminal;
    private BarCodeScanner scanner;
    private Basket basket;
    private TotalCalculator totalCalculator;

    @Before
    public void setUp() throws Exception {
        scanner = mock(BarCodeScanner.class);
        totalCalculator = mock(TotalCalculator.class);
        basket = mock(Basket.class);
        terminal = new PointOfSaleTerminal(scanner, basket, totalCalculator);

    }

    @Test
    public void shouldCalculateTotalWhenNoItemsScanned() {
        when(totalCalculator.calculateTotalFor(basket)).thenReturn(new BigDecimal("0.00"));

        assertThat(terminal.getTotal(), is(new BigDecimal("0.00")));

    }

    @Test
    public void shouldCalculateTotalForOneItem() throws ProductNotRecognisedException {
        Product productA = mock(Product.class);
        when(totalCalculator.calculateTotalFor(basket)).thenReturn(new BigDecimal("2.00"));
        when(scanner.scan("A")).thenReturn(productA);

        terminal.scan("A");

        assertThat(terminal.getTotal(), is(new BigDecimal("2.00")));
    }
}
