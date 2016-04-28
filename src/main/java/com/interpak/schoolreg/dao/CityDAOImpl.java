package com.interpak.schoolreg.dao;

import java.util.List;

import com.interpak.schoolreg.domain.City;
import com.interpak.schoolreg.domain.Country;
import com.interpak.schoolreg.domain.ZIP;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CityDAOImpl implements CityDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void addCity(City city) {
        sessionFactory.getCurrentSession().save(city);
    }

    @SuppressWarnings("unchecked")
    public List<City> listCity() {

        return sessionFactory.getCurrentSession().createQuery("from City")
            .list();
    }

    public void removeCity(Integer id) {
    	City city = (City) sessionFactory.getCurrentSession().load(
    			City.class, id);
        if (null != city) {
            sessionFactory.getCurrentSession().delete(city);
        }

    }
    
    public City findById(Integer id){
    	Query query = sessionFactory.getCurrentSession().createQuery("from City where idcity = :id");
        query.setParameter("id", id);
        City obj = (City) query.uniqueResult();
        return obj;
    }

}