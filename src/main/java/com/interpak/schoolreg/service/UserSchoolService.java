package com.interpak.schoolreg.service;

import java.util.List;

import com.interpak.schoolreg.domain.UserSchool;

public interface UserSchoolService {

    public void addUserSchool(UserSchool userSchool);

    public void updateUserSchool(UserSchool userSchool);

    public List<UserSchool> listUserSchool();

    public void removeUserSchool(Integer idu, Integer ids);
    
    public UserSchool findByUser(Integer id);
}