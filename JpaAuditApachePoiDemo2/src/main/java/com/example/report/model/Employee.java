package com.example.report.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(value = JsonInclude.Include.ALWAYS, content = JsonInclude.Include.ALWAYS)
public class Employee {

	private int id;
	private String name;
	private String designation;
	private float experience;
	private String isActive;
	/*
	 * private Project project; private ContactDetails contactDetails;
	 */

}
