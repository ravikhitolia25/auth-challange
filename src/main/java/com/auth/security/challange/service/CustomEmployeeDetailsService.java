package com.auth.security.challange.service;


import com.auth.security.challange.config.SecurityUserDetails;
import com.auth.security.challange.entity.Employee;
import com.auth.security.challange.repository.EmployeeRepository;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



@Service
public class CustomEmployeeDetailsService implements UserDetailsService {

    private EmployeeRepository employeeRepository;

    public CustomEmployeeDetailsService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Employee employee = employeeRepository.findByEmail(email);
       return new SecurityUserDetails(employee);
    }
}
