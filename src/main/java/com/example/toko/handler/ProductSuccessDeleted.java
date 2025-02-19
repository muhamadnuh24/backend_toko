package com.example.toko.handler;

public class ProductSuccessDeleted extends RuntimeException {
    public ProductSuccessDeleted(String message) {
        super(message);
    }
}