package com.example.toko.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductResponse {
    private List<Product> products;
}
