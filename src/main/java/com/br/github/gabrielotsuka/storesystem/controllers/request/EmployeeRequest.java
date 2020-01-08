package com.br.github.gabrielotsuka.storesystem.controllers.request;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class EmployeeRequest {
    @NotNull
    @Email
    private String email;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date init_date;
    @NotNull
    private String name;

    public EmployeeRequest() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getInit_date() {
        return init_date;
    }

    public void setInit_date(Date init_date) {
        this.init_date = init_date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
