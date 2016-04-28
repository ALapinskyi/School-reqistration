package com.interpak.schoolreg.service;

import java.util.List;

import com.interpak.schoolreg.domain.Country;

public interface CountryService {

    public void addCountry(Country country);

    public List<Country> listCountry();

    public void removeCountry(Integer id);
    
    public Country findById(Integer id);
}