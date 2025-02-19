package com.example.toko.model;

import java.util.Date;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
public class ProductApproval {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // `product_id` menjadi primary key untuk tabel ini
	
	@OneToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
    private Product product;  // Kolom foreign key yang menghubungkan dengan produk
	private String approval;
	private Date approvalDate;
	
	public ProductApproval() {
		super();
	}
	
}