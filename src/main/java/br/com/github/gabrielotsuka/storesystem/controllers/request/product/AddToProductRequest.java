package br.com.github.gabrielotsuka.storesystem.controllers.request.product;

import br.com.github.gabrielotsuka.storesystem.models.Product;

public class AddToProductRequest {
    private Integer quantity;

    public Product toProduct(){
        return new Product(quantity);
    }

    public Integer getQuantity() {
        return quantity;
    }
}
