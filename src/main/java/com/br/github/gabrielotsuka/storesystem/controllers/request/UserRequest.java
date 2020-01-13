package com.br.github.gabrielotsuka.storesystem.controllers.request;

import com.br.github.gabrielotsuka.storesystem.models.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.Pattern;

public class    UserRequest {
//    @Pattern(regexp = "[A-Z, a-z]", message = "There can't be special chars or numbers in name field")
    @NotBlank(message = "{name.not.blank}") 
    private String name;
    @NotBlank(message = "{email.not.blank}")
    @Email(message = "{email.not.valid}")
    private String email;
    @NotBlank(message = "{pwd.not.blank}")
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
