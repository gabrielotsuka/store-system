package com.br.github.gabrielotsuka.storesystem.models;

import javax.persistence.*;
import java.util.List;

@Table(
        name = "client",
        uniqueConstraints = {@UniqueConstraint(columnNames = "email")}
)
@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "clientId")
    private Long id;
    private String name;
    private String email;
    private String pwd;
    @OneToMany
    @JoinColumn(name = "clientId", referencedColumnName = "clientId")
    private List<Product> cart;

    public Client(String name, String email, String pwd) {
        this.name = name;
        this.email = email;
        this.pwd = pwd;
    }

    public Client() {
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
