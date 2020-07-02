package com.example.report.Service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.report.Repository.EmployeeRepository;
import com.example.report.dto.EmployeeTo;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	public List<EmployeeTo> getAllEmployees() {
		return employeeRepository.findAll();
	}

	public EmployeeTo getEmployee(int id) {
		return employeeRepository.findById(id).get();
	}

	public void updateEmployee(EmployeeTo employee) {
		employeeRepository.save(employee);
	}

	@Transactional
	public void addEmployee(EmployeeTo employee) {
		employeeRepository.save(employee);
	}

	public void deleteEmployee(int id) {
		employeeRepository.deleteById(id);
	}

	public void deleteAllEmployees() {
		employeeRepository.deleteAll();

	}
}
