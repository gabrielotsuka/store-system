package com.br.github.gabrielotsuka.storesystem.controllers.request;

import com.br.github.gabrielotsuka.storesystem.models.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class    UserRequest {
    @NotBlank
    private String name;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String pwd;

    public User toUser(){
        return new User(name,email,pwd);
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
