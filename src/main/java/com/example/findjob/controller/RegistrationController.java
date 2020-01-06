package com.example.findjob.controller;

import com.example.findjob.domain.User;
import com.example.findjob.domain.dto.CaptchaResponseDto;
import com.example.findjob.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registrationPage() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@RequestParam String passwordConfirm,
                @RequestParam("g-recaptcha-response") String captchaResponse,
                @Valid User user,
                          BindingResult bindingResult,
                          Model model, @RequestParam Map<String,
            String> form) {

        boolean captchaOk = ControllerUtils.checkIfCaptchaOk(captchaResponse);

        if (!captchaOk) {
            model.addAttribute("captchaError","Заполните капчу!");
        }

        if (!StringUtils.equals(user.getPassword(), passwordConfirm)) {
            model.addAttribute("passwordConfirmError","Пароли не совпадают");
        }

        if (bindingResult.hasErrors() || !captchaOk || !StringUtils.equals(user.getPassword(), passwordConfirm)) {
            model.mergeAttributes(ControllerUtils.getErrors(bindingResult));
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
            model.addAttribute("messageType", "alert-success");
            model.addAttribute("message", "Пользователь активирован");
        }
        return "login";
    }
}
