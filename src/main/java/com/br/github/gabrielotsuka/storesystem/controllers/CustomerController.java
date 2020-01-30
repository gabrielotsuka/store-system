package com.br.github.gabrielotsuka.storesystem.controllers;

import com.br.github.gabrielotsuka.storesystem.controllers.request.item.ItemRequest;
import com.br.github.gabrielotsuka.storesystem.controllers.response.OrderResponse;
import com.br.github.gabrielotsuka.storesystem.models.Item;
import com.br.github.gabrielotsuka.storesystem.models.Order;
import com.br.github.gabrielotsuka.storesystem.models.Product;
import com.br.github.gabrielotsuka.storesystem.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/customer")
public class CustomerController {
    @Autowired
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getStock(){
        return new ResponseEntity<>(customerService.getStock(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/getOrders")
    public ResponseEntity<List<OrderResponse>> getCustomerOrders(@PathVariable(value = "id") Long id){
        List<Order> orders = customerService.getCustomerOrders(id);
        return new ResponseEntity<>(customerService.getItemsByListOrder(orders), HttpStatus.OK);
    }

    @PostMapping(value = "/{id}/addItem")
    public ResponseEntity<OrderResponse> addItemToOrder(@PathVariable Long id,
                                                        @RequestBody @Valid ItemRequest request){
        Item item = customerService.setItem(request);
        Order order = customerService.addItemToOrder(id, item);
        List<Item> items = customerService.getItemsByOrder(order);
        return new ResponseEntity<>(OrderResponse.toResponse(order, items), HttpStatus.OK);
    }

    @PutMapping(value = "/{customer_id}/editItem/{item_id}")
    public ResponseEntity<OrderResponse> editItem(@PathVariable(value = "customer_id") Long c_id,
                                                         @PathVariable(value = "item_id") Long i_id,
                                                         @RequestBody @Valid ItemRequest request){
        return new ResponseEntity<>(customerService.editItem(c_id, i_id, customerService.setItem(request)), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{customer_id}/removeItem/{item_id}")
    public ResponseEntity<OrderResponse> removeItemFromOrder(@PathVariable(value = "customer_id") Long c_id,
                                                             @PathVariable(value = "item_id") Long i_id){
        Order order = customerService.removeItemFromOrder(c_id, i_id);
        List<Item> items = customerService.getItemsByOrder(order);
        return new ResponseEntity<>(OrderResponse.toResponse(order, items), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}/cleanOpenedOrder")
    public ResponseEntity<OrderResponse> cleanOpenedOrder(@PathVariable Long id){
        Order order = customerService.cleanOpenedOrder(id);
        return new ResponseEntity<>(OrderResponse.toResponse(order, new ArrayList<>()), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}/buyOrder")
    public ResponseEntity<OrderResponse> buyOrder(@PathVariable Long id){
        Order order = customerService.buyOrder(id);
        List<Item> items = customerService.getItemsByOrder(order);
        return new ResponseEntity<>(OrderResponse.toResponse(order, items), HttpStatus.OK);
    }
}
