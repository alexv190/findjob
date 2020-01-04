package com.example.findjob.controller;

import com.example.findjob.domain.Role;
import com.example.findjob.domain.User;
import com.example.findjob.repo.UserRepo;
import com.example.findjob.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/userList")
    public String userList(Model model) {
        Iterable<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "user/userList";
    }

    @GetMapping("/user/{user_id}")
    public String userProfile(@PathVariable(name = "user_id") User user, Model model) {
        model.addAttribute("user", user);
        return "user/userPage";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/user/{user_id}/edit")
    public String editUser(@PathVariable(name = "user_id") User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "user/userEdit";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/user/{user_id}/edit")
    public String updateUser(@PathVariable(name = "user_id") User user, @RequestParam Map<String, String> form, Model model) {
        userService.updateUser(user, form);
        return "redirect:/user/" + user.getId() + "/edit";
    }

}
