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
@Table(name = "School")
public class School implements Serializable{
	
	@Id
	@Column(name = "IDSchool")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "school_seq")
	@SequenceGenerator(name = "school_seq", sequenceName = "school_sequence")
	private Integer iDSchool;
	
	@Column(name = "Title")
	private String title;
	/*
	@ManyToOne
    @JoinColumn(name="IDZIP")
    private ZIP ZIPS;
	*/
	@Column(name = "IDZip")
	private Integer iDZip;

	public School() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getiDSchool() {
		return iDSchool;
	}

	public void setiDSchool(Integer iDSchool) {
		this.iDSchool = iDSchool;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getiDZip() {
		return iDZip;
	}

	public void setiDZip(Integer iDZip) {
		this.iDZip = iDZip;
	}
	
/*
	public ZIP getZIPS() {
		return ZIPS;
	}

	public void setZIPS(ZIP zIPS) {
		ZIPS = zIPS;
	}
*/

	
}
