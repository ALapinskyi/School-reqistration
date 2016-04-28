package com.interpak.schoolreg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 






import com.interpak.schoolreg.dao.UsersDAO;
import com.interpak.schoolreg.domain.Users;
 
@Service
public class UserServiceImpl implements UserService {
 
    @Autowired
    private UsersDAO userDAO;
 
    @Transactional
    public void addUser(Users user) {
    	userDAO.addUser(user);
    }
    
    @Transactional
    public void updateUser(Users user) {
    	userDAO.updateUser(user);
    }
 
    @Transactional
    public List<Users> listUser() {
 
        return userDAO.listUser();
    }
    
    @Transactional
    public List<Users> listUserByRole() {
 
        return userDAO.listUserByRole();
    }
 
    @Transactional
    public void removeUser(Integer id) {
    	userDAO.removeUser(id);
    }
    
    @Transactional
    public Users findByLogin(String login) {
    	return userDAO.findByLogin(login);
    }
    
    @Transactional
    public Users findById(Integer id){
    	return userDAO.findById(id);
    }
}