package com.br.github.gabrielotsuka.storesystem.controllers.request.employee;

import com.br.github.gabrielotsuka.storesystem.models.Employee;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class SavingRequest {
    @NotBlank
    private String name;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String pwd;

    public Employee toEmployee(){
        return new Employee(name,email,pwd);
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
