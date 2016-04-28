package com.interpak.schoolreg.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class UserAddressId implements Serializable{
	
	private Users user;
	private Address address;
	
	public UserAddressId() {
		super();
		// TODO Auto-generated constructor stub
	}
	@ManyToOne
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	
	@ManyToOne
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public UserAddressId(Users user, Address address) {
		super();
		this.user = user;
		this.address = address;
	}
	

}
