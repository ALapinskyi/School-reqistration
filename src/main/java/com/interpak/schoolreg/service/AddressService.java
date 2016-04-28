package com.interpak.schoolreg.service;

import java.util.List;

import com.interpak.schoolreg.domain.Address;

public interface AddressService {

    public void addAddress(Address address);

    public List<Address> listAddress();

    public void removeAddress(Integer id);
    
    public Address fingByTextZip(String text, Integer zip);
    
    public Address fingById(Integer id);
}