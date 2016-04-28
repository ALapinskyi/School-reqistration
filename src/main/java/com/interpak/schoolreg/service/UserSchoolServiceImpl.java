package com.interpak.schoolreg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 



import com.interpak.schoolreg.dao.UserSchoolDAO;
import com.interpak.schoolreg.domain.UserSchool;
 
@Service
public class UserSchoolServiceImpl implements UserSchoolService {
 
    @Autowired
    private UserSchoolDAO userSchoolDAO;
 
    @Transactional
    public void addUserSchool(UserSchool userSchool) {
    	userSchoolDAO.addUserSchool(userSchool);
    }
    
    @Transactional
    public void updateUserSchool(UserSchool userSchool){
    	userSchoolDAO.updateUserSchool(userSchool);
    }
 
    @Transactional
    public List<UserSchool> listUserSchool() {
 
        return userSchoolDAO.listUserSchool();
    }
 
    @Transactional
    public void removeUserSchool(Integer idu, Integer ids) {
    	userSchoolDAO.removeUserSchool(idu, ids);
    }
    
    @Transactional
    public UserSchool findByUser(Integer id){
    	return userSchoolDAO.findByUser(id);
    }
}