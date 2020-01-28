package com.br.github.gabrielotsuka.storesystem.services;

import com.br.github.gabrielotsuka.storesystem.models.Customer;
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

    public Order addItemToOrder(Customer customer, Item item) {
        Order order = hasOpenedOrder(customer);
        itemService.addItemToOrder(order, item);
        order.setTotalPrice(order.getTotalPrice() + item.getItemPrice());
        orderRepository.save(order);
        return order;
    }

    private Order hasOpenedOrder(Customer customer){
        List<Order> orders = orderRepository.findByCustomerId(customer.getId());
        for (Order order : orders)
            if(order.getStatus().equals("Opened"))
                return order;
        return new Order(customer);
    }
}
