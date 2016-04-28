package com.interpak.schoolreg.service;

import java.util.List;

import com.interpak.schoolreg.domain.Users;

public interface UserService {

    public void addUser(Users User);
    
    public void updateUser(Users user);

    public List<Users> listUser();
    
    public List<Users> listUserByRole();

    public void removeUser(Integer id);
    
    public Users findByLogin(String login);
    
    public Users findById(Integer id);
}