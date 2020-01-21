package com.br.github.gabrielotsuka.storesystem.controllers.request.employee;

import javax.validation.constraints.NotBlank;

public class PasswordRequest {
    @NotBlank
    private String pwd;

    public String getPwd() {
        return pwd;
    }
}
