package com.wigravy.market.controllers;

import com.wigravy.market.components.Cart;
import com.wigravy.market.services.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cart")
public class CartController {
    private ProductsService productService;
    private Cart cart;

    @Autowired
    public CartController(ProductsService productService, Cart cart) {
        this.productService = productService;
        this.cart = cart;
    }

    @GetMapping
    public String showCart(Model model) {
        model.addAttribute("products", cart.getProductsList());
        return "cart";
    }


    @GetMapping("add/{id}")
    public String addProductToCart(@PathVariable Long id) {
        cart.addProduct(productService.findById(id));
        return "redirect:/products";
    }

    @GetMapping("del/{id}")
    public String deleteProductFromCart(@PathVariable Long id) {
        cart.deleteProduct(productService.findById(id));
        return "redirect:/cart";
    }
}