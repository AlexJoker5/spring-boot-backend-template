package com.main.java.entity;

import com.main.java.enums.MyanmarRegion;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "address")
public class Address extends BaseEntity{
	
	@Enumerated
	private MyanmarRegion region;
	
	private String township;
	
	private String postalCode;
	
	@Column(name="full_address")
	private String fullAddress;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false, unique = true)
	private UserInfo userInfo;
}
