package com.example.productordersystem;

import static org.assertj.core.api.Assertions.*;

import static org.mockito.Mockito.*;


import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.productordersystem.entity.Order;
import com.example.productordersystem.entity.Product;
import com.example.productordersystem.repository.OrderRepository;
import com.example.productordersystem.repository.ProductRepository;
import com.example.productordersystem.service.OrderService;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {
	
	 @Mock
	    private OrderRepository orderRepository;
	    
	    @Mock
	    private ProductRepository productRepository;
	    
	    @InjectMocks
	    private OrderService orderService;

	    @Test
	    public void testPlaceOrder_Success() {
	        Product amulMilk = new Product(1L, "Amul Milk", 25, 80);
	        
	        Order expectedOrder = new Order();
	        expectedOrder.setOrderId(1L);
	        expectedOrder.setProduct(amulMilk);
	        expectedOrder.setQuantityOrdered(5);
	        expectedOrder.setOrderDate(LocalDateTime.now());

	        when(productRepository.findById(1L)).thenReturn(Optional.of(amulMilk));
	        when(orderRepository.save(any(Order.class))).thenReturn(expectedOrder);

	        Order result = orderService.placeOrder(1L, 5);

	        assertThat(result).isNotNull();
	        assertThat(result.getQuantityOrdered()).isEqualTo(5);
	        assertThat(result.getProduct().getName()).isEqualTo("Amul Milk");
	    }

	    @Test
	    public void testPlaceOrder_InsufficientStock() {
	        Product parleGBiscuits = new Product(2L, "Parle-G Biscuits", 10, 150);
	        
	        when(productRepository.findById(2L)).thenReturn(Optional.of(parleGBiscuits));
	        
	        assertThatThrownBy(() -> orderService.placeOrder(2L, 200))
	            .isInstanceOf(RuntimeException.class)
	            .hasMessageContaining("Insufficient stock");
	        
	        verify(orderRepository, never()).save(any());
	    }

	    @Test
	    public void testGetAllOrders() {
	        Product amulMilk = new Product(1L, "Amul Milk", 25, 80);
	        Product parleGBiscuits = new Product(2L, "Parle-G Biscuits", 10, 150);
	        
	        Order order1 = new Order();
	        order1.setOrderId(1L);
	        order1.setProduct(amulMilk);
	        order1.setQuantityOrdered(2);
	        
	        Order order2 = new Order();
	        order2.setOrderId(2L);
	        order2.setProduct(parleGBiscuits);
	        order2.setQuantityOrdered(10);
	        
	        when(orderRepository.findAll()).thenReturn(Arrays.asList(order1, order2));
	        
	        List<Order> result = orderService.getAllOrders();
	        
	        assertThat(result)
	            .hasSize(2)
	            .extracting(Order::getProduct)
	            .extracting(Product::getName)
	            .containsExactly("Amul Milk", "Parle-G Biscuits");
	    }

}
