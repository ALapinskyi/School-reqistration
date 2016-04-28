package com.interpak.schoolreg.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class UserSchoolId implements Serializable{
	
	private Users user;
	private School school;
	
	public UserSchoolId() {
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
	public School getSchool() {
		return school;
	}
	public void setSchool(School school) {
		this.school = school;
	}
	public UserSchoolId(Users user, School school) {
		super();
		this.user = user;
		this.school = school;
	}
	
	
	
}
