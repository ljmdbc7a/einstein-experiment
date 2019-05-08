package com.einstein.experiment.guice.example;

import com.einstein.experiment.guice.example.order.OrderService;
import com.einstein.experiment.guice.example.order.OrderServiceImpl;
import com.google.inject.AbstractModule;

public class OrderModule extends AbstractModule {

    @Override
    public void configure() {
        bind(OrderService.class).to(OrderServiceImpl.class);
    }
}
