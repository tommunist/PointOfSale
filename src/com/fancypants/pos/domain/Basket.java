package com.fancypants.pos.domain;

import java.util.HashMap;
import java.util.Map;

public class Basket {
    private Map<Pricing, Integer> productToQuantityMap;

    public Basket() {
        productToQuantityMap = new HashMap<Pricing, Integer>();
    }

    public void add(Pricing productPricing) {
        if (productToQuantityMap.containsKey(productPricing)) {
            productToQuantityMap.put(productPricing, productToQuantityMap.get(productPricing) + 1);
        } else {
            productToQuantityMap.put(productPricing, 1);
        }

    }


    public Map<Pricing, Integer> getContents() {
        return productToQuantityMap;
    }
}
