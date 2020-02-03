package br.com.github.gabrielotsuka.storesystem.controllers.request.admin;

import br.com.github.gabrielotsuka.storesystem.models.Admin;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class SaveAdminRequest {
    @NotBlank
    private String name;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String pwd;

    public Admin toAdmin(){
        return new Admin(name,email,pwd);
    }

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
