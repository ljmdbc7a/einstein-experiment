package com.einstein.experiment.guice.example.order;

import com.google.inject.Singleton;

@Singleton
public class OrderServiceImpl implements OrderService {

    @Override
    public void createOrder() {
        System.out.println("OrderService's createOrder is invoked");
    }
}
