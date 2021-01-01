package com.wigravy.market.controllers;

import com.wigravy.market.Components.Cart;
import com.wigravy.market.entities.Order;
import com.wigravy.market.entities.User;
import com.wigravy.market.exceptions.MarketException;
import com.wigravy.market.services.OrdersService;
import com.wigravy.market.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping("api/v1/order")
@AllArgsConstructor
public class OderController {
    private UserService userService;
    private OrdersService ordersService;
    private Cart cart;

    @PostMapping("/confirm")
    public ResponseEntity<?> confirmOrder(Principal principal, @RequestParam String address) {
        Optional<User> optionalUser = userService.findByPhone(principal.getName());
        if (optionalUser.isEmpty()) {
            return new ResponseEntity<>(new MarketException(HttpStatus.UNAUTHORIZED.value(), "Error. Try to login again."), HttpStatus.UNAUTHORIZED);
        }
        User user = optionalUser.get();
        Order order = new Order(user, cart, user.getPhone(), address);
        return new ResponseEntity<>(ordersService.saveOrder(order), HttpStatus.CREATED);
    }
}
