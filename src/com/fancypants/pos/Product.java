package com.fancypants.pos;

import java.math.BigDecimal;

public class Product {
    private BigDecimal price;
    private String code;

    public Product(String code, BigDecimal price) {
        this.price = price;
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
