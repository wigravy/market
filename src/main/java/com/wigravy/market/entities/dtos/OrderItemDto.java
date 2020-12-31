package com.wigravy.market.entities.dtos;


import com.wigravy.market.entities.OrderItem;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class OrderItemDto {
    private Long id;
    private BigDecimal price;
    private int quantity;
    private String productTitle;

    public OrderItemDto(OrderItem orderItem) {
        this.id = orderItem.getId();
        this.price = orderItem.getPrice();
        this.quantity = orderItem.getQuantity();
        this.productTitle = orderItem.getProduct().getTitle();
    }
}
