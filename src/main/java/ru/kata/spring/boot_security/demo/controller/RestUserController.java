package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

import java.security.Principal;

@RestController
@RequestMapping("/api/user")
public class RestUserController {

    private final UserServiceImpl userServiceImpl;
    @Autowired
    public RestUserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }
    @GetMapping
    public ResponseEntity<User> userPage(Principal principal) {
        User user = (User) userServiceImpl.loadUserByUsername(principal.getName());
        return ResponseEntity.ok(user);
    }
}
