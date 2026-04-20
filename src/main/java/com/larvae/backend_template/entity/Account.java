package com.larvae.backend_template.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.larvae.backend_template.enums.AccountStatus;
import com.larvae.backend_template.enums.Roles;
import jakarta.persistence.*;
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

	@Column(unique = true, nullable = false)
	private String username;

	@JsonIgnore
	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Roles role;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private AccountStatus status;

}
