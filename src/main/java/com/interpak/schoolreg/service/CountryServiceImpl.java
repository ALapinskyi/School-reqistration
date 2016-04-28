package com.interpak.schoolreg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 



import com.interpak.schoolreg.dao.CountryDAO;
import com.interpak.schoolreg.domain.Country;
 
@Service
public class CountryServiceImpl implements CountryService {
 
    @Autowired
    private CountryDAO countryDAO;
 
    @Transactional
    public void addCountry(Country country) {
    	countryDAO.addCountry(country);
    }
 
    @Transactional
    public List<Country> listCountry() {
 
        return countryDAO.listCountry();
    }
 
    @Transactional
    public void removeCountry(Integer id) {
    	countryDAO.removeCountry(id);
    }
    
    @Transactional
    public Country findById(Integer id){
    	return countryDAO.findById(id);
    }
}