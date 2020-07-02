package com.example.report.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.report.dto.EmployeeTo;

public interface EmployeeRepository extends JpaRepository<EmployeeTo, Integer> {

}
