package com.orderfood;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApp {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        FoodOrderService service = context.getBean(FoodOrderService.class);
        service.processOrder();
    }
}
