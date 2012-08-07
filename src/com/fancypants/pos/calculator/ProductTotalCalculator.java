package com.fancypants.pos.calculator;

import com.fancypants.pos.domain.Pricing;

import java.math.BigDecimal;

public class ProductTotalCalculator {

    public BigDecimal calculateTotalFor(Pricing pricing, Integer quantity) {
        if (pricing.hasVolumeDiscount()) {
            return calculateDiscountedTotal(pricing, quantity);
        }
        return calculateUndiscountedTotal(pricing, quantity);
    }

    private BigDecimal calculateUndiscountedTotal(Pricing pricing, Integer quantity) {
        return pricing.getUnitPrice().multiply(new BigDecimal(quantity));
    }

    private BigDecimal calculateDiscountedTotal(Pricing pricing, Integer quantity) {

        Integer discountVolumeQuantity = pricing.getVolumeQuantity();
        BigDecimal discountVolumePrice = pricing.getVolumePrice();
        BigDecimal discountedItemsSubtotal = new BigDecimal(quantity / discountVolumeQuantity).multiply(discountVolumePrice);
        BigDecimal undiscountedItemsSubtotal = calculateUndiscountedTotal(pricing, quantity % discountVolumeQuantity);
        return discountedItemsSubtotal.add(undiscountedItemsSubtotal);
    }
}
