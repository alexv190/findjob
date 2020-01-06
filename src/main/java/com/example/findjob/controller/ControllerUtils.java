package com.example.findjob.controller;

import com.example.findjob.domain.dto.CaptchaResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ControllerUtils {

    @Component
    public class CaptchaConst {
        @Value("${recaptcha.secret}")
        private String recaptchaSecret;

        @Value("${captcha.url}")
        private String captchaUrl;

        @Autowired
        private RestTemplate restTemplate;
    }

    private static CaptchaConst captchaConst;

    @Autowired
    public void setCaptchaConst(CaptchaConst captchaConst) {
        ControllerUtils.captchaConst = captchaConst;
    }

    static Map<String, String> getErrors(BindingResult bindingResult) {
        return bindingResult.getFieldErrors().stream().collect(Collectors.toMap(fieldError -> fieldError.getField() + "Error", FieldError::getDefaultMessage));
    }

    static boolean checkIfCaptchaOk(String captchaResponse) {
        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
        map.add("secret", captchaConst.recaptchaSecret);
        map.add("response", captchaResponse);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map,  new HttpHeaders());
        CaptchaResponseDto captchaResponseDto = captchaConst.restTemplate.postForObject(captchaConst.captchaUrl, request, CaptchaResponseDto.class);

        return captchaResponseDto.isSuccess();
    }
}
