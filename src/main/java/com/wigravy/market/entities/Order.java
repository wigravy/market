package com.wigravy.market.entities;

import com.wigravy.market.components.Cart;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "order")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<OrderItem> items;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "phone_number")
    private String phone;

    @Column(name = "address")
    private String address;

    public Order(User user, Cart cart, String phone, String address) {
        this.user = user;
        this.phone = phone;
        this.address = address;
        this.items = new ArrayList<>();
        for (OrderItem oi : cart.getItems()) {
            oi.setOrder(this);
            this.items.add(oi);
        }
        this.price = new BigDecimal(cart.getPrice().doubleValue());
        cart.clear();
    }
}
