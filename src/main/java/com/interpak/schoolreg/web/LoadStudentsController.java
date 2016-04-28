package com.interpak.schoolreg.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
public class LoadStudentsController {
	
	private Integer idU;
	private Boolean valueU;
	
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
	
	@RequestMapping(value = "/angularjs-http-service-ajax-get", method = RequestMethod.GET)
	public ModelAndView httpServiceGetExample( ModelMap model ) {
		return new ModelAndView("httpservice_get");
	}
	
	@RequestMapping(value = "/angularjs-http-service-ajax-post", method = RequestMethod.GET)
	public ModelAndView httpServicePostExample( ModelMap model ) {
		return new ModelAndView("httpservice_post");
	}
	
	 @RequestMapping("/list-of-students")
	    public String listStudents(Map<String, Object> map) {
	    	
	    	List<Users> users = userService.listUserByRole();
	    	map.put("users", users);
	        return "list-of-students";
	    }
	    
	    @RequestMapping("/profiles")
	    public String profiles(Map<String, Object> map) {
	    	
	    	List<Users> users = userService.listUser();
	    	map.put("users", users);
	        return "profiles";
	    }
		
	@RequestMapping(value = "/savecompany", method = RequestMethod.POST)
	public  @ResponseBody String saveCompany( @RequestParam("id") String id, @RequestParam("value") Boolean value) {		
		
		idU = Integer.valueOf(id);
		valueU = value;
		return "0";
	}
	
