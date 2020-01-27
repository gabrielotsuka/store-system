package com.br.github.gabrielotsuka.storesystem.controllers.request.item;

import com.br.github.gabrielotsuka.storesystem.models.Item;

public class ItemRequest {
    private Long productId;
    private int quantity;

    public Item toItem(){
        return new Item(quantity);
    }

    public Long getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }
}
