package com.wigravy.market.controllers;

import com.wigravy.market.entities.User;
import com.wigravy.market.entities.dtos.SystemUser;
import com.wigravy.market.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {
    UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        webDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/register")
    public String registrationPage(Model model) {
        model.addAttribute("systemUser", new SystemUser());
        return "registration_form";
    }

    @PostMapping("/register/process")
    public String registrationProcessing(@ModelAttribute("systemUser") @Validated SystemUser systemUser, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "registration_form";
        }
        if (userService.findByPhone(systemUser.getPhone()).isPresent()) {
            model.addAttribute("registrationError", String.format("User with current phone number [%s] is already exists.", systemUser.getPhone()));
            systemUser.setPhone("");
            model.addAttribute("systemUser", systemUser);
            return "registration_form";
        }
        userService.save(systemUser);
        return "registration_confirmation";
    }
}
