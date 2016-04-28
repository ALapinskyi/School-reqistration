package com.interpak.schoolreg.dao;

import java.util.List;

import com.interpak.schoolreg.domain.UserAddress;
import com.interpak.schoolreg.domain.UserSchool;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserSchoolDAOImpl implements UserSchoolDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void addUserSchool(UserSchool userSchool) {
        sessionFactory.getCurrentSession().save(userSchool);
    }
    
    public void updateUserSchool(UserSchool userSchool){
    	sessionFactory.getCurrentSession().update(userSchool);
    }

    @SuppressWarnings("unchecked")
    public List<UserSchool> listUserSchool() {

        return sessionFactory.getCurrentSession().createQuery("from UserSchool")
            .list();
    }

    public void removeUserSchool(Integer idu, Integer ids) {
    	Query query = sessionFactory.getCurrentSession().createQuery("from UserSchool where iduser = :idu and idschool = :ids");
        query.setParameter("idu", idu);
        query.setParameter("ids", ids);
        UserSchool obj = (UserSchool) query.uniqueResult();
        if (null != obj) {
            sessionFactory.getCurrentSession().delete(obj);
        }

    }

    public UserSchool findByUser(Integer id){
    	Query query = sessionFactory.getCurrentSession().createQuery("from UserSchool where iduser = :id");
        query.setParameter("id", id);
        UserSchool obj = (UserSchool) query.uniqueResult();
        return obj;
    }
    	
    
}