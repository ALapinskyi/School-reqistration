package com.interpak.schoolreg.domain;

public class SchoolUpdate {

	private School school;
	private UserSchool userschool;
	
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
	public SchoolUpdate() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SchoolUpdate(School school, UserSchool userschool) {
		super();
		this.school = school;
		this.userschool = userschool;
	}
	
	
}
