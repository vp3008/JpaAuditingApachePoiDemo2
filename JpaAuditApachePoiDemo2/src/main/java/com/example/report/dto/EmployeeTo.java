package com.example.report.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import com.example.report.auditing.Auditable;

import lombok.Data;

@Data
@Entity
@Table(name = "EMPLOYEE")
public class EmployeeTo extends Auditable<String> {

	@Id
	@GeneratedValue
	private int emp_id;
	private String name;
	private String designation;
	private float experience;
	private String isActive;

	@Column(name = "created_at")
	private Date createdAt;

	@Column(name = "updated_at")
	private Date updatedAt;

	@PrePersist
	public void onPrePersist() {
		this.setCreatedAt(new Date());
		this.setUpdatedAt(new Date());
	}

	@PreUpdate
	public void onPreUpdate() {
		this.setUpdatedAt(new Date());
	}

	/**
	 * @return the createdAt
	 */
	public Date getCreatedAt() {
		return createdAt;
	}

	/**
	 * @param createdAt the createdAt to set
	 */
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * @return the updatedAt
	 */
	public Date getUpdatedAt() {
		return updatedAt;
	}

	/**
	 * @param updatedAt the updatedAt to set
	 */
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	/*
	 * 
	 * @ManyToOne(cascade = CascadeType.ALL)
	 * 
	 * @JoinColumn(name = "PROJECT_ID") private ProjectTo project;
	 * 
	 * @OneToOne(cascade = CascadeType.ALL)
	 * 
	 * @JoinColumn(name = "OFFICIALE_MAIL") private ContactDetailsTo contactDetails;
	 */

}
