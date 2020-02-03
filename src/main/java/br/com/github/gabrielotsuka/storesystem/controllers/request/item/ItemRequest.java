package br.com.github.gabrielotsuka.storesystem.controllers.request.item;

import br.com.github.gabrielotsuka.storesystem.models.Item;
import br.com.github.gabrielotsuka.storesystem.models.Product;
import org.hibernate.validator.constraints.Range;

public class ItemRequest {
    private Long productId;
    @Range(min = 1)
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
