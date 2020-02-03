package br.com.github.gabrielotsuka.storesystem.controllers.response;

import br.com.github.gabrielotsuka.storesystem.models.Admin;

import java.util.ArrayList;
import java.util.List;

public class AdminResponse {
    private Long id;
    private String name;
    private String email;

    public AdminResponse(){}

    public AdminResponse(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public static AdminResponse toResponse(Admin admin){
        return new AdminResponse(admin.getId(), admin.getName(), admin.getEmail());
    }

    public static List<AdminResponse> toListResponse(List<Admin> admins){
        List<AdminResponse> response = new ArrayList<>();
        admins.forEach(temp -> response.add(AdminResponse.toResponse(temp)));
        return response;
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
