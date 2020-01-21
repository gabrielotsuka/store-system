package com.br.github.gabrielotsuka.storesystem.controllers;

import com.br.github.gabrielotsuka.storesystem.controllers.request.customer.EditRequest;
import com.br.github.gabrielotsuka.storesystem.controllers.request.customer.SaveRequest;
import com.br.github.gabrielotsuka.storesystem.controllers.request.customer.PasswordRequest;
import com.br.github.gabrielotsuka.storesystem.controllers.response.CustomerResponse;
import com.br.github.gabrielotsuka.storesystem.models.Customer;
import com.br.github.gabrielotsuka.storesystem.models.Product;
import com.br.github.gabrielotsuka.storesystem.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<CustomerResponse> save(@RequestBody @Valid SaveRequest customer){
        Customer customerRet = customerService.save(customer.toCustomer());
        return new ResponseEntity<CustomerResponse>(CustomerResponse.toResponse(customerRet),HttpStatus.CREATED);
    }

    @GetMapping(value = "/admin/customer")
    public ResponseEntity<List<CustomerResponse>> getCustomers(){
        return new ResponseEntity<List<CustomerResponse>>
                (CustomerResponse.toListResponse(customerService.getCustomers()),
                HttpStatus.OK);
    }

    @GetMapping(value = "/admin/customer/{id}")
    public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable(value = "id") Long id){
        Customer response = customerService.getCustomerById(id);
        return new ResponseEntity<CustomerResponse>(CustomerResponse.toResponse(response), HttpStatus.OK);
    }

    @PutMapping(value = "/admin/customer/{id}")
    public ResponseEntity<CustomerResponse> editCustomer(@PathVariable(value = "id") Long id,
                                                           @RequestBody @Valid EditRequest newCustomer){
        Customer response = customerService.editCustomer(id, newCustomer);
        return new ResponseEntity<CustomerResponse>(CustomerResponse.toResponse(response), HttpStatus.OK);
    }

    @PutMapping(value = "/admin/customer/{id}/changePwd")
    public ResponseEntity<CustomerResponse> changeCustomerPwd(@PathVariable(value = "id") Long id,
                                                              @RequestBody @Valid PasswordRequest newPwd){
        Customer response = customerService.changeCustomerPwd(id, newPwd);
        return new ResponseEntity<CustomerResponse>(CustomerResponse.toResponse(response),  HttpStatus.OK);
    }

    @DeleteMapping(value = "/admin/customer/{id}")
    public ResponseEntity<Object> deleteCustomer(@PathVariable(value = "id") Long id){
        customerService.deleteCustomer(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping(value = "/customer/{id}/cart/list")
    public List<Product> describeCart(@PathVariable(value = "id") Long id){
        return customerService.describeCart(id);
    }

}
