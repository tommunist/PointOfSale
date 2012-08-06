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

    public String getProductCode() {
        return productCode;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public BigDecimal getVolumePrice() {
        return volumePrice;
    }

    public Integer getVolumeQuantity() {
        return volumeQuantity;
    }
}
