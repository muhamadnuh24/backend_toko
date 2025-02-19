package com.example.toko.handler;

import com.example.toko.model.Product;

public class UpdatedProductSuccessfully extends RuntimeException {
    public UpdatedProductSuccessfully(String message) {
        super(message);
    }
}