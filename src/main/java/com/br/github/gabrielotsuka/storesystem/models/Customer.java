package com.br.github.gabrielotsuka.storesystem.models;

import javax.persistence.*;
import java.util.List;

@Table(
        name = "customer",
        uniqueConstraints = {@UniqueConstraint(columnNames = "email")}
)
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "customerId")
    private Long id;
    private String name;
    private String email;
    private String pwd;
    @OneToMany
    @JoinColumn(name = "customerId", referencedColumnName = "customerId")
    private List<Product> cart;

    public Customer(String name, String email, String pwd) {
        this.name = name;
        this.email = email;
        this.pwd = pwd;
    }

    public Customer(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Customer() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public List<Product> getCart() {
        return cart;
    }

    public void setCart(List<Product> cart) {
        this.cart = cart;
    }
}
