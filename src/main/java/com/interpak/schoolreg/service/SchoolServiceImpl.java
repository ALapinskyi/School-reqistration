package com.interpak.schoolreg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 




import com.interpak.schoolreg.dao.SchoolDAO;
import com.interpak.schoolreg.domain.School;
import com.interpak.schoolreg.domain.Users;
 
@Service
public class SchoolServiceImpl implements SchoolService {
 
    @Autowired
    private SchoolDAO schoolDAO;
 
    @Transactional
    public void addSchool(School school) {
    	schoolDAO.addSchool(school);
    }
 
    @Transactional
    public List<School> listSchool() {
 
        return schoolDAO.listSchool();
    }
 
    @Transactional
    public void removeSchool(Integer id) {
    	schoolDAO.removeSchool(id);
    }
    
    @Transactional
    public School findById(Integer id) {
    	return schoolDAO.findById(id);
    }
}