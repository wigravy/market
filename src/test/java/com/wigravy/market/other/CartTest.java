package com.wigravy.market.other;

import com.wigravy.market.Components.Cart;
import com.wigravy.market.entities.OrderItem;
import com.wigravy.market.entities.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest(classes = Cart.class)
public class CartTest {
    @Autowired
    private Cart cart;

    @BeforeEach
    public void init() {
        for (int item = 0; item < 10; item++) {
            Product product = new Product((long) item, "Test item #" + item, new BigDecimal(100));
            cart.add(product);
        }
    }

    @Test
    public void cartFillingTest() {
        Assertions.assertEquals(cart.getItems().size(), 10);
    }

    @Test
    public void cartRightPriceTest() {
        Assertions.assertEquals(cart.getPrice(), new BigDecimal(1000));
    }

    @Test
    public void cartRemoveItemTest() {
        Long removeItemId = 1L;
        cart.removeByProductId(removeItemId);
        Assertions.assertEquals(cart.getItems().size(), 9);
        for (OrderItem orderItem : cart.getItems()) {
            if (orderItem.getProduct().getId().equals(removeItemId)) {
                Assertions.fail("The product with id 1 should not be on the list!");
            }
        }
    }
}
