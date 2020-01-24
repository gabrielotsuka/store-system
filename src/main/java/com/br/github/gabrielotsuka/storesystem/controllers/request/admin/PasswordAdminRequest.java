package com.br.github.gabrielotsuka.storesystem.controllers.request.admin;

import javax.validation.constraints.NotBlank;

public class PasswordAdminRequest {
    @NotBlank
    private String pwd;

    public String getPwd() {
        return pwd;
    }
}
