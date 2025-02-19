package com.example.toko.controller;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.toko.handler.ProductNotFoundException;
import com.example.toko.handler.ProductSuccessDeleted;
import com.example.toko.handler.UpdatedProductSuccessfully;
import com.example.toko.model.Product;
import com.example.toko.model.ProductApproval;
import com.example.toko.model.ProductDetail;
import com.example.toko.model.ProductResponse;
import com.example.toko.service.TokoService;

@RestController
@RequestMapping("/toko")
@CrossOrigin(origins = "http://localhost:3000") // Gantilah dengan alamat frontend Anda
public class TokoController {

	@Autowired
    private TokoService tokoService;
	
	private static final Logger logger = Logger.getLogger(TokoController.class.getName());
	
	//@Operation(summary = "Get All Product", description = "Returns All Product if product table not empty")
	@GetMapping("/all")
	public ResponseEntity<ProductResponse> getProducts() {
		List<Product> product = tokoService.getProducts();  // Ambil data produk dari service
		ProductResponse prdRs =  new ProductResponse(product);
	    return ResponseEntity.ok(prdRs);
    }
	
	@PostMapping("/product")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        Product savedProduct = tokoService.saveProduct(product);
        return ResponseEntity.ok(savedProduct);
    }
	
	@GetMapping("/product/{id}")
    public ResponseEntity<ProductDetail> getDetailProduct(@PathVariable("id") Long productId) {
        Product savedProduct = tokoService.getDetailProduct(productId);
		ProductDetail prdRs =  new ProductDetail(savedProduct);
	    return ResponseEntity.ok(prdRs);
    }
	
	@PutMapping("/product/{id}")
    public void updateProduct(@PathVariable("id") Long productId, @RequestBody Product product) {
        Product savedProduct = tokoService.updateProduct(productId, product);
        throw new UpdatedProductSuccessfully("Product " + savedProduct.getName() + " Updated");
    }
	
	@DeleteMapping("/product/{id}")
	public void deleteProduct(@PathVariable("id") Long productId) {
	    Product existingProduct = tokoService.deleteProduct(productId);
	    throw new ProductSuccessDeleted("The Product " + existingProduct.getName() + " deleted successfully");
	}
	
	@GetMapping("/product/pending")
    public ResponseEntity<ProductResponse> getPendingProduct() {
		List<Product> product = tokoService.getPendingProducts();  // Ambil data produk dari service
		ProductResponse prdRs =  new ProductResponse(product);
	    return ResponseEntity.ok(prdRs);
    }
	
	@PutMapping("/product/{id}/approve")
    public void updateApproveProduct(@PathVariable("id") Long productId) {
        ProductApproval savedProduct = tokoService.approveProduct(productId);
        throw new UpdatedProductSuccessfully("Product " + savedProduct.getProduct().getName() + " Approved.");
    }
	
	@PutMapping("/product/{id}/reject")
    public void updateRejectedProduct(@PathVariable("id") Long productId) {
		ProductApproval savedProduct = tokoService.rejectedProduct(productId);
        throw new UpdatedProductSuccessfully("Product " + savedProduct.getProduct().getName() + " Rejected.");
    }
	
}
