package com.auth.security.challange.controller;

import com.auth.security.challange.dto.LoginDTO;
import com.auth.security.challange.dto.RegistrationDto;
import com.auth.security.challange.dto.ResponseDto;
import com.auth.security.challange.service.SecurityService;
import com.auth.security.challange.utill.JwtTokenGenration;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class SecurityController {

    @Autowired
    private SecurityService securityService;

    @Autowired
    private JwtTokenGenration jwtTokenGenration;

    @Value("${jwt.secret}")
    private String tokenHeader;
    @Value("${jwt.prefix}")
    private String tokenPrefix;

    @PostMapping("/register")
    public ResponseDto registerUser(@RequestBody RegistrationDto request) {

        return securityService.registerUser(request);
    }

    @PostMapping("/login")
    public ResponseDto login(@RequestBody LoginDTO loginDto, HttpServletResponse response){

        response.addHeader(tokenHeader, tokenPrefix + jwtTokenGenration.generateToken(loginDto.email()));
        return securityService.userLogin(loginDto);
    }


}
