package com.example.report.model;

import lombok.Data;

@Data
public class ContactDetails {

	private long phone1;
	private long phone2;
	private String officialEmail;
	private String personalEmail;
	private Employee employee;

}
