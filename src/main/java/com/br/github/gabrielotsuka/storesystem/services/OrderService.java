package com.br.github.gabrielotsuka.storesystem.services;

import com.br.github.gabrielotsuka.storesystem.models.Item;
import com.br.github.gabrielotsuka.storesystem.models.Order;
import com.br.github.gabrielotsuka.storesystem.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

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

    private void verifyOrderExistence(Long id){
        List<Order> orders = new ArrayList<>();
        orders = orderRepository.findByCustomerId(id);
        System.out.println(orders);
    }

    public List<Order> getCustomerOrders(Long id) {
        return orderRepository.findByCustomerId(id);
    }

    public Order addItemToOrder(Long id, Item item) {
        Order order = hasOpenedOrder(id);
        itemService.addItemToOrder(order, item);
    }

    private Order hasOpenedOrder(Long customerId){
        List<Order> orders = orderRepository.findByCustomerId(customerId);
        for (Order order : orders)
            if(order.getStatus().equals("Opened"))
                return order;
        return new Order();
    }
}
