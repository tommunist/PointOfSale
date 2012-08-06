package com.fancypants.pos;

import java.math.BigDecimal;

public class VolumeDiscount {
    private BigDecimal amount;
    private final int quantity;

    public VolumeDiscount(int quantity, BigDecimal amount) {
        this.quantity = quantity;
        this.amount = amount;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
