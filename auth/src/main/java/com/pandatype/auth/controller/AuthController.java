package com.pandatype.auth.controller;

import com.pandatype.auth.entities.AuthResponseEntity;
import com.pandatype.auth.entities.UserEntity;
import com.pandatype.auth.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;


@RestController
@RequestMapping(value = "/api/auth")
public class AuthController {
    Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Resource
    private UserService userService;

    @PostMapping(value = "/login")
    public AuthResponseEntity login(@RequestBody final UserEntity userEntity) throws AuthenticationException {
        return userService.login(userEntity);
    }

    @PostMapping(value = "/register")
    public AuthResponseEntity register(@RequestBody @Valid final UserEntity userEntity) {
        return userService.save(userEntity);
    }

    @PostMapping(value = "/message")
    public String message() {
        return "TEST RESTRICTED MESSAGE";
    }

    @PostMapping(value = "/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String admin() {
        return "TEST ADMIN MESSAGE";
    }
}