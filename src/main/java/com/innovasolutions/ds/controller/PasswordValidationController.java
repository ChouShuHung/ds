package com.innovasolutions.ds.controller;


import com.innovasolutions.ds.service.security.password.IPasswordValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Set;


@RestController
public class PasswordValidationController {
    private static final String WARNING_INCORRECT_CONTENT = "Please enter the correct key \"password\" and value";

    @Autowired
    IPasswordValidationService pwdValidationService;

    @RequestMapping(value = "/validatePassword", method = RequestMethod.POST)
    public ResponseEntity<Object> validatePassword(@RequestBody Map<String, Object> jsonText) {
        String password = (String) jsonText.entrySet().stream().filter(j -> ("password").equals(j.getKey()))
                .map(Map.Entry::getValue)
                .findFirst()
                .orElse("");

        if (StringUtils.isEmpty(password)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(WARNING_INCORRECT_CONTENT);
        } else {
            Set<String> result = pwdValidationService.validatePassword(password);
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }
    }
}
