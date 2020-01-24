package com.br.github.gabrielotsuka.storesystem.controllers.request.admin;

import com.br.github.gabrielotsuka.storesystem.models.Admin;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class EditAdminRequest {
    @NotBlank
    private String name;
    @NotBlank
    @Email
    private String email;

    public Admin toAdmin(){return new Admin(name, email);}

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
