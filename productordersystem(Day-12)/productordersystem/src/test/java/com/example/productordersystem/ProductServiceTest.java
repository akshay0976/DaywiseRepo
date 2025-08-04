package com.example.productordersystem;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.productordersystem.entity.Product;
import com.example.productordersystem.repository.ProductRepository;
import com.example.productordersystem.service.ProductService;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
	
	@Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    public void testAddProduct() {
        Product aashirvaadAtta = new Product(1L, "Aashirvaad Atta", 299, 100);
        
        when(productRepository.save(any(Product.class))).thenReturn(aashirvaadAtta);

        Product result = productService.addProduct(aashirvaadAtta);

        assertThat(result)
            .isNotNull()
            .extracting(Product::getName, Product::getPrice)
            .containsExactly("Aashirvaad Atta", 299.0);
        
        verify(productRepository).save(aashirvaadAtta);
    }

    @Test
    public void testGetAllProducts() {
        Product tataTea = new Product(1L, "Tata Tea", 150, 50);
        Product maggiNoodles = new Product(2L, "Maggi Noodles", 12, 200);
        
        when(productRepository.findAll()).thenReturn(Arrays.asList(tataTea, maggiNoodles));

        List<Product> result = productService.getAllProducts();

        assertThat(result)
            .hasSize(2)
            .extracting(Product::getName)
            .containsExactly("Tata Tea", "Maggi Noodles");
    }

    @Test
    public void testUpdateStock() {
        Product parleGBiscuits = new Product(1L, "Parle-G Biscuits", 10, 150);
        Product updatedProduct = new Product(1L, "Parle-G Biscuits", 10, 100);
        
        when(productRepository.findById(1L)).thenReturn(Optional.of(parleGBiscuits));
        when(productRepository.save(any(Product.class))).thenReturn(updatedProduct);

        Product result = productService.updateStock(1L, 100);

        assertThat(result)
            .isNotNull()
            .extracting(Product::getAvailableQuantity)
            .isEqualTo(100);
        
        verify(productRepository).findById(1L);
        verify(productRepository).save(argThat(p -> p.getAvailableQuantity() == 100));
    }

    @Test
    public void testUpdateStock_ProductNotFound() {
        when(productRepository.findById(99L)).thenReturn(Optional.empty());
        
        assertThatThrownBy(() -> productService.updateStock(99L, 100))
            .isInstanceOf(RuntimeException.class)
            .hasMessageContaining("Product not found");
        
        verify(productRepository, never()).save(any());
    }

}
