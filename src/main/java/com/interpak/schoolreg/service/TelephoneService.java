package com.interpak.schoolreg.service;

import java.util.List;

import com.interpak.schoolreg.domain.Telephone;

public interface TelephoneService {

    public void addTelephone(Telephone telephone);
    
    public void updateTelephone(Telephone telephone);

    public List<Telephone> listTelephone();

    public void removeTelephone(Integer id);
    
    public Telephone findByUser(Integer id);
}