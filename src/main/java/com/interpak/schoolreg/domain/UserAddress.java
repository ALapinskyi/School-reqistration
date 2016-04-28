package com.interpak.schoolreg.domain;

import java.io.Serializable;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "UserAddress")
@AssociationOverrides({
		@AssociationOverride(name = "pk.user", joinColumns = @JoinColumn(name = "IDUser")),
		@AssociationOverride(name = "pk.address", joinColumns = @JoinColumn(name = "IDAddress")) })
public class UserAddress implements Serializable {

	private UserAddressId pk = new UserAddressId();
	
	@Column(name = "IsMain")
    private String isMain;
	
	@Column(name = "Type")
    private String type;
	
	public UserAddress() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public UserAddress(UserAddressId pk, String isMain, String type) {
		super();
		this.pk = pk;
		this.isMain = isMain;
		this.type = type;
	}


	@EmbeddedId
	public UserAddressId getPk() {
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
	public Address getAddress() {
		return getPk().getAddress();
	}

	public void setAddress(Address address) {
		getPk().setAddress(address);
	}
	
	public void setPk(UserAddressId pk) {
		this.pk = pk;
	}

	public String getIsMain() {
		return isMain;
	}

	public void setIsMain(String isMain) {
		this.isMain = isMain;
	}

	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
}
