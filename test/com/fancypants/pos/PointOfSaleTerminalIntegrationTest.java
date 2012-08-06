package com.fancypants.pos;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PointOfSaleTerminalIntegrationTest {

    private PointOfSaleTerminal terminal;

    @Before
    public void setUp() {
        terminal = new PointOfSaleTerminal(new BarCodeScanner(), new Basket(), new TotalCalculator());
    }

    @Test
    @Ignore("WIP")
    public void shouldProvideTotalForBasketWithOneOfEachItem() throws ProductNotRecognisedException {
        terminal.scan("A");
        terminal.scan("B");
        terminal.scan("C");
        terminal.scan("D");

        assertThat(terminal.getTotal(), is(new BigDecimal("15.40")));

    }

}
