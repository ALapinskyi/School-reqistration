package com.interpak.schoolreg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 



import com.interpak.schoolreg.dao.ZIPDAO;
import com.interpak.schoolreg.domain.ZIP;
 
@Service
public class ZIPServiceImpl implements ZIPService {
 
    @Autowired
    private ZIPDAO zipDAO;
 
    @Transactional
    public void addZIP(ZIP ZIP) {
    	zipDAO.addZIP(ZIP);
    }
 
    @Transactional
    public List<ZIP> listZIP() {
 
        return zipDAO.listZIP();
    }
 
    @Transactional
    public void removeZIP(Integer id) {
    	zipDAO.removeZIP(id);
    }
    
    @Transactional
    public ZIP findById(Integer id){
    	return zipDAO.findById(id);
    }
}