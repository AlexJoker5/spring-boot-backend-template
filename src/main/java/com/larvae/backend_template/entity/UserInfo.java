package com.larvae.backend_template.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * JPA entity representing user information linked to an account.
 */
@Entity
@Table(name = "user_info")
@Getter
@Setter
public class UserInfo extends BaseEntity{

	@Column(name = "first_name", nullable = false)
	private String firstName;

	@Column(name = "last_name", nullable = false)
	private String lastName;
	
	@Column(name = "join_date")
	private LocalDateTime joinDate;
	
	@Column(name = "resign_date")
	private LocalDateTime resignDate;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "account_id", nullable = false, unique = true)
	private Account account;

	
}
