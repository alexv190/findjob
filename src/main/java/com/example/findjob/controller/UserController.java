package com.example.findjob.controller;

import com.example.findjob.domain.Role;
import com.example.findjob.domain.User;
import com.example.findjob.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class UserController {

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/users")
    public String userList(Model model) {
        Iterable<User> users = userRepo.findAll();
        model.addAttribute("users", users);
        return "user/userList";
    }

    @GetMapping("/user/{user_id}")
    public String userProfile(@PathVariable(name = "user_id") User user, Model model) {
        model.addAttribute("user", user);
        return "user/userpage";
    }

    @GetMapping("/user/{user_id}/edit")
    public String editUser(@PathVariable(name = "user_id") User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "user/useredit";
    }

    @PostMapping("/user/update/{user_id}")
    public String updateUser(@PathVariable(name = "user_id") User user, @RequestParam Map<String, String> form, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("message", "Обновлено");
        user.getRoles().clear();
        for (String key : form.keySet()) {
            if (Role.getRoleFromString(key) != null) {
                user.getRoles().add(Role.getRoleFromString(key));
            }
        }

        userRepo.save(user);
        return "redirect:/user/" + user.getId() + "/edit";
    }

}
