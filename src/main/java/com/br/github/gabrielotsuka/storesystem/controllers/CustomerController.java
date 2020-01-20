package com.br.github.gabrielotsuka.storesystem.controllers;

import com.br.github.gabrielotsuka.storesystem.controllers.request.CustomerRequest;
import com.br.github.gabrielotsuka.storesystem.controllers.request.PasswordRequest;
import com.br.github.gabrielotsuka.storesystem.controllers.response.CustomerResponse;
import com.br.github.gabrielotsuka.storesystem.models.Product;
import com.br.github.gabrielotsuka.storesystem.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController

public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping(value = "/admin/customer")
    public ResponseEntity<CustomerResponse> save(@RequestBody @Valid CustomerRequest customer){
        return customerService.save(customer.toCustomer());
    }

    @GetMapping(value = "/admin/customer")
    public List<CustomerResponse> getCustomers(){
        return customerService.getCustomers();
    }

    @GetMapping(value = "/admin/customer/{id}")
    public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable(value = "id") Long id){
        return customerService.getCustomerById(id);
    }

    @PutMapping(value = "/admin/customer/{id}")
    public ResponseEntity<CustomerResponse> editCustomer(@PathVariable(value = "id") Long id,
                                                       @RequestBody @Valid CustomerRequest newCustomer){
        return customerService.editCustomer(id, newCustomer);
    }

    @PutMapping(value = "/admin/customer/{id}/changePwd")
    public ResponseEntity<CustomerResponse> changeCustomerPwd(@PathVariable(value = "id") Long id,
                                                              @RequestBody @Valid PasswordRequest newPwd){
        return customerService.changeCustomerPwd(id, newPwd);
    }

    @DeleteMapping(value = "/admin/customer/{id}")
    public ResponseEntity<Object> deleteCustomer(@PathVariable(value = "id") Long id){
        return customerService.deleteCustomer(id);
    }

    @GetMapping(value = "/customer/{id}/cart/list")
    public List<Product> describeCart(@PathVariable(value = "id") Long id){
        return customerService.describeCart(id);
    }

}
