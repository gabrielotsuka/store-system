package com.br.github.gabrielotsuka.storesystem.controllers.response;

import com.br.github.gabrielotsuka.storesystem.models.Customer;

public class CustomerResponse {
    private Long id;
    private String name;
    private String email;

    public CustomerResponse(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public CustomerResponse() {
    }

    public static CustomerResponse toResponse(Customer customer) {
        return new CustomerResponse(customer.getId(), customer.getName(), customer.getEmail());
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}