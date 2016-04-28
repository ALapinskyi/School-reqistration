package com.interpak.schoolreg.domain;

import java.io.Serializable;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name = "UserSchool")
@AssociationOverrides({
		@AssociationOverride(name = "pk.user", joinColumns = @JoinColumn(name = "IDUser")),
		@AssociationOverride(name = "pk.school", joinColumns = @JoinColumn(name = "IDSchool")) })
public class UserSchool implements Serializable{
	
	private UserSchoolId pk = new UserSchoolId();
	
	
	public UserSchool() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Column(name = "Description")
    private String description;
	
	@EmbeddedId
	public UserSchoolId getPk() {
		return pk;
	}

	@Transient
	public Users getUser() {
		return getPk().getUser();
	}

	public void setUser(Users user) {
		getPk().setUser(user);
	}

	@Transient
	public School getSchool() {
		return getPk().getSchool();
	}

	public void setSchool(School school) {
		getPk().setSchool(school);
	}
	
	public void setPk(UserSchoolId pk) {
		this.pk = pk;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public UserSchool(UserSchoolId pk, String description) {
		super();
		this.pk = pk;
		this.description = description;
	}
	
	
}
