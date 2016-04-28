package com.interpak.schoolreg.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "Country")
public class Country implements Serializable{
	
	@Id
	@Column(name = "IDCountry")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "country_seq")
	@SequenceGenerator(name = "country_seq", sequenceName = "country_sequence")
	private Integer iDCountry;
	
	@Column(name = "Title")
	private String title;

	public Country() {
		super();
		// TODO Auto-generated constructor stub
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
