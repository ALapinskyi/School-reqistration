package com.interpak.schoolreg.dao;

import java.util.List;

import com.interpak.schoolreg.domain.Country;
import com.interpak.schoolreg.domain.School;
import com.interpak.schoolreg.domain.Users;

public interface SchoolDAO {

    public void addSchool(School school);

    public List<School> listSchool();

    public void removeSchool(Integer id);
    
    public School findById(Integer id);
}