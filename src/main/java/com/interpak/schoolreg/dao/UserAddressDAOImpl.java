package com.interpak.schoolreg.dao;

import java.util.List;

import com.interpak.schoolreg.domain.Address;
import com.interpak.schoolreg.domain.UserAddress;
import com.interpak.schoolreg.domain.UserSchool;
import com.interpak.schoolreg.domain.Users;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserAddressDAOImpl implements UserAddressDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void addUserAddress(UserAddress userAddress) {
        sessionFactory.getCurrentSession().save(userAddress);
    }

    @SuppressWarnings("unchecked")
    public List<UserAddress> listUserAddress() {

        return sessionFactory.getCurrentSession().createQuery("from UserAddress")
            .list();
    }
    
    public void updateUserAddress(UserAddress userAddress){
    	sessionFactory.getCurrentSession().update(userAddress);
    }
    
    @SuppressWarnings("unchecked")
	public List<UserAddress> listNotMain(Integer id){
    	Query query = sessionFactory.getCurrentSession().createQuery("from UserAddress where iduser = :id and ismain = 'no'");
        query.setParameter("id", id);
        List<UserAddress> items  = query.list();
        return items;
    }

    public void removeUserAddress(Integer idu, Integer ida) {
    	Query query = sessionFactory.getCurrentSession().createQuery("from UserAddress where iduser = :idu and idaddress = :ida");
        query.setParameter("idu", idu);
        query.setParameter("ida", ida);
        UserAddress obj = (UserAddress) query.uniqueResult();
        if (null != obj) {
            sessionFactory.getCurrentSession().delete(obj);
        }
    }
    
    
    public UserAddress findByUserMain(Integer id){
    	
    	Query query = sessionFactory.getCurrentSession().createQuery("from UserAddress where iduser = :id and ismain = 'yes'");
        query.setParameter("id", id);
        UserAddress obj = (UserAddress) query.uniqueResult();
        return obj;
    }

}