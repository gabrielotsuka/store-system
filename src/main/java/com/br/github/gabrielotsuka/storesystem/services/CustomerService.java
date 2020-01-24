package com.br.github.gabrielotsuka.storesystem.services;

import com.br.github.gabrielotsuka.storesystem.controllers.request.customer.EditCustomerRequest;
import com.br.github.gabrielotsuka.storesystem.controllers.request.customer.PasswordCustomerRequest;
import com.br.github.gabrielotsuka.storesystem.error.ResourceNotFoundException;
import com.br.github.gabrielotsuka.storesystem.models.Customer;
import com.br.github.gabrielotsuka.storesystem.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    private Customer verifyCustomerExistence(Long id){
        Optional<Customer> customer = customerRepository.findById(id);
        if(!customer.isPresent())
            throw new ResourceNotFoundException("Customer not found. ID: " + id);
        else
            return customer.get();
    }

    public Customer saveCustomer(Customer customer) {
        customerRepository.save(customer);
        return customer;
    }

    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Long id) {
        Customer customer = verifyCustomerExistence(id);
        return customer;
    }

    public Customer editCustomer(Long id, EditCustomerRequest newCustomer) {
        Customer oldCustomer = verifyCustomerExistence(id);
        oldCustomer.setEmail(newCustomer.getEmail());
        oldCustomer.setName(newCustomer.getName());
        customerRepository.save(oldCustomer);
        return oldCustomer;
    }

    public Customer changeCustomerPwd(Long id, PasswordCustomerRequest newPwd) {
        Customer customer = verifyCustomerExistence(id);
        customer.setPwd(newPwd.getPwd());
        customerRepository.save(customer);
        return customer;
    }

    public void deleteCustomer(Long id) {
        Customer customer = verifyCustomerExistence(id);
        customerRepository.delete(customer);
    }
}
