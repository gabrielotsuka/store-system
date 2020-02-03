package br.com.github.gabrielotsuka.storesystem.controllers.request.customer;

import javax.validation.constraints.NotBlank;

public class PasswordCustomerRequest {
    @NotBlank
    private String pwd;

    public String getPwd() {
        return pwd;
    }
}
