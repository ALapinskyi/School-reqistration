package com.interpak.schoolreg.dao;

import java.util.List;
import com.interpak.schoolreg.domain.Country;

public interface CountryDAO {

    public void addCountry(Country country);

    public List<Country> listCountry();

    public void removeCountry(Integer id);
    
    public Country findById(Integer id);
}