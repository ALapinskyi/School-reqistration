package com.interpak.schoolreg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 



import com.interpak.schoolreg.dao.CityDAO;
import com.interpak.schoolreg.domain.City;
 
@Service
public class CityServiceImpl implements CityService {
 
    @Autowired
    private CityDAO cityDAO;
 
    @Transactional
    public void addCity(City city) {
    	cityDAO.addCity(city);
    }
 
    @Transactional
    public List<City> listCity() {
 
        return cityDAO.listCity();
    }
 
    @Transactional
    public void removeCity(Integer id) {
    	cityDAO.removeCity(id);
    }
    
    @Transactional
    public City findById(Integer id){
    	return cityDAO.findById(id);
    }
}