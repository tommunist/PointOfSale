package com.fancypants.pos.repository;

import com.fancypants.pos.domain.Pricing;
import com.fancypants.pos.exception.ProductCodeNotRecognisedException;

import java.util.Map;

public class PricingRepository {
    private Map<String, Pricing> productCodeToPriceMap;

    public PricingRepository(Map<String, Pricing> productCodeToPriceMap) {
        this.productCodeToPriceMap = productCodeToPriceMap;
    }

    public Pricing getPricingFor(String productCode) throws ProductCodeNotRecognisedException {
        if (productCodeToPriceMap.containsKey(productCode)) {
            return productCodeToPriceMap.get(productCode);
        }
        throw new ProductCodeNotRecognisedException("Error: Could not find price for product ['" + productCode + "']");
    }
}
