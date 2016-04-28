package com.interpak.schoolreg.service;

import java.util.List;

import com.interpak.schoolreg.domain.Users;
import com.interpak.schoolreg.domain.Wishes;

public interface WishesService {

    public void addWishes(Wishes wishes);
    
    public void updateWish(Wishes wishes);

    public List<Wishes> listWishes();

    public void removeWishes(Integer id);
    
    public Wishes findByUser(Integer id);
}