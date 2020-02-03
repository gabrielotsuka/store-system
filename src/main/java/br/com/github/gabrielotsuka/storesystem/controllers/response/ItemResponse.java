package br.com.github.gabrielotsuka.storesystem.controllers.response;

import br.com.github.gabrielotsuka.storesystem.models.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemResponse {
    private Long id;
    private Integer quantity;
    private String productName;
    private Double price;
    private double itemPrice;

    public ItemResponse() {
        super();
    }

    public ItemResponse(Long id, Integer quantity, String productName, Double price, double itemPrice) {
        this.id = id;
        this.quantity = quantity;
        this.productName = productName;
        this.price = price;
        this.itemPrice = itemPrice;
    }

    public static ItemResponse toResponse(Item item) {
        return new ItemResponse(item.getId(),
                item.getQuantity(),
                item.getProduct().getName(),
                item.getProduct().getPrice(),
                item.getItemPrice());
    }

    public static List<ItemResponse> toListResponse(List<Item> items){
        List<ItemResponse> response = new ArrayList<>();
        items.forEach(temp -> response.add(ItemResponse.toResponse(temp)));
        return response;
    }

    public Long getId() {
        return id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public String getProductName() {
        return productName;
    }

    public Double getPrice() {
        return price;
    }

    public double getItemPrice() {
        return itemPrice;
    }
}
