package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {
    List<User> allUsers();

    User getUser(long n);

    void saveUser(User user);

    void deleteUser(long id);
}