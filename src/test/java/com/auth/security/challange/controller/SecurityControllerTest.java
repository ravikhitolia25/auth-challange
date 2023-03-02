package com.auth.security.challange.controller;//package com.auth.security.challange.controller;


import com.auth.security.challange.AuthChallangeApplication;
import com.auth.security.challange.dto.LoginDTO;
import com.auth.security.challange.dto.RegistrationDto;
import com.auth.security.challange.entity.Employee;
import com.auth.security.challange.repository.EmployeeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {TestConfiguration.class, AuthChallangeApplication.class})
@AutoConfigureMockMvc
 class SecurityControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    EmployeeRepository employeeRepository;

    @Test
     void should_register_new_employee() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
       RegistrationDto registrationDto= new RegistrationDto();
       registrationDto.setEmail("testemployee@gmail.com");
       registrationDto.setUsername("testemployee@gmail.com");
       registrationDto.setPassword("test@123");

        Employee employee= new Employee();
        employee.setId(101l);
        employee.setUsername(registrationDto.getUsername());
        employee.setEmail(registrationDto.getEmail());
        employee.setPassword(registrationDto.getPassword());
        when(employeeRepository.save(Mockito.any(Employee.class))).thenReturn(employee);
        MvcResult result = mockMvc.perform(
                        post("/api/v1/auth/register")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(registrationDto)))
                .andExpect(status().isOk()) .andReturn();


    }
    @Test
     void should_not_register_existing_employee() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
       RegistrationDto registrationDto = new RegistrationDto();
       registrationDto.setEmail("testemployee@gmail.com");
       registrationDto.setUsername("testemployee@gmail.com");
       registrationDto.setPassword("test@123");
        Employee employee= new Employee();
        employee.setUsername(registrationDto.getUsername());
        employee.setEmail(registrationDto.getEmail());
        employee.setPassword(registrationDto.getPassword());
        when(employeeRepository.existsByEmail(registrationDto.getEmail())).thenReturn(true);
        when(employeeRepository.save(Mockito.any(Employee.class))).thenReturn(employee);
        mockMvc.perform(
                post("/api/v1/auth/register")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(registrationDto)))
                .andExpect(status().isOk());
    }

    @Test
     void should_login_existing_employee() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
       LoginDTO loginDto= LoginDTO.builder().password("test@123").email("testemployee@gmail.com").build();

        Employee existingEmployee=new Employee();
        existingEmployee.setId(1L);
        when(employeeRepository.findByEmail(loginDto.email())).thenReturn(existingEmployee);
        mockMvc.perform(
                        post("/api/v1/auth/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(loginDto)))
                .andExpect(status().isOk());


    }
}
