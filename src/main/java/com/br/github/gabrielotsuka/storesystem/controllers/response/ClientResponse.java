package com.br.github.gabrielotsuka.storesystem.controllers.response;

import com.br.github.gabrielotsuka.storesystem.models.Client;
import org.springframework.http.HttpStatus;

public class ClientResponse {
    private Long id;
    private String name;
    private String email;

    public ClientResponse(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public ClientResponse() {
    }

    public static ClientResponse toResponse(Client client) {
        return new ClientResponse(client.getId(),client.getName(),client.getEmail());
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
