package com.interpak.schoolreg.dao;

import java.util.List;

import com.interpak.schoolreg.domain.Address;
import com.interpak.schoolreg.domain.UserAddress;

public interface UserAddressDAO {

    public void addUserAddress(UserAddress userAddress);
    
    public void updateUserAddress(UserAddress userAddress);

    public List<UserAddress> listUserAddress();
    
    public List<UserAddress> listNotMain(Integer id);

    public void removeUserAddress(Integer idu, Integer ida);
    
    public UserAddress findByUserMain(Integer id);
}