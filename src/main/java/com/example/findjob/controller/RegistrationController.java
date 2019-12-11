package com.example.findjob.controller;

import com.example.findjob.domain.Role;
import com.example.findjob.domain.User;
import com.example.findjob.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
public class RegistrationController {

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/registration")
    public String registrationPage() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, @RequestParam Map<String, String> form, Model model) {
        User existingUser = userRepo.findByUsername(user.getUsername());
        if (existingUser != null) {
            model.addAttribute("message","Пользователь с таким именем уже зарегистрирован");
            return "registration";
        }
        user.setActive(true);
        String str_role = form.get("r_role");
        user.setRoles(Stream.of(Role.getRoleFromString(str_role), Role.USER).collect(Collectors.toSet()));
        userRepo.save(user);
        return "redirect:/login";
    }
}
