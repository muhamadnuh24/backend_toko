package com.example.toko.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductException {
    private int responseCode;
	private String errorMessage;
}
