package com.br.github.gabrielotsuka.storesystem.controllers.request.product;

import com.br.github.gabrielotsuka.storesystem.models.Product;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;

public class ProductSavingRequest {
    @NotBlank
    private String name;
    @DecimalMin(value = "0.1", message = "Price must be greater than 0")
    private Double price;
    @Range(min = 1, message = "Quantity must be greater than 0")
    private Integer quantity;

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
