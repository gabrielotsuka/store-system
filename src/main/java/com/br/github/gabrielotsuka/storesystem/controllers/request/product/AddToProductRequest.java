package com.br.github.gabrielotsuka.storesystem.controllers.request.product;

import com.br.github.gabrielotsuka.storesystem.models.Product;

public class AddToProductRequest {
    private Integer quantity;

    public Product toProduct(){
        return new Product(quantity);
    }

    public Integer getQuantity() {
        return quantity;
    }
}
