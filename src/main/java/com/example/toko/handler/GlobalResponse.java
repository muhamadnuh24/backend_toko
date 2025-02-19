package com.example.toko.handler;

import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.example.toko.model.ProductException;
import com.example.toko.model.ProductUpdateDeletedResponse;

@ControllerAdvice
public class GlobalResponse {

	// Menangani exception custom ProductNotFoundException
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ProductException> handleProductNotFoundException(ProductNotFoundException ex) {
    	ProductException errorResponse = new ProductException(HttpStatus.OK.value(), ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.OK);
    }
    
    @ExceptionHandler(ProductSuccessDeleted.class)
    public ResponseEntity<ProductUpdateDeletedResponse> handleDeletedSuccessfully(ProductSuccessDeleted ex) {
    	ProductUpdateDeletedResponse errorResponse = new ProductUpdateDeletedResponse(HttpStatus.OK.value(), ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.OK);
    }
    
    @ExceptionHandler(UpdatedProductSuccessfully.class)
    public ResponseEntity<ProductUpdateDeletedResponse> handleUpdateSuccessfully(UpdatedProductSuccessfully ex) {
    	ProductUpdateDeletedResponse errorResponse = new ProductUpdateDeletedResponse(HttpStatus.OK.value(), ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.OK);
    }
    
    @ExceptionHandler(ProductNotNullException.class)
    public ResponseEntity<ProductException> handleContentNull(ProductNotNullException ex) {
    	ProductException errorResponse = new ProductException(HttpStatus.OK.value(), ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.OK);
    }
	
	@ExceptionHandler(ConfigDataResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ConfigDataResourceNotFoundException ex, WebRequest request) {
        return new ResponseEntity<>("Resource not found: " + ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HttpMessageNotWritableException.class)
    public ResponseEntity<String> handleHttpMessageNotWritableException(HttpMessageNotWritableException ex, WebRequest request) {
        return new ResponseEntity<>("Error writing JSON output: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGlobalException(Exception ex, WebRequest request) {
        return new ResponseEntity<>("Internal Server Error: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
