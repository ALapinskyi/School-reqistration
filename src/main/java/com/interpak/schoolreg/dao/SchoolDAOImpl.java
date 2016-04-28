package com.interpak.schoolreg.dao;

import java.util.List;

import com.interpak.schoolreg.domain.Country;
import com.interpak.schoolreg.domain.School;
import com.interpak.schoolreg.domain.Users;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SchoolDAOImpl implements SchoolDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void addSchool(School school) {
        sessionFactory.getCurrentSession().save(school);
    }

    @SuppressWarnings("unchecked")
    public List<School> listSchool() {

        return sessionFactory.getCurrentSession().createQuery("from School")
            .list();
    }

    public void removeSchool(Integer id) {
    	School school = (School) sessionFactory.getCurrentSession().load(
    			School.class, id);
        if (null != school) {
            sessionFactory.getCurrentSession().delete(school);
        }

    }
    
    public School findById(Integer id)
    {
    	Query query = sessionFactory.getCurrentSession().createQuery("from School where idSchool = :id");
        query.setParameter("id", id);
        School obj = (School) query.uniqueResult();
        return obj;
    }

}