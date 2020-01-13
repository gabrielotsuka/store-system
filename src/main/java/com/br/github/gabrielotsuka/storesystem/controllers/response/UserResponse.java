package com.br.github.gabrielotsuka.storesystem.controllers.response;

import com.br.github.gabrielotsuka.storesystem.models.User;

public class UserResponse {
    private Long id;
    private String name;
    private String email;

    public UserResponse(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public static UserResponse toResponse(User user){
        return new UserResponse(user.getId(), user.getName(), user.getEmail());
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
