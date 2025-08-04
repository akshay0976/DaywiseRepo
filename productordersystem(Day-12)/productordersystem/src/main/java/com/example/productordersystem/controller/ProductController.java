package com.example.productordersystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.springtest.entity.Product;
import com.example.springtest.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	
	 @Autowired
	    private ProductService productService;

	    @PostMapping
	    public Product addProduct(@RequestBody Product product) {
	        return productService.addProduct(product);
	    }

	    @GetMapping
	    public List<Product> getAllProducts() {
	        return productService.getAllProducts();
	    }

	    @PutMapping("/{id}/stock")
	    public Product updateStock(@PathVariable Long id, @RequestParam int quantity) {
	        return productService.updateStock(id, quantity);
	    }
}

}
