package com.fancypants.pos.exception;

public class ProductNotRecognisedException extends RuntimeException {
    public ProductNotRecognisedException(String message) {
        super(message);
    }
}
