package com.example.findjob.controller;

import com.example.findjob.domain.Role;
import com.example.findjob.domain.User;
import com.example.findjob.repo.UserRepo;
import com.example.findjob.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registrationPage() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@Valid User user,  BindingResult bindingResult, Model model, @RequestParam Map<String, String> form) {

        if (bindingResult.hasErrors()) {
            model.mergeAttributes(ControllerUtils.getErrors(bindingResult));
            return "registration";
        }
        if (!StringUtils.equals(user.getPassword(), user.getPassword2())) {
            model.addAttribute("password2Error","Пароли не совпадают");
            return "registration";
        }
        String result = userService.addUser(user, form.get("r_role"));
        if (result != null) {//error
            model.addAttribute("message", result);
            return "registration";
        }
        return "redirect:/login";
    }

    @GetMapping("/activate/{activationCode}")
    public String activateUser(@PathVariable String activationCode, Model model) {
        String result = userService.activateUser(activationCode);
        if (result != null) {//error
            model.addAttribute("message", result);
        } else {
            model.addAttribute("messagetype", "alert-success");
            model.addAttribute("message", "Пользователь активирован");
        }
        return "login";
    }
}
