package com.br.github.gabrielotsuka.storesystem.controllers.request.customer;

import com.br.github.gabrielotsuka.storesystem.models.Employee;

import javax.validation.constraints.NotBlank;

public class PasswordRequest {
    @NotBlank
    private String pwd;

    public String getPwd() {
        return pwd;
    }
}
