package com.fancypants.pos.domain;

import java.util.HashMap;
import java.util.Map;

public class Basket {
    private Map<String, Integer> productToQuantityMap;

    public Basket() {
        productToQuantityMap = new HashMap<String, Integer>();
    }

    public void add(String productCode) {
        if (productToQuantityMap.containsKey(productCode)) {
            productToQuantityMap.put(productCode, productToQuantityMap.get(productCode) + 1);
        } else {
            productToQuantityMap.put(productCode, 1);
        }

    }


    public Map<String, Integer> getContents() {
        return productToQuantityMap;
    }
}
