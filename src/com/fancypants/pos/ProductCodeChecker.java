package com.fancypants.pos;

import com.fancypants.pos.exception.ProductNotRecognisedException;

import java.util.HashMap;
import java.util.Map;

public class ProductCodeChecker {
    private Map<String, Boolean> productCodeMap;

    public ProductCodeChecker() {
        productCodeMap = new HashMap<String, Boolean>();
    }

    public void checkProductCode(String productCode) throws ProductNotRecognisedException {
        if (!productCodeMap.containsKey(productCode)) {
            throw new ProductNotRecognisedException("Error: could not recognise product with code ['" + productCode + "'].");
        }
    }

    public void add(String productCode) {
        productCodeMap.put(productCode, true);
    }
}
