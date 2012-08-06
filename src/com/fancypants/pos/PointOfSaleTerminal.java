package com.fancypants.pos;

import com.fancypants.pos.calculator.BasketTotalCalculator;
import com.fancypants.pos.domain.Basket;
import com.fancypants.pos.exception.DiscountNotFoundException;
import com.fancypants.pos.exception.PriceNotFoundException;
import com.fancypants.pos.exception.ProductNotRecognisedException;

import java.math.BigDecimal;

public class PointOfSaleTerminal {

    private ProductCodeChecker checker;
    private Basket basket;
    private BasketTotalCalculator basketTotalCalculator;

    public PointOfSaleTerminal(ProductCodeChecker checker, Basket basket, BasketTotalCalculator basketTotalCalculator) {
        this.checker = checker;
        this.basket = basket;
        this.basketTotalCalculator = basketTotalCalculator;
    }

    public void scan(String productCode) throws ProductNotRecognisedException {
        checker.checkProductCode(productCode);
        basket.add(productCode);
    }

    public BigDecimal getTotal() throws PriceNotFoundException, DiscountNotFoundException {
        return basketTotalCalculator.calculateTotalFor(basket);
    }
}
