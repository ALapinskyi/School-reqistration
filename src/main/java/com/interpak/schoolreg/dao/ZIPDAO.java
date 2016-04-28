package com.interpak.schoolreg.dao;

import java.util.List;
import com.interpak.schoolreg.domain.ZIP;

public interface ZIPDAO {

    public void addZIP(ZIP zip);

    public List<ZIP> listZIP();

    public void removeZIP(Integer id);
    
    public ZIP findById(Integer id);
}