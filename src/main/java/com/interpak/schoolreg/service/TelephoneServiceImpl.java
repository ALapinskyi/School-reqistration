package com.interpak.schoolreg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 



import com.interpak.schoolreg.dao.TelephoneDAO;
import com.interpak.schoolreg.domain.Telephone;
 
@Service
public class TelephoneServiceImpl implements TelephoneService {
 
    @Autowired
    private TelephoneDAO telephoneDAO;
 
    @Transactional
    public void addTelephone(Telephone telephone) {
    	telephoneDAO.addTelephone(telephone);
    }
    
    @Transactional
    public void updateTelephone(Telephone telephone){
    	telephoneDAO.updateTelephone(telephone);
    }
 
    @Transactional
    public List<Telephone> listTelephone() {
 
        return telephoneDAO.listTelephone();
    }
 
    @Transactional
    public void removeTelephone(Integer id) {
    	telephoneDAO.removeTelephone(id);
    }
    
    @Transactional
    public Telephone findByUser(Integer id){
    	return telephoneDAO.findByUser(id);
    }
}