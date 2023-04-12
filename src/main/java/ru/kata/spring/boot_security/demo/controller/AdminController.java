package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.*;
import ru.kata.spring.boot_security.demo.service.*;

import java.util.*;

@Controller
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @RequestMapping(value = "/admin")
    public String getAllUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "get_all_users";
    }

    @GetMapping(value = "/admin/newUser")
    public String newUser(@ModelAttribute("user") User user) {
        return "new_user";
    }

    @PostMapping(value = "/admin/createUser")
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

    @GetMapping(value = "/admin/editUser/{id}")
    public String editUser(@PathVariable("id") int id, Model model) {
        User user = userService.getById(id);
        model.addAttribute("user", user);
        return "edit_user";
    }

    @PostMapping(value = "/admin/updateUser/{id}")
    public String updateUser(@ModelAttribute("user") User user) {
        userService.update(user);
        return "redirect:/admin";
    }

    @PostMapping(value = "/admin/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.delete(id);
        return "redirect:/admin";
    }

    @GetMapping(value = "/admin/{id}")
    public String getById(@PathVariable("id") long id, Model model) {
        User user = userService.getById(id);
        model.addAttribute("user", user);
        return "admin";
    }
}
