package com.br.github.gabrielotsuka.storesystem.services;

import com.br.github.gabrielotsuka.storesystem.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    private final OrderRepository orderRepository;
    @Autowired
    private final ItemService itemService;

    public OrderService(OrderRepository orderRepository, ItemService itemService) {
        this.orderRepository = orderRepository;
        this.itemService = itemService;
    }


}
