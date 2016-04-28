package com.interpak.schoolreg.dao;

import java.util.List;

import com.interpak.schoolreg.domain.City;
import com.interpak.schoolreg.domain.Country;

public interface CityDAO {

    public void addCity(City city);

    public List<City> listCity();

    public void removeCity(Integer id);
    
    public City findById(Integer id); 
}