package com.fancypants.pos;

public class ProductNotRecognisedException extends Exception {
    public ProductNotRecognisedException(String message) {
        super(message);
    }
}
