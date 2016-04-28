package com.interpak.schoolreg.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Users")
public class Users implements Serializable {

	@Id
	@Column(name = "IDUser")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_seq")
	@SequenceGenerator(name = "users_seq", sequenceName = "users_sequence")
	private Integer iDUser;

	@Column(name = "Name")
	private String name;

	@Column(name = "Surname")
	private String surname;

	@Column(name = "Gender")
	private String gender;

	@Column(name = "Birthdate")
	private Date birthdate;

	@Column(name = "Insnumber")
	private String insnumber;

	@Column(name = "Username")
	private String username;

	@Column(name = "Password")
	private String password;

	@Column(name = "IDRole")
	private Integer iDRole;

	@OneToMany(mappedBy = "userT")
	private Set<Telephone> telephones;

	@OneToMany(mappedBy = "userW")
	private Set<Wishes> wishes;

	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}
/*
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.user", cascade = CascadeType.ALL)
	private Set<UserAddress> userAddresses = new HashSet<UserAddress>(0);

	public Set<UserAddress> getUserAddresses() {
		return userAddresses;
	}

	public void setUserAddresses(Set<UserAddress> userAddresses) {
		this.userAddresses = userAddresses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.user", cascade = CascadeType.ALL)
	private Set<UserSchool> userSchools = new HashSet<UserSchool>(0);

	public Set<UserSchool> getUserSchools() {
		return userSchools;
	}

	public void setUserSchools(Set<UserSchool> userSchool) {
		this.userSchools = userSchool;
	}
*/
	public Integer getiDUser() {
		return iDUser;
	}

	public void setiDUser(Integer iDUser) {
		this.iDUser = iDUser;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getInsnumber() {
		return insnumber;
	}

	public void setInsnumber(String insnumber) {
		this.insnumber = insnumber;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getiDRole() {
		return iDRole;
	}
	public void setiDRole(Integer iDRole) {
		this.iDRole = iDRole;
	}
	public Set<Telephone> getTelephones() {
		return telephones;
	}

	public void setTelephones(Set<Telephone> telephones) {
		this.telephones = telephones;
	}

	public Set<Wishes> getWishes() {
		return wishes;
	}

	public void setWishes(Set<Wishes> wishes) {
		this.wishes = wishes;
	}
	public Users(String name, String surname, String gender, Date birthdate,
			String insnumber, String username, String password, Integer role) {
		super();
		this.name = name;
		this.surname = surname;
		this.gender = gender;
		this.birthdate = birthdate;
		this.insnumber = insnumber;
		this.username = username;
		this.password = password;
		this.iDRole = role;
	}
	
}
