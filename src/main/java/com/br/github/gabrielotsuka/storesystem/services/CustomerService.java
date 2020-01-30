package com.br.github.gabrielotsuka.storesystem.services;

import com.br.github.gabrielotsuka.storesystem.controllers.request.customer.EditCustomerRequest;
import com.br.github.gabrielotsuka.storesystem.controllers.request.customer.PasswordCustomerRequest;
import com.br.github.gabrielotsuka.storesystem.controllers.request.item.ItemRequest;
import com.br.github.gabrielotsuka.storesystem.controllers.response.OrderResponse;
import com.br.github.gabrielotsuka.storesystem.error.ResourceNotFoundException;
import com.br.github.gabrielotsuka.storesystem.models.Customer;
import com.br.github.gabrielotsuka.storesystem.models.Item;
import com.br.github.gabrielotsuka.storesystem.models.Order;
import com.br.github.gabrielotsuka.storesystem.models.Product;
import com.br.github.gabrielotsuka.storesystem.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private final CustomerRepository customerRepository;
    @Autowired
    private final OrderService orderService;
    @Autowired
    private final ProductService productService;
    @Autowired
    private final ItemService itemService;

    public CustomerService(CustomerRepository customerRepository, OrderService orderService, ProductService productService, ItemService itemService) {
        this.customerRepository = customerRepository;
        this.orderService = orderService;
        this.productService = productService;
        this.itemService = itemService;
    }

    private Customer verifyCustomerExistence(Long id){
        Optional<Customer> customer = customerRepository.findById(id);
        if(!customer.isPresent())
            throw new ResourceNotFoundException("Customer not found. ID: " + id);
        else
            return customer.get();
    }

    @Transactional
    public Customer saveCustomer(Customer customer) {
        customerRepository.save(customer);
        return customer;
    }

    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    public List<Product> getStock() {
        return productService.getProducts();
    }

    public Customer getCustomerById(Long id) {
        return verifyCustomerExistence(id);
    }

    @Transactional
    public Customer editCustomer(Long id, EditCustomerRequest newCustomer) {
        Customer oldCustomer = verifyCustomerExistence(id);
        oldCustomer.setEmail(newCustomer.getEmail());
        oldCustomer.setName(newCustomer.getName());
        customerRepository.save(oldCustomer);
        return oldCustomer;
    }

    @Transactional
    public Customer changeCustomerPwd(Long id, PasswordCustomerRequest newPwd) {
        Customer customer = verifyCustomerExistence(id);
        customer.setPwd(newPwd.getPwd());
        customerRepository.save(customer);
        return customer;
    }

    @Transactional
    public void deleteCustomer(Long id) {
        Customer customer = verifyCustomerExistence(id);
        customer.setEmail("INVALID_"+customer.getEmail());
        orderService.cleanOpenedOrder(customer);
        customerRepository.save(customer);
    }

    public List<Order> getCustomerOrders(Long id) {
        verifyCustomerExistence(id);
        return orderService.getCustomerOrders(id);
    }

    @Transactional
    public Order addItemToOrder(Long id, Item item) {
        Customer customer = verifyCustomerExistence(id);
        Order order = orderService.addItemToOrder(customer, item);
        customerRepository.save(customer);
        return order;
    }

    public Item setItem(ItemRequest request) {
        Product prod = productService.getProductById(request.getProductId());
        Integer quantity = request.getQuantity();
        return new Item(prod, quantity, quantity * prod.getPrice());
    }

    public List<Item> getItemsByOrder(Order order) {
        return itemService.getItemsByOrder(order);
    }

    public List<OrderResponse> getItemsByListOrder(List<Order> orders) {
        List<OrderResponse> response = new ArrayList<>();
        orders.forEach(temp -> response.add(OrderResponse.toResponse(temp, getItemsByOrder(temp))));
        return response;
    }

    public Order removeItemFromOrder(Long c_id, Long i_id) {
        Customer customer = verifyCustomerExistence(c_id);
        return orderService.removeItemFromOrder(i_id, customer);
    }

    public OrderResponse editItem(Long c_id, Long i_id, Item newItem) {
        Customer customer = verifyCustomerExistence(c_id);
        Order order = orderService.editItem(customer, i_id, newItem);
        List<Item> items = getItemsByOrder(order);
        return OrderResponse.toResponse(order, items);
    }

    public Order cleanOpenedOrder(Long id) {
        Customer customer = verifyCustomerExistence(id);
        Order order = orderService.cleanOpenedOrder(customer);
        return order;
    }

    public Order buyOrder(Long id) {
        Customer customer = verifyCustomerExistence(id);
        Order order = orderService.buyOrder(customer);
        return order;
    }
}
