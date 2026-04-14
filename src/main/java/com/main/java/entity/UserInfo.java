package com.main.java.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user_info")
@Getter
@Setter
public class UserInfo extends BaseEntity{
	
	private String firstName;

	private String lastName;
	
	@Column(name = "join_date")
	private LocalDateTime joinDate;
	
	@Column(name = "resign_date")
	private LocalDateTime resignDate;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "account_id", nullable = false, unique = true)
	private Account account;

	
}
