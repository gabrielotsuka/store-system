package com.br.github.gabrielotsuka.storesystem.services;

import com.br.github.gabrielotsuka.storesystem.controllers.request.CustomerRequest;
import com.br.github.gabrielotsuka.storesystem.controllers.request.PasswordRequest;
import com.br.github.gabrielotsuka.storesystem.controllers.response.CustomerResponse;
import com.br.github.gabrielotsuka.storesystem.error.ResourceNotFoundException;
import com.br.github.gabrielotsuka.storesystem.models.Customer;
import com.br.github.gabrielotsuka.storesystem.models.Product;
import com.br.github.gabrielotsuka.storesystem.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public ResponseEntity<CustomerResponse> save(Customer customer) {
        customerRepository.save(customer);
        return new ResponseEntity<CustomerResponse>(CustomerResponse.toResponse(customer), HttpStatus.CREATED);
    }

    public List<CustomerResponse> getCustomers() {
        List<Customer> arr = customerRepository.findAll();
        List<CustomerResponse> response = new ArrayList<>();
        arr.forEach(temp -> response.add(CustomerResponse.toResponse(temp)));
        return response;
    }

    public ResponseEntity<CustomerResponse> getCustomerById(Long id) {
        Customer customer = verifyCustomerExistence(id);
        return new ResponseEntity<CustomerResponse>(CustomerResponse.toResponse(customer), HttpStatus.OK);
    }

    public ResponseEntity<CustomerResponse> editCustomer(Long id, CustomerRequest newCustomer) {
        Customer oldCustomer = verifyCustomerExistence(id);
        oldCustomer.setEmail(newCustomer.getEmail());
        oldCustomer.setName(newCustomer.getName());
        customerRepository.save(oldCustomer);
        return new ResponseEntity<CustomerResponse>(CustomerResponse.toResponse(oldCustomer), HttpStatus.OK);
    }

    public ResponseEntity<CustomerResponse> changeCustomerPwd(Long id, PasswordRequest newPwd) {
        Customer customer = verifyCustomerExistence(id);
        customer.setPwd(newPwd.getPwd());
        customerRepository.save(customer);
        return new ResponseEntity<CustomerResponse>(CustomerResponse.toResponse(customer), HttpStatus.OK);
    }

    public ResponseEntity<Object> deleteCustomer(Long id) {
        Customer customer = verifyCustomerExistence(id);
        customerRepository.delete(customer);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public List<Product> describeCart(Long id) {
        Customer customer = verifyCustomerExistence(id);
        return customer.getCart();
    }

    private Customer verifyCustomerExistence(Long id){
        Optional<Customer> customer = customerRepository.findById(id);
        if(!customer.isPresent())
            throw new ResourceNotFoundException("Customer not found. ID: " + id);
        else
            return customer.get();
    }
}
