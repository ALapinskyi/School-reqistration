package com.interpak.schoolreg.dao;

import java.util.List;
import com.interpak.schoolreg.domain.Users;

public interface UsersDAO {

    public void addUser(Users user);

    public void updateUser(Users user);
    
    public List<Users> listUser();
    
    public List<Users> listUserByRole();

    public void removeUser(Integer id);
    
    public Users findByLogin(String login);
    
    public Users findById(Integer id);
}