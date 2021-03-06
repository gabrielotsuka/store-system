package br.com.github.gabrielotsuka.storesystem.models;

import javax.persistence.*;

@Entity
@Table(
        name = "admin",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})}
)
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String name;
    private String email;
    private String pwd;

    public Admin(String name, String email, String pwd) {
        this.name = name;
        this.email = email;
        this.pwd = pwd;
    }

    public Admin(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Admin(String pwd) {
        this.pwd = pwd;
    }

    public Admin() {
        super();
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

    public String getPwd() {
        return pwd;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

}
