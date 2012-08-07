package com.fancypants.pos;

import com.fancypants.pos.calculator.BasketTotalCalculator;
import com.fancypants.pos.domain.Basket;
import com.fancypants.pos.domain.Pricing;
import com.fancypants.pos.exception.DiscountNotFoundException;
import com.fancypants.pos.exception.PriceNotFoundException;
import com.fancypants.pos.exception.ProductCodeNotRecognisedException;

import java.math.BigDecimal;

public class PointOfSaleTerminal {

    private Scanner scanner;
    private Basket basket;
    private BasketTotalCalculator basketTotalCalculator;

    public PointOfSaleTerminal(Scanner scanner, Basket basket, BasketTotalCalculator basketTotalCalculator) {
        this.scanner = scanner;
        this.basket = basket;
        this.basketTotalCalculator = basketTotalCalculator;
    }

    public void scan(String productCode) throws ProductCodeNotRecognisedException {
        Pricing productPricing = scanner.scan(productCode);
        basket.add(productPricing);
    }

    public BigDecimal getTotal() throws PriceNotFoundException, DiscountNotFoundException {
        return basketTotalCalculator.calculateTotalFor(basket);
    }
}
