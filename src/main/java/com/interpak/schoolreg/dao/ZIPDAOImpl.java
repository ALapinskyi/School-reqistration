package com.interpak.schoolreg.dao;

import java.util.List;

import com.interpak.schoolreg.domain.Wishes;
import com.interpak.schoolreg.domain.ZIP;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ZIPDAOImpl implements ZIPDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void addZIP(ZIP zip) {
        sessionFactory.getCurrentSession().save(zip);
    }

    @SuppressWarnings("unchecked")
    public List<ZIP> listZIP() {

        return sessionFactory.getCurrentSession().createQuery("from ZIP")
            .list();
    }

    public void removeZIP(Integer id) {
    	ZIP zip = (ZIP) sessionFactory.getCurrentSession().load(
    			ZIP.class, id);
        if (null != zip) {
            sessionFactory.getCurrentSession().delete(zip);
        }

    }
    
    public ZIP findById(Integer id){
    	Query query = sessionFactory.getCurrentSession().createQuery("from ZIP where idzip = :id");
        query.setParameter("id", id);
        ZIP obj = (ZIP) query.uniqueResult();
        return obj;
    }

}