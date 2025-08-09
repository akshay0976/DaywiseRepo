package com.example.orderAssignment.controller;

import com.example.orderAssignment.model.Order;
import com.example.orderAssignment.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/{productId}/{quantity}")
    public Order placeOrder(@PathVariable String productId, @PathVariable int quantity) {
        return orderService.placeOrder(productId, quantity);
    }
}
