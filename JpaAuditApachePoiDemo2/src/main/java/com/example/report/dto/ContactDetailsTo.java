package com.example.report.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "CONTACT_INFO")
public class ContactDetailsTo {

	private long phone1;
	private long phone2;
	@Id
	@Column(name = "OFFICIALE_MAIL")
	private String officialEmail;
	private String personalEmail;
	/*
	 * @OneToOne(cascade = CascadeType.ALL) private EmployeeTo employee;
	 */
}
