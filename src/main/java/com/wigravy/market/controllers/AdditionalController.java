package com.wigravy.market.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdditionalController {
    @GetMapping("/about")
    public String aboutPage() {
        return "about";
    }

    @GetMapping("/")
    public String homepage() {
        return "index";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login_page";
    }

}