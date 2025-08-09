package com.example.eurekaAssignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaAssignmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaAssignmentApplication.class, args);
	}

}
