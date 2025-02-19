package com.example.toko.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.toko.model.Product;
import com.example.toko.model.ProductApproval;

public interface ProductRepository extends JpaRepository<Product, Long> {
	@Query("SELECT p FROM Product p JOIN p.productApproval pa WHERE pa.approval = :status")
    List<Product> findProducts(@Param("status") String status);
}
