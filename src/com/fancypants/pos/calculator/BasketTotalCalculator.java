package com.fancypants.pos.calculator;

import com.fancypants.pos.domain.Basket;
import com.fancypants.pos.domain.Pricing;
import com.fancypants.pos.exception.DiscountNotFoundException;
import com.fancypants.pos.exception.PriceNotFoundException;

import java.math.BigDecimal;
import java.util.Map;

public class BasketTotalCalculator {
    private ProductTotalCalculator productTotalCalculator;

    public BasketTotalCalculator(ProductTotalCalculator productTotalCalculator) {
        this.productTotalCalculator = productTotalCalculator;
    }

    public BigDecimal calculateTotalFor(Basket basket) throws PriceNotFoundException, DiscountNotFoundException {
        Map<Pricing, Integer> contents = basket.getContents();
        BigDecimal total = new BigDecimal("0.00");
        for (Pricing pricing : contents.keySet()) {
            total = total.add(productTotalCalculator.calculateTotalFor(pricing, contents.get(pricing)));
        }
        return total;
    }
}
