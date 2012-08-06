package com.fancypants.pos;

import java.math.BigDecimal;
import java.util.Map;

public class TotalCalculator {
    public BigDecimal calculateTotalFor(Basket basket) {
        Map<Product, Integer> items = basket.getItems();
        BigDecimal total = new BigDecimal("0.00");
        for (Product product : items.keySet()) {
            total = total.add(product.getPrice());
        }
        return total;
    }
}
