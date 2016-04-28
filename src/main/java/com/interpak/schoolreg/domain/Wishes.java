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
@Table(name = "Wishes")
public class Wishes implements Serializable{
	
	@Id
	@Column(name = "IDWishes")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "wishes_seq")
	@SequenceGenerator(name = "wishes_seq", sequenceName = "wishes_sequence")
	private Integer iDWishes;
	
	@Column(name = "Description")
	private String description;

	@ManyToOne
    @JoinColumn(name="IDUser")
    private Users userW;

	public Wishes() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getiDWishes() {
		return iDWishes;
	}

	public void setiDWishes(Integer iDWishes) {
		this.iDWishes = iDWishes;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public Users getUserW() {
		return userW;
	}

	public void setUserW(Users userW) {
		this.userW = userW;
	}
	
	
}
