package ru.kata.spring.boot_security.demo.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping()
    public String homeUser(Model model) {
        model.addAttribute("user", SecurityContextHolder.getContext().
                getAuthentication().getPrincipal());
        return "user";
    }
}

