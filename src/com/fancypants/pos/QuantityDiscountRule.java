package com.fancypants.pos;

import java.math.BigDecimal;

public class QuantityDiscountRule {
    private BigDecimal discountedAmount;
    private final int thresholdQuantity;

    public QuantityDiscountRule(int thresholdQuantity, BigDecimal discountedAmount) {
        this.thresholdQuantity = thresholdQuantity;
        this.discountedAmount = discountedAmount;
    }

    public Integer getThresholdQuantity() {
        return thresholdQuantity;
    }

    public BigDecimal getDiscountedAmount() {
        return discountedAmount;
    }
}
