package com.fancypants.pos.exception;

public class ProductCodeNotRecognisedException extends RuntimeException {
    public ProductCodeNotRecognisedException(String message) {
        super(message);
    }
}
