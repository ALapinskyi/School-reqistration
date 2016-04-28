package com.interpak.schoolreg.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "Address")
public class Address implements Serializable{
	
	@Id
	@Column(name = "IDAddress")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_seq")
	@SequenceGenerator(name = "address_seq", sequenceName = "address_sequence")
	private Integer iDAddress;
	
	@Column(name = "TextAddress")
	private String textAdress;
	
    @Column(name="IDZIP")
    private Integer iDZip;


	public Address() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getiDAddress() {
		return iDAddress;
	}

	public void setiDAddress(Integer iDAddress) {
		this.iDAddress = iDAddress;
	}

	public String getTextAdress() {
		return textAdress;
	}

	public void setTextAdress(String textAdress) {
		this.textAdress = textAdress;
	}
/*
	public ZIP getZIPA() {
		return ZIPA;
	}

	public void setZIPA(ZIP zIPA) {
		ZIPA = zIPA;
	}*/

	public Integer getiDZip() {
		return iDZip;
	}

	public void setiDZip(Integer iDZip) {
		this.iDZip = iDZip;
	}


	
}
