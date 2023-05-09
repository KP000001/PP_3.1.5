package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class RestAdminController {
    private final UserServiceImpl userServiceImpl;

    @Autowired
    public RestAdminController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping(value = "/allUsers")
    public ResponseEntity<List<User>> allUsers() {
        return ResponseEntity.ok(userServiceImpl.allUsers());
    }

    @GetMapping(value = "/getUser/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") long id) {
        return ResponseEntity.ok(userServiceImpl.getUser(id));
    }

    @PostMapping(value = "/saveUser")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        userServiceImpl.saveUser(user);
        return ResponseEntity.ok(user);
    }

    @PutMapping(value = "/updateUser/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        userServiceImpl.saveUser(user);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping(value = "/deleteUser/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable(name = "id") int id) {
        userServiceImpl.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}