package com.br.github.gabrielotsuka.storesystem.services;

import com.br.github.gabrielotsuka.storesystem.error.ClosedOrderException;
import com.br.github.gabrielotsuka.storesystem.error.ResourceNotFoundException;
import com.br.github.gabrielotsuka.storesystem.models.Customer;
import com.br.github.gabrielotsuka.storesystem.models.Item;
import com.br.github.gabrielotsuka.storesystem.models.Order;
import com.br.github.gabrielotsuka.storesystem.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

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

    private Order verifyOrderExistence(Long id){
        Optional<Order> order = orderRepository.findById(id);
        if (!order.isPresent())
            throw new ResourceNotFoundException("Order not found. ID: " + id);
        else
            return order.get();

    }

    public List<Order> getCustomerOrders(Long id) {
        return orderRepository.findByCustomerId(id);
    }


    @Transactional
    public Order addItemToOrder(Customer customer, Item item) {
        Order order = hasOpenedOrder(customer);
        itemService.addItemToOrder(order, item);
        order.setTotalPrice(order.getTotalPrice() + item.getItemPrice());
        orderRepository.save(order);
        return order;
    }

    @Transactional
    private Order hasOpenedOrder(Customer customer){
        List<Order> orders = orderRepository.findByCustomerId(customer.getId());
        for (Order order : orders)
            if(order.getStatus().equals("Opened"))
                return order;
        Order newOrder = new Order(customer);
        orderRepository.save(newOrder);
        return newOrder;
    }

    @Transactional
    public Order removeItemFromOrder(Long i_id, Customer customer) {
        Order order = hasOpenedOrder(customer);
        Item item = itemService.removeItem(i_id);
        order.setTotalPrice(order.getTotalPrice() - item.getItemPrice());
        orderRepository.save(order);
        return order;
    }

    @Transactional
    public Order editItem(Customer customer, Long i_id, Item newItem) {
        Item oldItem = itemService.getItemById(i_id);
        Order order  = oldItem.getOrder();
        isOpened(order);
        order.setTotalPrice(order.getTotalPrice() - oldItem.getItemPrice());
        itemService.editItem(oldItem, newItem);
        order.setTotalPrice(order.getTotalPrice() + oldItem.getItemPrice());

        orderRepository.save(order);
        return order;
    }

    public Order cleanOpenedOrder(Customer customer) {
        Order order = hasOpenedOrder(customer);
        order = cleanOrderById(order.getId());
        return order;
    }

    @Transactional
    public Order cleanOrderById(Long orderId){
        Order order = verifyOrderExistence(orderId);
        itemService.removeAllItems(order);
        order.setTotalPrice(0);
        orderRepository.save(order);
        return order;
    }

    @Transactional
    public Order closeOrder(Customer customer) {
        Order order = hasOpenedOrder(customer);
        order.setStatus("Closed");
        orderRepository.save(order);
        return order;
    }

    public void isOpened(Order order){
        if (order.getStatus().equals("Closed"))
            throw new ClosedOrderException(".");
    }
}
