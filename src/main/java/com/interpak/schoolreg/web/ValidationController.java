package com.interpak.schoolreg.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.interpak.schoolreg.domain.Address;
import com.interpak.schoolreg.domain.City;
import com.interpak.schoolreg.domain.Country;
import com.interpak.schoolreg.domain.School;
import com.interpak.schoolreg.domain.Telephone;
import com.interpak.schoolreg.domain.UserAddress;
import com.interpak.schoolreg.domain.UserSchool;
import com.interpak.schoolreg.domain.Users;
import com.interpak.schoolreg.domain.Wishes;
import com.interpak.schoolreg.domain.ZIP;
import com.interpak.schoolreg.service.AddressService;
import com.interpak.schoolreg.service.CityService;
import com.interpak.schoolreg.service.CountryService;
import com.interpak.schoolreg.service.SchoolService;
import com.interpak.schoolreg.service.TelephoneService;
import com.interpak.schoolreg.service.UserAddressService;
import com.interpak.schoolreg.service.UserSchoolService;
import com.interpak.schoolreg.service.UserService;
import com.interpak.schoolreg.service.WishesService;
import com.interpak.schoolreg.service.ZIPService;


@Controller
public class ValidationController {
	
	 @Autowired
	    private CountryService countryService;
	    
	    @Autowired
	    private CityService cityService;
	    
	    @Autowired
	    private ZIPService zipService;
	    
	    @Autowired
	    private SchoolService schoolService;
	    
	    @Autowired
	    private UserService userService;
	    
	    @Autowired
	    private UserSchoolService userSchoolService;
	    
	    @Autowired
	    private WishesService wishesService;
	    
	    @Autowired
	    private TelephoneService telephoneService;
	    
	    @Autowired
	    private AddressService addressService;
	    
	    @Autowired
	    private UserAddressService userAddressService;

	@RequestMapping(value = "/checkusername", method = RequestMethod.POST)
	public  @ResponseBody String checkusername( @RequestParam("username") String username) {		
		
		Users user = userService.findByLogin(username);
		if(user == null)
		{
			return "0";
		}
		else
		{
			return username;
		}
		
	}
	
}
