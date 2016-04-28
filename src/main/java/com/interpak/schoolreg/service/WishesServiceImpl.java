package com.interpak.schoolreg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 



import com.interpak.schoolreg.dao.WishesDAO;
import com.interpak.schoolreg.domain.Wishes;
 
@Service
public class WishesServiceImpl implements WishesService {
 
    @Autowired
    private WishesDAO wishesDAO;
 
    @Transactional
    public void addWishes(Wishes wishes) {
    	wishesDAO.addWishes(wishes);
    }

    @Transactional
    public void updateWish(Wishes wishes){
    	wishesDAO.updateWish(wishes);
    }
 
    @Transactional
    public List<Wishes> listWishes() {
 
        return wishesDAO.listWishes();
    }
 
    @Transactional
    public void removeWishes(Integer id) {
    	wishesDAO.removeWishes(id);
    }

    @Transactional
    public Wishes findByUser(Integer id){
    	return wishesDAO.findByUser(id);
    }
}