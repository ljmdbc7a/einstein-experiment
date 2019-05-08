package com.einstein.experiment.guice.example.order;

import com.google.inject.Inject;

public class OrderManager {

    private final OrderService orderService;

    @Inject
    public OrderManager(OrderService orderService) {
        this.orderService = orderService;
    }

    public void createOrder() {
        orderService.createOrder();
    }
}
