package com.fancypants.pos;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class BarCodeScanner {
    private Map<String, Product> barcodeToProductMap;

    public BarCodeScanner() {
        barcodeToProductMap = new HashMap<String, Product>();
        barcodeToProductMap.put("A", new Product("A", new BigDecimal("2.00")));
        barcodeToProductMap.put("B", new Product("B", new BigDecimal("12.00")));
        barcodeToProductMap.put("C", new Product("C", new BigDecimal("1.25")));
        barcodeToProductMap.put("D", new Product("D", new BigDecimal("0.15")));
    }

    public Product scan(String barcode) throws ProductNotRecognisedException {
        if (barcodeToProductMap.containsKey(barcode)) {
            return barcodeToProductMap.get(barcode);
        }
        throw new ProductNotRecognisedException("Error: could not recognise product with code ['" + barcode + "'].");
    }
}
