package com.interpak.schoolreg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 





import com.interpak.schoolreg.dao.UserAddressDAO;
import com.interpak.schoolreg.domain.UserAddress;
 
@Service
public class UserAddressServiceImpl implements UserAddressService {
 
    @Autowired
    private UserAddressDAO userAddressDAO;
 
    @Transactional
    public void addUserAddress(UserAddress userAddress) {
    	userAddressDAO.addUserAddress(userAddress);
    }
    @Transactional
    public void updateUserAddress(UserAddress userAddress)
    {
    	userAddressDAO.updateUserAddress(userAddress);
    }
 
    @Transactional
    public List<UserAddress> listUserAddress() {
 
        return userAddressDAO.listUserAddress();
    }
    
    @Transactional
    public List<UserAddress> listNotMain(Integer id){
    	return userAddressDAO.listNotMain(id);
    }
 
    @Transactional
    public void removeUserAddress(Integer idu, Integer ida) {
    	userAddressDAO.removeUserAddress(idu,ida);
    }
    @Transactional
    public UserAddress findByUserMain(Integer id)
    {
    	return userAddressDAO.findByUserMain(id);
    }
}