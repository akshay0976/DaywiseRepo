package com.example.orderAssignment.service;

import com.example.orderAssignment.dto.ProductDTO;
import com.example.orderAssignment.model.Order;
import com.example.orderAssignment.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductClient productClient;

    public Order placeOrder(String productId, int quantity) {
        ProductDTO product = productClient.getProductById(productId);

        if (product.getStock() < quantity) {
            throw new RuntimeException("Insufficient stock");
        }

        double totalPrice = product.getPrice() * quantity;

        Order order = new Order();
        order.setProductId(productId);
        order.setQuantity(quantity);
        order.setTotalPrice(totalPrice);
        order.setStatus("PLACED");

        return orderRepository.save(order);
    }
}
