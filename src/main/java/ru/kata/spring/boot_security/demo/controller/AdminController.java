package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.*;
import ru.kata.spring.boot_security.demo.service.*;

import java.util.*;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "get_all_users";
    }

    @GetMapping("/newUser")
    public String newUser(@ModelAttribute("user") User user) {
        return "new_user";
    }

    @PostMapping("/createUser")
    public String createUser(@ModelAttribute("user") User user,
                             @RequestParam ArrayList<String> listRoleId) {
        Set<Role> userRole = new HashSet<>();
        for (String roleId : listRoleId) {
            Role role = roleService.getById(Long.parseLong(roleId));
            userRole.add(role);
        }
        user.setRoles(userRole);
        userService.create(user);
        return "redirect:/admin";
    }

    @GetMapping("/editUser/{id}")
    public String editUser(@PathVariable int id, Model model) {
        model.addAttribute("user", userService.getById(id));
        return "edit_user";
    }

    @PostMapping("/updateUser/{id}")
    public String updateUser(@ModelAttribute("user") User user) {
        userService.update(user);
        return "redirect:/admin";
    }

    @PostMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable long id) {
        userService.delete(id);
        return "redirect:/admin";
    }

    @GetMapping("/{id}")
    public String getById(@PathVariable long id, Model model) {
        model.addAttribute("user", userService.getById(id));
        return "/admin";
    }
}