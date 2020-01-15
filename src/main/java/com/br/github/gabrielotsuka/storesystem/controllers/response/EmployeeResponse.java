package com.br.github.gabrielotsuka.storesystem.controllers.response;

import com.br.github.gabrielotsuka.storesystem.models.Employee;

public class EmployeeResponse {
    private Long id;
    private String name;
    private String email;

    public EmployeeResponse(){}

    public EmployeeResponse(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public static EmployeeResponse toResponse(Employee employee){
        return new EmployeeResponse(employee.getId(), employee.getName(), employee.getEmail());
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
