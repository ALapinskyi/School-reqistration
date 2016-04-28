package com.interpak.schoolreg.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Role")
public class Role implements Serializable{
	
	@Id
	@Column(name = "IDRole")
	@GeneratedValue
	private Integer iDRole;
	
	@Column(name = "NameRole")
	private String nameRole;

	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getiDRole() {
		return iDRole;
	}

	public void setiDRole(Integer iDRole) {
		this.iDRole = iDRole;
	}

	public String getNameRole() {
		return nameRole;
	}

	public void setNameRole(String nameRole) {
		this.nameRole = nameRole;
	}
	
	
}
