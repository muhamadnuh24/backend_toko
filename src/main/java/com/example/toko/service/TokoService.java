package com.example.toko.service;

import java.net.http.HttpHeaders;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.toko.handler.ProductNotFoundException;
import com.example.toko.handler.ProductNotNullException;
import com.example.toko.model.Product;
import com.example.toko.model.ProductApproval;
import com.example.toko.repository.ProductApprovalRepository;
import com.example.toko.repository.ProductRepository;

@Service //singleton
public class TokoService {

	private final ProductRepository productRepository;
	private final ProductApprovalRepository productApprovalRepository;
	
	@Autowired
	public TokoService(ProductRepository productRepository, ProductApprovalRepository productApprovalRepository) {
		this.productRepository = productRepository;
		this.productApprovalRepository = productApprovalRepository;
	}
	
	public List<Product> getProducts() {
		List<Product> response = new ArrayList();
        response = productRepository.findProducts("APPROVED");
        
        if (response.isEmpty()) {
			throw new ProductNotFoundException("Tidak ada produk, mohon tambahkan produk terlebih dahulu."); 
	    }
        
        return response;
    }
	

	public Product getDetailProduct(Long productId) {
		Product response = new Product();
        response = productRepository.findById(productId).orElse(null);;
        
        if (response == null) {
			throw new ProductNotFoundException("Produk tidak ditemukan."); 
	    }
        
        return response;
	}
	
	public List<Product> getPendingProducts() {
		List<Product> response = new ArrayList();
        response = productRepository.findProducts("PENDING");
        
        if (response.isEmpty()) {
			throw new ProductNotFoundException("Tidak ada produk."); 
	    }
        
        return response;
    }
	
	public Product saveProduct(Product product) {
		
		if(product == null || product.getName() == null || product.getPrice() == 0 || product.getDescription() == null || product.getDescription().equalsIgnoreCase("") || product.getName().equalsIgnoreCase("")) {
			if(product.getPrice() == 0 || product.getPrice() < 0) {
				throw new ProductNotNullException("Tidak Dapat menambahkan Data, Price harus lebih besar dari 0");
			}else if(product.getDescription() == null || product.getDescription().equalsIgnoreCase("")) {
				throw new ProductNotNullException("Tidak Dapat menambahkan Data, Description kosong");
			}else if(product.getName() == null || product.getName().equalsIgnoreCase("")) {
				throw new ProductNotNullException("Tidak Dapat menambahkan Data, Nama Kosong");
			}
		} 
		
		
		ProductApproval approval = new ProductApproval();
		approval.setApproval("PENDING");
		product.setProductApproval(approval);
		approval.setProduct(product);
		
        return productRepository.save(product);  // save() akan menyimpan produk ke database
    }
	
	public Product updateProduct(Long id, Product updatedProduct) {
		Product existingProduct = productRepository.findById(id).orElse(null);

        if (existingProduct == null) {
        	throw new ProductNotFoundException("Update produk Gagal, Id Tidak ditemukan");
        }
        
        if(updatedProduct.getPrice() == 0 || updatedProduct.getPrice() < 0) {
			throw new ProductNotNullException("Tidak Dapat mengubah Data, Price harus lebih besar dari 0");
		}else if(updatedProduct.getDescription() == null || updatedProduct.getDescription().equalsIgnoreCase("")) {
			throw new ProductNotNullException("Tidak Dapat mengubah Data, Description kosong");
		}else if(updatedProduct.getName() == null || updatedProduct.getName().equalsIgnoreCase("")) {
			throw new ProductNotNullException("Tidak Dapat mengubah Data, Nama Kosong");
		}else {
			existingProduct.setName(updatedProduct.getName());
	        existingProduct.setPrice(updatedProduct.getPrice());
	        existingProduct.setDescription(updatedProduct.getDescription());
		}

        return productRepository.save(existingProduct);
    }
	
	public ProductApproval approveProduct(Long id) {
		ProductApproval approval = productApprovalRepository.findByProductId(id);

	    if (approval != null) {
	        // Ubah status approval menjadi APPROVED
	        approval.setApproval("APPROVED");
	        approval.setApprovalDate(new Date());  // Set tanggal approval sekarang
	        ;
	        
	        // Simpan perubahan ke database
	        return productApprovalRepository.save(approval);
	    } else {
	        throw new ProductNotFoundException("Product approval not found for productId: " + id);
	    }
    }
	
	public ProductApproval rejectedProduct(Long id) {
		ProductApproval approval = productApprovalRepository.findByProductId(id);

	    if (approval != null) {
	        // Ubah status approval menjadi APPROVED
	        approval.setApproval("REJECTED");
	        approval.setApprovalDate(new Date());  // Set tanggal approval sekarang
	        ;
	        
	        // Simpan perubahan ke database
	        return productApprovalRepository.save(approval);
	    } else {
	        throw new ProductNotFoundException("Product approval not found for productId: " + id);
	    }
    }
	
	public Product deleteProduct(Long id) {
		Product existingProduct = productRepository.findById(id).orElse(null);

        if (existingProduct == null) {
        	throw new ProductNotFoundException("Delete produk Gagal, Id Tidak ditemukan");
        }
        
        productRepository.delete(existingProduct);
        
        return existingProduct;
        
    }
	
}
