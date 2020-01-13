package com.br.github.gabrielotsuka.storesystem.controllers.request;

import com.br.github.gabrielotsuka.storesystem.models.User;

//import javax.validation.constraints.Pattern;

public class UserRequest {
//    @Pattern(regexp = "[A-Z, a-z]", message = "")
    private String name;
    private String email;
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
