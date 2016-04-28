package com.interpak.schoolreg.dao;

import java.util.List;

import com.interpak.schoolreg.domain.Users;
import com.interpak.schoolreg.domain.Wishes;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class WishesDAOImpl implements WishesDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void addWishes(Wishes wishes) {
        sessionFactory.getCurrentSession().save(wishes);
    }
    
    public void updateWish(Wishes wishes){
    	sessionFactory.getCurrentSession().update(wishes);
    }

    @SuppressWarnings("unchecked")
    public List<Wishes> listWishes() {

        return sessionFactory.getCurrentSession().createQuery("from Wishes")
            .list();
    }

    public void removeWishes(Integer id) {
    	Wishes wishes = (Wishes) sessionFactory.getCurrentSession().load(
    			Wishes.class, id);
        if (null != wishes) {
            sessionFactory.getCurrentSession().delete(wishes);
        }

    }
    
    public Wishes findByUser(Integer id){
    	Query query = sessionFactory.getCurrentSession().createQuery("from Wishes where iduser = :id");
        query.setParameter("id", id);
        Wishes obj = (Wishes) query.uniqueResult();
        return obj;
    }
}