package com.example.report.model;

import java.util.Set;

import lombok.Data;

@Data
public class Project {

	private String projectName;
	private String manager;
	private Set<Employee> employees;
}
