package com.fancypants.pos;

import java.math.BigDecimal;

public class PointOfSaleTerminal {

    private BarCodeScanner scanner;
    private Basket basket;
    private TotalCalculator totalCalculator;

    public PointOfSaleTerminal(BarCodeScanner scanner, Basket basket, TotalCalculator totalCalculator) {
        this.scanner = scanner;
        this.basket = basket;
        this.totalCalculator = totalCalculator;
    }

    public void scan(String productCode) {
        basket.add(scanner.scan(productCode));
    }

    public BigDecimal getTotal() {
        return totalCalculator.calculateTotalFor(basket);
    }
}
