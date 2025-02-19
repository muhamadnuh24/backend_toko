package com.example.toko.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.toko.model.ProductApproval;

public interface ProductApprovalRepository extends JpaRepository<ProductApproval, Long> {
	ProductApproval findByProductId(Long productId);
}
