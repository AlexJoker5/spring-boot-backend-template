package com.main.java.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * JPA entity representing an account record.
 */
@Entity
@Table(name = "account")
@Getter
@Setter
public class Account extends BaseEntity{

	@Column(unique = true)
	private String username;
	
	private String password;
	
	private String role;
	
	private String status;

}
