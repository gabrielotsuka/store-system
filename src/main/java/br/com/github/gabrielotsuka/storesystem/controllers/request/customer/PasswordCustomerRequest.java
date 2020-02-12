package br.com.github.gabrielotsuka.storesystem.controllers.request.customer;

import br.com.github.gabrielotsuka.storesystem.models.Customer;

import javax.validation.constraints.NotBlank;

public class PasswordCustomerRequest {
    @NotBlank
    private String pwd;

    public String getPwd() {
        return pwd;
    }

    public Customer toCustomer() {
        return new Customer(pwd);
    }
}
