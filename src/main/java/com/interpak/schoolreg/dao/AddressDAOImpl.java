package com.interpak.schoolreg.dao;

import java.util.List;

import com.interpak.schoolreg.domain.Address;
import com.interpak.schoolreg.domain.Country;
import com.interpak.schoolreg.domain.Users;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AddressDAOImpl implements AddressDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void addAddress(Address address) {
        sessionFactory.getCurrentSession().save(address);
    }

    @SuppressWarnings("unchecked")
    public List<Address> listAddress() {

        return sessionFactory.getCurrentSession().createQuery("from Address")
            .list();
    }
    
    

    public void removeAddress(Integer id) {
    	Address address = (Address) sessionFactory.getCurrentSession().load(
    			Address.class, id);
        if (null != address) {
            sessionFactory.getCurrentSession().delete(address);
        }
    }
    
    public Address fingByTextZip(String text, Integer zip){
    	Query query = sessionFactory.getCurrentSession().createQuery("from Address where textaddress = :text and idzip = :zip");
        query.setParameter("text", text);
        query.setParameter("zip", zip);
        Address obj = (Address) query.uniqueResult();
        return obj;
    }
    
    public Address fingById(Integer id){
    	Query query = sessionFactory.getCurrentSession().createQuery("from Address where idaddress = :id");
        query.setParameter("id", id);
        Address obj = (Address) query.uniqueResult();
        return obj;
    }
    

}