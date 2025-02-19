package com.example.toko.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductUpdateDeletedResponse {
    private int responseCode;
	private String message;
}
