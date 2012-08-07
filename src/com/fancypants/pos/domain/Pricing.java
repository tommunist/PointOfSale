package com.fancypants.pos.domain;

import java.math.BigDecimal;

public class Pricing {
    private String productCode;
    private BigDecimal unitPrice;
    private BigDecimal volumePrice;
    private Integer volumeQuantity;

    public Pricing(String productCode, BigDecimal unitPrice, BigDecimal volumePrice, Integer volumeQuantity) {
        this.productCode = productCode;
        this.unitPrice = unitPrice;
        this.volumePrice = volumePrice;
        this.volumeQuantity = volumeQuantity;
    }

    public Pricing(String productCode, BigDecimal unitPrice) {
        this(productCode, unitPrice, null, null);
    }

    public String getProductCode() {
        return productCode;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public BigDecimal getVolumePrice() {
        return volumePrice;
    }

    public boolean hasVolumeDiscount() {
        return (volumePrice != null && volumeQuantity != null);
    }

    public Integer getVolumeQuantity() {
        return volumeQuantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        return (o != null
                && getClass() == o.getClass())
                && productCode.equals(((Pricing) o).getProductCode());
    }

    @Override
    public int hashCode() {
        return productCode != null ? productCode.hashCode() : 0;
    }
}