	@RequestMapping(value = "/getAllProfiles", method = RequestMethod.GET)
	public  @ResponseBody String getAllProfiles( ModelMap model ) {
		
		Users user = userService.findById(idU);
		Wishes wish = wishesService.findByUser(idU);
		Telephone telephone = telephoneService.findByUser(idU);
		
		School school = null;
		ZIP zip = null;
		City city = null;
		UserSchool userSchool = userSchoolService.findByUser(idU);
		if(userSchool != null){
			school = schoolService.findById(userSchool.getSchool().getiDSchool());
			zip = zipService.findById(school.getiDZip());
			city = cityService.findById(zip.getiDCity());
		}
			
		Address address = null;
		ZIP zip2 = null;
		City city2 = null;
		Country country2 = null;
		
		Address add1 = null;
		ZIP z1 = null;
		City c1 = null;
		Country coun1 = null;
		UserAddress uA1 = null;
		
		Address add2 = null;
		ZIP z2 = null;
		City c2 = null;
		Country coun2 = null;
		UserAddress uA2 = null;
		
		UserAddress userAddress = userAddressService.findByUserMain(idU);
		List<UserAddress> userAddress2 = new ArrayList<UserAddress>();
		if(userAddress != null){
			address = addressService.fingById(userAddress.getAddress().getiDAddress());
			zip2 = zipService.findById(address.getiDZip());
			city2 = cityService.findById(zip2.getiDCity());
			country2 = countryService.findById(city.getiDCountry());
			userAddress2 = userAddressService.listNotMain(idU);
			int index = 0;
			if(userAddress2 != null)
			{
				for(UserAddress add : userAddress2)
				{
					if(index == 1)
					{
						uA2 = add;
						add2 = addressService.fingById(uA2.getAddress().getiDAddress());
						z2 = zipService.findById(add2.getiDZip());
						c2 = cityService.findById(z2.getiDCity());
						coun2 = countryService.findById(c2.getiDCountry());
						index++;
					}
					if(index == 0)
					{
						
						uA1 = add;
						add1 = addressService.fingById(uA1.getAddress().getiDAddress());
						z1 = zipService.findById(add1.getiDZip());
						c1 = cityService.findById(z1.getiDCity());
						coun1 = countryService.findById(c1.getiDCountry());
						index++;
					}
					
				}
			}
		}
		
		
		String jsonData;
		if(wish != null){
			if(userSchool != null){
				if(userAddress != null){
					if(uA1 != null)
					{
						if(uA2 != null)
						{
							jsonData = "[{"
									+ "\"name\":\""+user.getName()+"\","
									+ "\"surname\":\""+user.getSurname()+"\","
									+ "\"birthdate\":\""+user.getBirthdate()+"\","
									+ "\"insnumber\":\""+user.getInsnumber()+"\","
									+ "\"gender\":\""+user.getGender()+"\","
									
									+ "\"wishes\":\""+wish.getDescription()+"\","
									
									+ "\"telephone\":\""+telephone.getNumberphone()+"\","
									
									+ "\"schooldesc\":\""+userSchool.getDescription()+"\","
									+ "\"schooltitle\":\""+school.getTitle()+"\","
									+ "\"zipnumber\":\""+zip.getNumber()+"\","
									+ "\"citytitle\":\""+city.getTitle()+"\","
									
									+ "\"maincountry\":\""+country2.getTitle()+"\","
									+ "\"maincity\":\""+city2.getTitle()+"\","
									+ "\"mainzip\":\""+zip2.getNumber()+"\","
									+ "\"textaddress\":\""+address.getTextAdress()+"\","
									+ "\"mainismain\":\""+userAddress.getIsMain()+"\","
									+ "\"maintype\":\""+userAddress.getType()+"\","
									
									+ "\"country1\":\""+coun1.getTitle()+"\","
									+ "\"city1\":\""+c1.getTitle()+"\","
									+ "\"zip1\":\""+z1.getNumber()+"\","
									+ "\"textaddress1\":\""+add1.getTextAdress()+"\","
									+ "\"ismain1\":\""+uA1.getIsMain()+"\","
									+ "\"type1\":\""+uA1.getType()+"\","
									
									+ "\"country2\":\""+coun2.getTitle()+"\","
									+ "\"city2\":\""+c2.getTitle()+"\","
									+ "\"zip2\":\""+z2.getNumber()+"\","
									+ "\"textaddress2\":\""+add2.getTextAdress()+"\","
									+ "\"ismain2\":\""+uA2.getIsMain()+"\","
									+ "\"type2\":\""+uA2.getType()+"\"}]";
						}
						else
						{
							jsonData = "[{"
									+ "\"name\":\""+user.getName()+"\","
									+ "\"surname\":\""+user.getSurname()+"\","
									+ "\"birthdate\":\""+user.getBirthdate()+"\","
									+ "\"insnumber\":\""+user.getInsnumber()+"\","
									+ "\"gender\":\""+user.getGender()+"\","
									
									+ "\"wishes\":\""+wish.getDescription()+"\","
									
									+ "\"telephone\":\""+telephone.getNumberphone()+"\","
									
									+ "\"schooldesc\":\""+userSchool.getDescription()+"\","
									+ "\"schooltitle\":\""+school.getTitle()+"\","
									+ "\"zipnumber\":\""+zip.getNumber()+"\","
									+ "\"citytitle\":\""+city.getTitle()+"\","
									
									+ "\"maincountry\":\""+country2.getTitle()+"\","
									+ "\"maincity\":\""+city2.getTitle()+"\","
									+ "\"mainzip\":\""+zip2.getNumber()+"\","
									+ "\"textaddress\":\""+address.getTextAdress()+"\","
									+ "\"mainismain\":\""+userAddress.getIsMain()+"\","
									+ "\"maintype\":\""+userAddress.getType()+"\","
									
									+ "\"country1\":\""+coun1.getTitle()+"\","
									+ "\"city1\":\""+c1.getTitle()+"\","
									+ "\"zip1\":\""+z1.getNumber()+"\","
									+ "\"textaddress1\":\""+add1.getTextAdress()+"\","
									+ "\"ismain1\":\""+uA1.getIsMain()+"\","
									+ "\"type1\":\""+uA1.getType()+"\"}]";
						}
					}
					else
					{
						jsonData = "[{"
								+ "\"name\":\""+user.getName()+"\","
								+ "\"surname\":\""+user.getSurname()+"\","
								+ "\"birthdate\":\""+user.getBirthdate()+"\","
								+ "\"insnumber\":\""+user.getInsnumber()+"\","
								+ "\"gender\":\""+user.getGender()+"\","
								
								+ "\"wishes\":\""+wish.getDescription()+"\","
								
								+ "\"telephone\":\""+telephone.getNumberphone()+"\","
								
								+ "\"schooldesc\":\""+userSchool.getDescription()+"\","
								+ "\"schooltitle\":\""+school.getTitle()+"\","
								+ "\"zipnumber\":\""+zip.getNumber()+"\","
								+ "\"citytitle\":\""+city.getTitle()+"\","
								
								+ "\"maincountry\":\""+country2.getTitle()+"\","
								+ "\"maincity\":\""+city2.getTitle()+"\","
								+ "\"mainzip\":\""+zip2.getNumber()+"\","
								+ "\"textaddress\":\""+address.getTextAdress()+"\","
								+ "\"mainismain\":\""+userAddress.getIsMain()+"\","
								+ "\"maintype\":\""+userAddress.getType()+"\"}]";
					}
					
			}
			else
			{
				jsonData = "[{"
						+ "\"name\":\""+user.getName()+"\","
						+ "\"surname\":\""+user.getSurname()+"\","
						+ "\"birthdate\":\""+user.getBirthdate()+"\","
						+ "\"insnumber\":\""+user.getInsnumber()+"\","
						+ "\"gender\":\""+user.getGender()+"\","
						
						+ "\"wishes\":\""+wish.getDescription()+"\","
						
						+ "\"telephone\":\""+telephone.getNumberphone()+"\","
						
						+ "\"schooldesc\":\""+userSchool.getDescription()+"\","
						+ "\"schooltitle\":\""+school.getTitle()+"\","
						+ "\"zipnumber\":\""+zip.getNumber()+"\","
						+ "\"citytitle\":\""+city.getTitle()+"\"}]";
			}
			}
			else
			{
				jsonData = "[{"
						+ "\"name\":\""+user.getName()+"\","
						+ "\"surname\":\""+user.getSurname()+"\","
						+ "\"birthdate\":\""+user.getBirthdate()+"\","
						+ "\"insnumber\":\""+user.getInsnumber()+"\","
						+ "\"gender\":\""+user.getGender()+"\","
						
						+ "\"wishes\":\""+wish.getDescription()+"\","
						
						+ "\"telephone\":\""+telephone.getNumberphone()+"\"}]";
			}
		}
		else
		{
			jsonData = "[{"
					+ "\"name\":\""+user.getName()+"\","
					+ "\"surname\":\""+user.getSurname()+"\","
					+ "\"birthdate\":\""+user.getBirthdate()+"\","
					+ "\"insnumber\":\""+user.getInsnumber()+"\","
					+ "\"gender\":\""+user.getGender()+"\","
					
					+ "\"telephone\":\""+telephone.getNumberphone()+"\"}]";
		}
		return jsonData;
	}
}
