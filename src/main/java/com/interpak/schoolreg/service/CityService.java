package com.interpak.schoolreg.service;

import java.util.List;

import com.interpak.schoolreg.domain.City;

public interface CityService {

    public void addCity(City city);

    public List<City> listCity();

    public void removeCity(Integer id);
    
    public City findById(Integer id); 
}