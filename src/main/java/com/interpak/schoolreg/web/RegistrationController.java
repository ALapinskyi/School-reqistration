package com.interpak.schoolreg.web;

import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.interpak.schoolreg.domain.Registration;
import com.interpak.schoolreg.domain.UserAddressId;
import com.interpak.schoolreg.domain.UserSchool;
import com.interpak.schoolreg.domain.UserSchoolId;
import com.interpak.schoolreg.domain.Users;
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
public class RegistrationController {
	
	private String password;
	private String username;
	
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
    
    @RequestMapping("/create-account-page-1")
    public String createAccount1(Map<String, Object> map) {
    	
        return "create-account-1";
    }
    
    @RequestMapping("/create-account-page-2")
    public String createAccount2(Map<String, Object> map) {
    	
    	map.put("countrys", countryService.listCountry());
    	map.put("citys", cityService.listCity());
    	map.put("zips", zipService.listZIP());
        return "create-account-2";
    }
    
    @RequestMapping("/create-account-page-3")
    public String createAccount3(Map<String, Object> map) {
    	
    	map.put("countrys", countryService.listCountry());
    	map.put("citys", cityService.listCity());
    	map.put("zips", zipService.listZIP());
    	map.put("schools", schoolService.listSchool());
        return "create-account-3";
    }
    
    @RequestMapping("/create-account-page-4")
    public String createAccount4(Map<String, Object> map) {

        return "create-account-4";
    }
    
    @RequestMapping(value = "/finish-reg", method = RequestMethod.GET)
    public String finishReg(Map<String, Object> map) {
    	
    	map.put("reg", new Registration());
        return "finish-reg";
    }
    
    @RequestMapping(value = "/accountcreation", method = RequestMethod.POST)
    public String accountCreation(Map<String, Object> map, @ModelAttribute("reg") Registration reg) {
    	    	
    	char[] chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
    	StringBuilder sb = new StringBuilder();
    	Random random = new Random();
    	for (int i = 0; i < 8; i++) {
    	    char c = chars[random.nextInt(chars.length)];
    	    sb.append(c);
    	}
    	password = sb.toString();
    	
    	reg.getUser().setPassword(password);
    	reg.getUser().setiDRole(2);
    	userService.addUser(reg.getUser());
    	Users user = userService.findByLogin(reg.getUser().getUsername());
    	
    	userSchoolService.addUserSchool(new UserSchool(new UserSchoolId(user, schoolService.findById(reg.getSchool().getiDSchool())),
    			reg.getUserschool().getDescription()));
    	
    	reg.getWish().setUserW(user);
    	wishesService.addWishes(reg.getWish());
    	
    	reg.getTelephone().setUserT(user);
    	telephoneService.addTelephone(reg.getTelephone());
    	
    	if(addressService.fingByTextZip(reg.getDataAddress().getTextAdress(), reg.getDataAddress().getiDZip()) == null)
    	{
    		addressService.addAddress(reg.getDataAddress());
    	}
    	
    	reg.getDataUserAddress().setPk(new UserAddressId(user, addressService.fingByTextZip(reg.getDataAddress().getTextAdress(), 
    			reg.getDataAddress().getiDZip())));
    	userAddressService.addUserAddress(reg.getDataUserAddress());
    	
    	if(reg.getAddress1() != null)
    	{
    		if(addressService.fingByTextZip(reg.getAddress1().getTextAdress(), reg.getAddress1().getiDZip()) == null)
    			addressService.addAddress(reg.getAddress1());
    		reg.getUserAddress1().setPk(new UserAddressId(user, addressService.fingByTextZip(reg.getAddress1().getTextAdress(), 
        			reg.getAddress1().getiDZip())));
    		userAddressService.addUserAddress(reg.getUserAddress1());
    	}
    	
    	if(reg.getAddress2() != null)
    	{
    		if(addressService.fingByTextZip(reg.getAddress2().getTextAdress(), reg.getAddress2().getiDZip()) == null)
    			addressService.addAddress(reg.getAddress2());
    		reg.getUserAddress2().setPk(new UserAddressId(user, addressService.fingByTextZip(reg.getAddress2().getTextAdress(), 
        			reg.getAddress2().getiDZip())));
    		userAddressService.addUserAddress(reg.getUserAddress2());
    	}
    	username = user.getUsername();
    	    	
        return "redirect:/finished";
    }
    
    @RequestMapping("/finished")
    public String finished(Map<String, Object> map) {

    	map.put("password", password);
    	map.put("username", username);
        return "finished";
    }

}
