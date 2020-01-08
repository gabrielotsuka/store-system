package com.br.github.gabrielotsuka.storesystem.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="employee")
public class Employee implements Serializable {

    public Employee(){
        super();
    }

    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date init_date;
    @NotEmpty
    private String email;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Employee(String name, Date init_date, @NotEmpty String email) {
        this.name = name;
        this.init_date = init_date;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getInit_date() {
        return init_date;
    }

    public void setInit_date(Date init_date) {
        this.init_date = init_date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
