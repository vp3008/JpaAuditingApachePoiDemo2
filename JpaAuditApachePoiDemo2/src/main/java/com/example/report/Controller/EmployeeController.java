package com.example.report.Controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.report.Service.EmployeeService;
import com.example.report.dto.EmployeeTo;
import com.example.report.export.EmployeeExcelExporter;
import com.example.report.model.Employee;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/employee")
@Api
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	ModelMapper modelMapper;

	@GetMapping("/employees")
	@ApiOperation(value = "Get operation to fetch all employees")
	public List<EmployeeTo> getAllEmployees() {
		return employeeService.getAllEmployees();
	}

	@GetMapping("/employees/{id}")
	@ApiOperation(value = "Get operation to fetch employee by ID")
	public Employee getEmployee(@PathVariable int id) {
		EmployeeTo empTo = employeeService.getEmployee(id);
		return modelMapper.map(empTo, Employee.class);
	}

	@PostMapping("/employees")
	@ApiOperation(value = "Post operation to save employee")
	public void addEmployee(
			@ApiParam(name = "Employee Object", required = true) @RequestBody(required = true) Employee employee) {
		EmployeeTo empTo = modelMapper.map(employee, EmployeeTo.class);
		employeeService.addEmployee(empTo);
	}

	@PutMapping("/employees/{id}")
	@ApiOperation(value = "PUT operation to update employee")
	public void updateEmployee(@RequestBody Employee employee, @PathVariable int id) {
		EmployeeTo empTo = modelMapper.map(employee, EmployeeTo.class);
		empTo.setEmp_id(id);
		employeeService.updateEmployee(empTo);
	}

	@DeleteMapping("/employees/{id}")
	@ApiOperation(value = "Delete operation to delete employee by ID")
	public void deleteEmployee(@PathVariable int id) {
		employeeService.deleteEmployee(id);
	}

	@DeleteMapping("/employees")
	@ApiOperation(value = "Delete operation to delete all employees")
	public void deleteAllEmployees() {
		employeeService.deleteAllEmployees();
	}
	
	@GetMapping("/employees/export")
	public void exportToExcel (HttpServletResponse response) throws IOException{
		response.setContentType("application/octet-stream");
		String headerKey = "Content-Disposition";
		
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());
		String fileName = "Employees_" + currentDateTime + ".xlsx";
		
		String headerValue = "attachment; filename="+fileName;
		response.setHeader(headerKey, headerValue);	
		
		List<EmployeeTo> listEmployees = employeeService.getAllEmployees();
		EmployeeExcelExporter excelExporter = new EmployeeExcelExporter(listEmployees);
		excelExporter.export(response);
	}
}
