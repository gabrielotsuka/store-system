package com.br.github.gabrielotsuka.storesystem.controllers.request.item;

import com.br.github.gabrielotsuka.storesystem.models.Item;
import com.br.github.gabrielotsuka.storesystem.models.Product;

public class ItemRequest {
    private Long productId;
    private Integer quantity;

    public Item toItem(Product product){
        return new Item(product, quantity);
    }

    public Long getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }
}
