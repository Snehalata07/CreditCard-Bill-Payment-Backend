package com.creditcardpayment.bean;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.creditcardpayment.entity.CustomerEntity;


@Embeddable
public class Address implements Serializable{

	private static final long serialVersionUID = 1L;

	@NotNull
	private String doorNo;
	
	@NotNull(message="street name cannot be null")	
	@NotBlank(message="street name cannot be blank")
	private String street;
	
	@NotNull(message="area name cannot be null")	
	@NotBlank(message="area name cannot be blank")
	private String area;
	
	@NotNull(message="city name cannot be null")	
	@NotBlank(message="city name cannot be blank")
	private String city;
	
	@NotNull(message="state name cannot be null")	
	@NotBlank(message="state cannot be blank")
	private String state;
	
	@NotNull(message="pincode cannot be null")	
	@NotBlank(message="full name cannot be blank")
	@Pattern(regexp = "[0-9]{6}")
	private Integer pincode;
	
	@OneToMany
	@Transient
	private Set<CustomerEntity> customers;
	
	public Address() {
		/* Default Constructor */
	}

	public Address(String doorNo, String street, String area, String city, String state, Integer pincode) {
		super();
		this.doorNo = doorNo;
		this.street = street;
		this.area = area;
		this.city = city;
		this.state = state;
		this.pincode = pincode;
	}

	public String getDoorNo() {
		return doorNo;
	}

	public void setDoorNo(String doorNo) {
		this.doorNo = doorNo;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Integer getPincode() {
		return pincode;
	}

	public void setPincode(Integer pincode) {
		this.pincode = pincode;
	}

	
	@Override
	public String toString() {
		return String.format("doorNo=%s, street=%s, area=%s, city=%s, state=%s, pincode=%s", doorNo, street,
				area, city, state, pincode);
	}
	
	
	
	
}
