package com.main.java.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "account")
@Getter
@Setter
public class Account extends BaseEntity{
	
	private String username;
	
	private String password;
	
	private String role;
	
	private String status;

}
