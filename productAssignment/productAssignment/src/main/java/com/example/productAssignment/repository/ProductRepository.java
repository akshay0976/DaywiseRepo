package com.example.productAssignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.productAssignment.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}

