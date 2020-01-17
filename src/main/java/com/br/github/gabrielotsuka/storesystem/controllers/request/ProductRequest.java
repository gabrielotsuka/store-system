package com.br.github.gabrielotsuka.storesystem.controllers.request;

import com.br.github.gabrielotsuka.storesystem.models.Product;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;

public class ProductRequest {
    @NotBlank
    private String name;
    @DecimalMin("0.0")
    private Double price;
    @Range(min = 0)
    private int quantity;

    public Product toProduct(){
        return new Product(name,price,quantity);
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }
}
