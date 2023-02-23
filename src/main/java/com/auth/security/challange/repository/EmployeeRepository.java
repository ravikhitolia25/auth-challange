package com.auth.security.challange.repository;

import com.auth.security.challange.entity.Employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    public Employee findByEmail(String email);

    Boolean existsByEmail(String email);
}
