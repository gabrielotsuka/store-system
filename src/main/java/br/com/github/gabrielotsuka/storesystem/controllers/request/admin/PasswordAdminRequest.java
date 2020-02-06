package br.com.github.gabrielotsuka.storesystem.controllers.request.admin;

import br.com.github.gabrielotsuka.storesystem.models.Admin;

import javax.validation.constraints.NotBlank;

public class PasswordAdminRequest {
    @NotBlank
    private String pwd;

    public Admin toAdmin(){return new Admin(pwd);}

    public String getPwd() {
        return pwd;
    }
}
