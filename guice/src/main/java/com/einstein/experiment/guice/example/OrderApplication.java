package com.einstein.experiment.guice.example;

import com.einstein.experiment.guice.example.order.OrderManager;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class OrderApplication {

    public static void main(String[] args) {
        System.out.println("application is starting...");
        Injector injector = Guice.createInjector(new OrderModule());
        OrderManager orderManager = injector.getInstance(OrderManager.class);
        orderManager.createOrder();
        System.out.println("application is end...");
    }
}
