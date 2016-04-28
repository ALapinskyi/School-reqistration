package com.interpak.schoolreg.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "City")
public class City implements Serializable{
	
	@Id
	@Column(name = "IDCity")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "city_seq")
	@SequenceGenerator(name = "city_seq", sequenceName = "city_sequence")
	private Integer iDCity;
	
	@Column(name = "IDCountry")
	private Integer iDCountry;
	
	@Column(name = "Title")
	private String title;

	public City() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getiDCity() {
		return iDCity;
	}

	public void setiDCity(Integer iDCity) {
		this.iDCity = iDCity;
	}

	public Integer getiDCountry() {
		return iDCountry;
	}

	public void setiDCountry(Integer iDCountry) {
		this.iDCountry = iDCountry;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	
}
