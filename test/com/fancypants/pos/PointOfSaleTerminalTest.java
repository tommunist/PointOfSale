package com.fancypants.pos;

import com.fancypants.pos.calculator.BasketTotalCalculator;
import com.fancypants.pos.domain.Basket;
import com.fancypants.pos.exception.DiscountNotFoundException;
import com.fancypants.pos.exception.PriceNotFoundException;
import com.fancypants.pos.exception.ProductNotRecognisedException;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class PointOfSaleTerminalTest {
    private PointOfSaleTerminal terminal;
    private ProductCodeChecker checker;
    private Basket basket;
    private BasketTotalCalculator basketTotalCalculator;

    @Before
    public void setUp() throws Exception {
        checker = mock(ProductCodeChecker.class);
        basketTotalCalculator = mock(BasketTotalCalculator.class);
        basket = mock(Basket.class);
        terminal = new PointOfSaleTerminal(checker, basket, basketTotalCalculator);

    }

    @Test
    public void shouldCheckProductCodeAndAddProductToBasketEachTimeScanIsCalled() throws ProductNotRecognisedException {
        terminal.scan("A");

        verify(basket).add("A");
        verify(checker).checkProductCode("A");
    }

    @Test
    public void shouldCalculateTotal() throws ProductNotRecognisedException, PriceNotFoundException, DiscountNotFoundException {
        when(basketTotalCalculator.calculateTotalFor(basket)).thenReturn(new BigDecimal("2.00"));
        assertThat(terminal.getTotal(), is(new BigDecimal("2.00")));
    }
}
