package com.interpak.schoolreg.dao;

import java.util.List;

import com.interpak.schoolreg.domain.Users;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UsersDAOImpl implements UsersDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void addUser(Users user) {
        sessionFactory.getCurrentSession().save(user);
    }
    
    public void updateUser(Users user) {
        sessionFactory.getCurrentSession().update(user);
    }

    @SuppressWarnings("unchecked")
    public List<Users> listUser() {

        return sessionFactory.getCurrentSession().createQuery("from Users")
            .list();
    }
    
    @SuppressWarnings("unchecked")
    public List<Users> listUserByRole() {

        return sessionFactory.getCurrentSession().createQuery("from Users where idrole = 2")
            .list();
    }

    public void removeUser(Integer id) {
    	Users user = (Users) sessionFactory.getCurrentSession().load(
    			Users.class, id);
        if (null != user) {
            sessionFactory.getCurrentSession().delete(user);
        }
    }
    
    public Users findByLogin(String login)
    {
    	Query query = sessionFactory.getCurrentSession().createQuery("from Users where username = :login");
        query.setParameter("login", login);
        Users obj = (Users) query.uniqueResult();
        return obj;
    }
    
    public Users findById(Integer id)
    {
    	Query query = sessionFactory.getCurrentSession().createQuery("from Users where iduser = :id");
        query.setParameter("id", id);
        Users obj = (Users) query.uniqueResult();
        return obj;
    }

}