package com.auth.security.challange.service;

import com.auth.security.challange.dto.LoginDTO;
import com.auth.security.challange.dto.RegistrationDto;
import com.auth.security.challange.dto.ResponseDto;
import com.auth.security.challange.entity.Employee;


public interface SecurityService {

    ResponseDto registerUser(RegistrationDto registrationDto);

    ResponseDto userLogin(LoginDTO loginDTO);

    Employee fetchUserByEmail(String email);
}
