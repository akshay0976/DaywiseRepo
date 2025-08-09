package com.example.productAssignment.service;


import java.util.List;
import java.util.Optional;

import com.example.productAssignment.model.Product;

public interface ProductService {
    Product saveProduct(Product product);
    List<Product> getAllProducts();
    Optional<Product> getProductById(Long id);
    Product updateProduct(Long id, Product product);
    void deleteProduct(Long id);
}
