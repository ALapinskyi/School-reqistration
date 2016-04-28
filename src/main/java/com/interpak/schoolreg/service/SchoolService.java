package com.interpak.schoolreg.service;

import java.util.List;

import com.interpak.schoolreg.domain.School;
import com.interpak.schoolreg.domain.Users;

public interface SchoolService {

    public void addSchool(School school);

    public List<School> listSchool();

    public void removeSchool(Integer id);
    
    public School findById(Integer id);
}