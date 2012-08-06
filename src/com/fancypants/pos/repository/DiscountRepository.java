package com.fancypants.pos.repository;

import com.fancypants.pos.VolumeDiscount;
import com.fancypants.pos.exception.DiscountNotFoundException;

import java.util.Map;

public class DiscountRepository {
    private Map<String, VolumeDiscount> productCodeToDiscountMap;

    public DiscountRepository(Map<String, VolumeDiscount> productCodeToDiscountMap) {
        this.productCodeToDiscountMap = productCodeToDiscountMap;
    }

    public VolumeDiscount getDiscountRuleFor(String productCode) throws DiscountNotFoundException {
        if (doesDiscountExistFor(productCode)) {
            return productCodeToDiscountMap.get(productCode);
        }
        throw new DiscountNotFoundException("Error: Could not find discount for product ['" + productCode + "']");
    }

    public boolean doesDiscountExistFor(String productCode) {
        return productCodeToDiscountMap.containsKey(productCode);
    }
}
