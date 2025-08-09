package com.example.productAssignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ProductAssignmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductAssignmentApplication.class, args);
	}

}
