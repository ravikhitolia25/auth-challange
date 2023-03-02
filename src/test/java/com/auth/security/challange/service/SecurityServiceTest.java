package com.auth.security.challange.service;//package com.auth.security.challange.service;

import com.auth.security.challange.dto.LoginDTO;
import com.auth.security.challange.dto.RegistrationDto;
import com.auth.security.challange.dto.ResponseDto;
import com.auth.security.challange.entity.Employee;
import com.auth.security.challange.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SecurityServiceTest {
    @InjectMocks
    private SecurityServiceImpl securityServiceImpl;
    @Mock
    EmployeeRepository employeeRepository;

    @Test
    void should_register_new_employee(){
        Employee registerUser= testEmployee();
        when((employeeRepository.findByEmail(Mockito.any()))).thenReturn(registerUser);
        Employee employee= securityServiceImpl.fetchUserByEmail(registerUser.getEmail());
        assertThat(employee).isEqualTo(employee);
    }

    @Test
    void should_login_existing_employee(){
        Employee registerUser= testEmployee();
        when((employeeRepository.findByEmail(Mockito.any()))).thenReturn(registerUser);
         ResponseDto response=  securityServiceImpl.userLogin(testLoginDTO());
        assertThat(200).isEqualTo(response.getCode());

    }

    private static LoginDTO testLoginDTO() {
        return LoginDTO.builder().password("test@123").email("testemployee@gmail.com").build();
    }

    private static RegistrationDto testRegistrationDto() {
         RegistrationDto registrationDto = new RegistrationDto();
        registrationDto.setUsername("testEmployee");
        registrationDto.setEmail("testemployeename@test.com");
        registrationDto.setPassword("Test@123");

        return registrationDto;

    }


    private static Employee testEmployee() {
        Employee employee = new Employee();
        employee.setId(1l);
        employee.setUsername("testemployeename@test.com");
        employee.setPassword("Test@123");
        employee.setEmail("testemployeename@test.com");
        return employee;
    }
}
