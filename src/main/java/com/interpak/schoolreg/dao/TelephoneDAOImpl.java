package com.interpak.schoolreg.dao;

import java.util.List;

import com.interpak.schoolreg.domain.Telephone;
import com.interpak.schoolreg.domain.Wishes;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TelephoneDAOImpl implements TelephoneDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void addTelephone(Telephone telephone) {
        sessionFactory.getCurrentSession().save(telephone);
    }
    
    public void updateTelephone(Telephone telephone){
    	sessionFactory.getCurrentSession().update(telephone);
    }

    @SuppressWarnings("unchecked")
    public List<Telephone> listTelephone() {

        return sessionFactory.getCurrentSession().createQuery("from Telephone")
            .list();
    }

    public void removeTelephone(Integer id) {
    	Telephone telephone = (Telephone) sessionFactory.getCurrentSession().load(
    			Telephone.class, id);
        if (null != telephone) {
            sessionFactory.getCurrentSession().delete(telephone);
        }

    }
    
    public Telephone findByUser(Integer id){
    	Query query = sessionFactory.getCurrentSession().createQuery("from Telephone where iduser = :id");
        query.setParameter("id", id);
        Telephone obj = (Telephone) query.uniqueResult();
        return obj;
    }

}