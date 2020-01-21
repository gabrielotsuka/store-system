package com.br.github.gabrielotsuka.storesystem.controllers.request.customer;

import com.br.github.gabrielotsuka.storesystem.models.Customer;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class SaveRequest {
    @NotBlank
    private String name;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String pwd;

    public Customer toCustomer(){return new Customer(name, email, pwd);}

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPwd() {
        return pwd;
    }
}
