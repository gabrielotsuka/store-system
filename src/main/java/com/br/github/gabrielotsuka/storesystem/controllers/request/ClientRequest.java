package com.br.github.gabrielotsuka.storesystem.controllers.request;


import com.br.github.gabrielotsuka.storesystem.models.Client;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class ClientRequest {
    @NotBlank
    private String name;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String pwd;

    public Client toClient(){return new Client(name, email, pwd);}

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
