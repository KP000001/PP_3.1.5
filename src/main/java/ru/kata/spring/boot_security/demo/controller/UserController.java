package ru.kata.spring.boot_security.demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping
    public String homeUser(Model model, Authentication authentication) {
        model.addAttribute("user", authentication.getPrincipal());
        return "user";
    }
}

