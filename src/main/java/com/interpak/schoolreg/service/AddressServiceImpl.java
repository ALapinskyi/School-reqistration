package com.interpak.schoolreg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 




import com.interpak.schoolreg.dao.AddressDAO;
import com.interpak.schoolreg.domain.Address;
 
@Service
public class AddressServiceImpl implements AddressService {
 
    @Autowired
    private AddressDAO addressDAO;
 
    @Transactional
    public void addAddress(Address address) {
    	addressDAO.addAddress(address);
    }
 
    @Transactional
    public List<Address> listAddress() {
 
        return addressDAO.listAddress();
    }
 
    @Transactional
    public void removeAddress(Integer id) {
    	addressDAO.removeAddress(id);
    }
    
    @Transactional
    public Address fingByTextZip(String text, Integer zip){
    	return addressDAO.fingByTextZip(text,zip);
    }
    
    @Transactional
    public Address fingById(Integer id){
    	return addressDAO.fingById(id);
    }
}