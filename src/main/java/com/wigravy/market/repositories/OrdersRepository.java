package com.wigravy.market.repositories;

import com.wigravy.market.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<Order, Long> {
}
