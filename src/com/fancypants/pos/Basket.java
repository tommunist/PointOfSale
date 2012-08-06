package com.fancypants.pos;

import java.util.Map;

public class Basket {
    private Map<Product, Integer> items;

    public void add(Product product) {
        throw new UnsupportedOperationException("Method Not Implemented");
    }

    public Map<Product, Integer> getItems() {
        return items;
    }
}
