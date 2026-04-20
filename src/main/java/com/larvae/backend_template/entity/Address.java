package com.larvae.backend_template.entity;

import com.larvae.backend_template.enums.MyanmarRegion;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "address")
public class Address extends BaseEntity{
	
	@Enumerated(EnumType.STRING)
	private MyanmarRegion region;

	@Column(nullable = false, unique = true)
	private String township;

	@Column(name = "postal_code", length = 5)
	private String postalCode;
	
	@Column(name="full_address")
	private String fullAddress;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "user_id", nullable = false, unique = true)
	private UserInfo userInfo;
}
