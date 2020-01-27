package com.br.github.gabrielotsuka.storesystem.controllers;

import com.br.github.gabrielotsuka.storesystem.controllers.request.item.ItemRequest;
import com.br.github.gabrielotsuka.storesystem.controllers.response.OrderResponse;
import com.br.github.gabrielotsuka.storesystem.models.Product;
import com.br.github.gabrielotsuka.storesystem.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
        return new ResponseEntity<>(OrderResponse.toListResponse(customerService.getCustomerOrders(id)), HttpStatus.OK);
    }

    @PostMapping(value = "/{id}/addItem")
    public ResponseEntity<OrderResponse> addItemToOrder(@PathVariable Long id,
                                                        @RequestBody @Valid ItemRequest item){
        return new ResponseEntity<>(OrderResponse.toResponse(customerService.addItemToOrder(id, item.toItem())),
                HttpStatus.OK);
    }
}
