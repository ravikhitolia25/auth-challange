package com.auth.security.challange.service;

import com.auth.security.challange.aop.TrackExecutionTime;
import com.auth.security.challange.dto.*;
import com.auth.security.challange.entity.Employee;
import com.auth.security.challange.exception.CommonException;
import com.auth.security.challange.repository.EmployeeRepository;
import com.auth.security.challange.utill.ApplicationConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SecurityServiceImpl implements SecurityService {

    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    CustomEmployeeDetailsService customEmployeeDetailsService;

    @Override
    public ResponseDto registerUser(RegistrationDto registrationDto) {
        if (employeeRepository.existsByEmail(registrationDto.getEmail())) {
            throw new CommonException(ApplicationConstants.ERROR_STATUS_CODE, HttpStatus.OK, ApplicationConstants.Email_ALREADY_EXIST);
        }

        Employee employee = employeeRepository.save(Employee.builder()
                .email(registrationDto.getEmail())
                .username(registrationDto.getUsername())
                .createdAt(LocalDateTime.now())
                .password(passwordEncoder.encode(registrationDto.getPassword()))
                .build());

        return new SuccessResponseDto(RegistrationResponseDto
                .builder()
                .email(employee.getEmail())
                .username(employee.getUsername())
                .build());
    }

    @Override
    @TrackExecutionTime
    public ResponseDto userLogin(LoginDTO loginDTO) {
        Employee existingUser = employeeRepository.findByEmail(loginDTO.email());
        if (existingUser == null) {
            return new ErrorResponseDto(ApplicationConstants.HTTP_RESPONSE_ERROR_CODE,
                    ApplicationConstants.HTTP_RESPONSE_ERROR_CODE_NOT_FOUND_MSG);
        } else if (!passwordEncoder.matches(loginDTO.password(), customEmployeeDetailsService.loadUserByUsername(loginDTO.email()).getPassword())) {
            return new ErrorResponseDto(ApplicationConstants.HTTP_RESPONSE_ERROR_CODE,
                    ApplicationConstants.PASSWORD_MISMATCH);
        }else{
            return new SuccessResponseDto(LoginResponseDto.builder()
                    .email(existingUser.getEmail())
                    .password(existingUser.getPassword())
                    .username(existingUser.getUsername())
                    .build());
        }
    }

    @Override
    public Employee fetchUserByEmail(String email) {
        return employeeRepository.findByEmail(email);
    }


}
