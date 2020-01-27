package com.br.github.gabrielotsuka.storesystem.controllers.response;

import com.br.github.gabrielotsuka.storesystem.models.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderResponse {
    private Long id;
    private double totalPrice;
    private String status;

    public OrderResponse(Long id, double totalPrice, String status) {
        this.id = id;
        this.totalPrice = totalPrice;
        this.status = status;
    }

    public OrderResponse() {
    }

    public static OrderResponse toResponse(Order order){
        return new OrderResponse(order.getId(), order.getTotalPrice(), order.getStatus());
    }

    public static List<OrderResponse> toListResponse(List<Order> orders){
        List<OrderResponse> response = new ArrayList<>();
        orders.forEach(temp -> response.add(OrderResponse.toResponse(temp)));
        return response;
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
}
