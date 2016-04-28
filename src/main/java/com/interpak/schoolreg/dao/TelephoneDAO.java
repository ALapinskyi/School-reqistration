package com.interpak.schoolreg.dao;

import java.util.List;
import com.interpak.schoolreg.domain.Telephone;

public interface TelephoneDAO {

    public void addTelephone(Telephone telephone);
    
    public void updateTelephone(Telephone telephone);

    public List<Telephone> listTelephone();

    public void removeTelephone(Integer id);
    
    public Telephone findByUser(Integer id);
}