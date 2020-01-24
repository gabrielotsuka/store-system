package com.br.github.gabrielotsuka.storesystem.controllers.request.employee;

import com.br.github.gabrielotsuka.storesystem.models.Customer;
import com.br.github.gabrielotsuka.storesystem.models.Employee;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class EditEmployeeRequest {
    @NotBlank
    private String name;
    @NotBlank
    @Email
    private String email;

    public Employee toEmployee(){return new Employee(name, email);}

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
