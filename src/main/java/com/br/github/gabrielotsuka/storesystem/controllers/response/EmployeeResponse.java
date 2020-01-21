package com.br.github.gabrielotsuka.storesystem.controllers.response;

import com.br.github.gabrielotsuka.storesystem.models.Employee;

import java.util.ArrayList;
import java.util.List;

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

    public static List<EmployeeResponse> toListResponse(List<Employee> employees){
        List<EmployeeResponse> response = new ArrayList<>();
        employees.forEach(temp -> response.add(EmployeeResponse.toResponse(temp)));
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
