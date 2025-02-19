package com.example.toko.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private Long price;
	private String description;
	
	@JsonIgnore  // Menyembunyikan property 'productApproval' dari JSON output
	@OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
    private ProductApproval productApproval;
	public Product() {
		super();
	}
	
}