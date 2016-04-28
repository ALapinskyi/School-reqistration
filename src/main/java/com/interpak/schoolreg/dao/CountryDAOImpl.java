package com.interpak.schoolreg.dao;

import java.util.List;

import com.interpak.schoolreg.domain.Address;
import com.interpak.schoolreg.domain.Country;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CountryDAOImpl implements CountryDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void addCountry(Country country) {
        sessionFactory.getCurrentSession().save(country);
    }

    @SuppressWarnings("unchecked")
    public List<Country> listCountry() {

        return sessionFactory.getCurrentSession().createQuery("from Country")
            .list();
    }

    public void removeCountry(Integer id) {
    	Country country = (Country) sessionFactory.getCurrentSession().load(
    			Country.class, id);
        if (null != country) {
            sessionFactory.getCurrentSession().delete(country);
        }

    }
    
    public Country findById(Integer id){
    	Query query = sessionFactory.getCurrentSession().createQuery("from Country where idcountry = :id");
        query.setParameter("id", id);
        Country obj = (Country) query.uniqueResult();
        return obj;
    }

}