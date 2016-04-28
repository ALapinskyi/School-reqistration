package com.interpak.schoolreg.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "ZIP")
public class ZIP implements Serializable{
	
	@Id
	@Column(name = "IDZIP")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "zip_seq")
	@SequenceGenerator(name = "zip_seq", sequenceName = "zip_sequence")
	private Integer iDZIP;
	
	@Column(name = "IDCity")
	private Integer iDCity;
	
	
	@Column(name = "NumberZIP")
	private String number;
	/*
	@OneToMany(mappedBy = "ZIPA")
    private Set<Address> ZIPAddress = new HashSet<Address>();
	
	@OneToMany(mappedBy = "cityA")
    private Set<Address> cityAddress = new HashSet<Address>();
	
	@OneToMany(mappedBy = "countryA")
    private Set<Address> countryAddress = new HashSet<Address>();
	
	@OneToMany(mappedBy = "ZIPS")
    private Set<School> ZIPSchool = new HashSet<School>();
	
	@OneToMany(mappedBy = "cityS")
    private Set<School> citySchool = new HashSet<School>();
	
	@OneToMany(mappedBy = "countryS")
    private Set<School> countrySchool = new HashSet<School>();
*/
	public ZIP() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getiDZIP() {
		return iDZIP;
	}

	public void setiDZIP(Integer iDZIP) {
		this.iDZIP = iDZIP;
	}

	public Integer getiDCity() {
		return iDCity;
	}

	public void setiDCity(Integer iDCity) {
		this.iDCity = iDCity;
	}


	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
/*
	public Set<Address> getZIPAddress() {
		return ZIPAddress;
	}

	public void setZIPAddress(Set<Address> zIPAddress) {
		ZIPAddress = zIPAddress;
	}

	public Set<Address> getCityAddress() {
		return cityAddress;
	}

	public void setCityAddress(Set<Address> cityAddress) {
		this.cityAddress = cityAddress;
	}

	public Set<Address> getCountryAddress() {
		return countryAddress;
	}

	public void setCountryAddress(Set<Address> countryAddress) {
		this.countryAddress = countryAddress;
	}

	public Set<School> getZIPSchool() {
		return ZIPSchool;
	}

	public void setZIPSchool(Set<School> zIPSchool) {
		ZIPSchool = zIPSchool;
	}

	public Set<School> getCitySchool() {
		return citySchool;
	}

	public void setCitySchool(Set<School> citySchool) {
		this.citySchool = citySchool;
	}

	public Set<School> getCountrySchool() {
		return countrySchool;
	}

	public void setCountrySchool(Set<School> countrySchool) {
		this.countrySchool = countrySchool;
	}*/
}
