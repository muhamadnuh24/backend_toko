package com.example.toko.handler;

public class ProductNotNullException extends RuntimeException {
    public ProductNotNullException(String message) {
        super(message);
    }
}