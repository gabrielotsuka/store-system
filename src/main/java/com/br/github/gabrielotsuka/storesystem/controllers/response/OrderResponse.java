package com.br.github.gabrielotsuka.storesystem.controllers.response;

import com.br.github.gabrielotsuka.storesystem.models.Item;
import com.br.github.gabrielotsuka.storesystem.models.Order;

import java.util.List;

public class OrderResponse {
    private Long id;
    private double totalPrice;
    private String status;
    private List<ItemResponse> items;

    public OrderResponse(Long id, double totalPrice, String status, List<ItemResponse> items) {
        this.id = id;
        this.totalPrice = totalPrice;
        this.status = status;
        this.items = items;
    }

    public OrderResponse() {
        super();
    }

    public static OrderResponse toResponse(Order order, List<Item> items) {
        return new OrderResponse(order.getId(),
                order.getTotalPrice(),
                order.getStatus(),
                ItemResponse.toListResponse(items));
    }

    public Long getId() {
        return id;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public List<ItemResponse> getItems() {
        return items;
    }
}
