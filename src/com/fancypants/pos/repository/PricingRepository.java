package com.fancypants.pos.repository;

import com.fancypants.pos.exception.PriceNotFoundException;

import java.math.BigDecimal;
import java.util.Map;

public class PricingRepository {
    private Map<String, BigDecimal> productCodeToPriceMap;

    public PricingRepository(Map<String, BigDecimal> productCodeToPriceMap) {
        this.productCodeToPriceMap = productCodeToPriceMap;
    }

    public BigDecimal getPriceFor(String productCode) throws PriceNotFoundException {
        if (productCodeToPriceMap.containsKey(productCode)) {
            return productCodeToPriceMap.get(productCode);
        }
        throw new PriceNotFoundException("Error: Could not find price for product ['" + productCode + "']");
    }
}
