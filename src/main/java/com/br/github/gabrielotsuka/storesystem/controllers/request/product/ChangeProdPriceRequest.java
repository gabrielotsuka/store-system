package com.br.github.gabrielotsuka.storesystem.controllers.request.product;

import com.br.github.gabrielotsuka.storesystem.models.Product;

import javax.validation.constraints.DecimalMin;

public class ChangeProdPriceRequest {
    @DecimalMin(value = "0.1", message = "Price must be greater than 0")
    private Double price;

    public Product toProduct(){
        return new Product(price);
    }

    public Double getPrice() {
        return price;
    }
}
