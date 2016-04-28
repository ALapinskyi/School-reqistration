package com.interpak.schoolreg.domain;

import java.util.ArrayList;
import java.util.List;

public class Registration {
	
	private Users user;
	private Wishes wish;
	private Telephone telephone;
	private School school;
	private UserSchool userschool;
	private Address dataAddress;
	private UserAddress dataUserAddress;
	
	private Address address1;
	private UserAddress userAddress1;
	private Address address2;
	private UserAddress userAddress2;
	
	
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	public Wishes getWish() {
		return wish;
	}
	public void setWish(Wishes wish) {
		this.wish = wish;
	}
	public Telephone getTelephone() {
		return telephone;
	}
	public void setTelephone(Telephone telephone) {
		this.telephone = telephone;
	}
	public School getSchool() {
		return school;
	}
	public void setSchool(School school) {
		this.school = school;
	}
	public UserSchool getUserschool() {
		return userschool;
	}
	public void setUserschool(UserSchool userschool) {
		this.userschool = userschool;
	}
	public Address getDataAddress() {
		return dataAddress;
	}
	public void setDataAddress(Address dataAddress) {
		this.dataAddress = dataAddress;
	}
	
	public UserAddress getDataUserAddress() {
		return dataUserAddress;
	}
	public void setDataUserAddress(UserAddress dataUserAddress) {
		this.dataUserAddress = dataUserAddress;
	}
	
	public Address getAddress1() {
		return address1;
	}
	public void setAddress1(Address address1) {
		this.address1 = address1;
	}
	public UserAddress getUserAddress1() {
		return userAddress1;
	}
	public void setUserAddress1(UserAddress userAddress1) {
		this.userAddress1 = userAddress1;
	}
	public Address getAddress2() {
		return address2;
	}
	public void setAddress2(Address address2) {
		this.address2 = address2;
	}
	public UserAddress getUserAddress2() {
		return userAddress2;
	}
	public void setUserAddress2(UserAddress userAddress2) {
		this.userAddress2 = userAddress2;
	}
	public Registration() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
