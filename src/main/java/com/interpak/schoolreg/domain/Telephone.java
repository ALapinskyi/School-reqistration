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
@Table(name = "Telephone")
public class Telephone implements Serializable{
	
	@Id
	@Column(name = "IDTelephone")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "telephone_seq")
	@SequenceGenerator(name = "telephone_seq", sequenceName = "telephone_sequence")
	private Integer iDTelephone;
	
	@Column(name = "Numberphone")
	private String numberphone;

	
	@ManyToOne
    @JoinColumn(name="IDUser")
    private Users userT;

	public Telephone() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getiDTelephone() {
		return iDTelephone;
	}

	public void setiDTelephone(Integer iDTelephone) {
		this.iDTelephone = iDTelephone;
	}

	public String getNumberphone() {
		return numberphone;
	}

	public void setNumberphone(String numberphone) {
		this.numberphone = numberphone;
	}


	public Users getUserT() {
		return userT;
	}

	public void setUserT(Users userT) {
		this.userT = userT;
	}
	
	

}
