package com.interpak.schoolreg.web;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.interpak.schoolreg.domain.Address;
import com.interpak.schoolreg.domain.City;
import com.interpak.schoolreg.domain.Country;
import com.interpak.schoolreg.domain.Registration;
import com.interpak.schoolreg.domain.School;
import com.interpak.schoolreg.domain.SchoolUpdate;
import com.interpak.schoolreg.domain.Telephone;
import com.interpak.schoolreg.domain.UserAddress;
import com.interpak.schoolreg.domain.UserAddressId;
import com.interpak.schoolreg.domain.UserSchool;
import com.interpak.schoolreg.domain.UserSchoolId;
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

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserProfileController {

	private Integer idU;
	private Users user;
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
    
    @RequestMapping("/")
    public String home(Map<String, Object> map) {
    	
    	Authentication authentication = 
                SecurityContextHolder.getContext().getAuthentication();
 		if (authentication != null) {
 			System.out.println(authentication.getName());
 			if(authentication.getName().equals("admin"))
 			{
 		        return "redirect:/list-of-students";
 			}
 			else if(!authentication.getName().equals("guest"))
 			{
 				return "redirect:/myprofile";
 			}
 			
 		}
        return "home";
    }
    
    @RequestMapping("/index")
    public String index(Map<String, Object> map) {
    	
        return "home";
    }
    
    @RequestMapping("/error403")
    public String error403(Map<String, Object> map) {
    	
        return "error403";
    }
    
    @RequestMapping("/myprofile")
    public String myProfile(Map<String, Object> map) {
    	
    	if(user.getiDRole() == 1)
    		return "redirect:/list-of-students";
    	
    	map.put("countrys", countryService.listCountry());
    	map.put("cities", cityService.listCity());
    	map.put("zips", zipService.listZIP());
    	map.put("schools", schoolService.listSchool());
    	map.put("user", user);
    	
    	String[] genders = {"male", "female"};
    	map.put("genders", genders);
    	
    	Wishes wish = wishesService.findByUser(user.getiDUser());
    	map.put("wish", wish);
    	
    	//UserAddress ua = userAddressService.findByUser(user.getiDUser());
    	
    	UserSchool us = userSchoolService.findByUser(user.getiDUser());
    	SchoolUpdate schUpd = new SchoolUpdate(schoolService.findById(us.getSchool().getiDSchool()),us);
    	map.put("schoolForm", schUpd);
    	
        return "myprofile";
    }
    
    
    
    
    
   
    @ModelAttribute("user")
    public Users addUserInfoToRequest(Model model) {
    	Authentication auth = SecurityContextHolder.getContext()
 				.getAuthentication();
    	user = new Users();
    	if (!(auth instanceof AnonymousAuthenticationToken)) {
    		String login = auth.getName();
    		user = userService.findByLogin(login);
    	}
     	return user;
    }
    
    
    
    ////load and update data in profile/main info
    
    @RequestMapping(value = "/loadprofile1", method = RequestMethod.POST)
	public  @ResponseBody String loadprofile1( @RequestParam("id") String id) {		
		
		idU = Integer.valueOf(id);
		return "0";
	}
	
	@RequestMapping(value = "/getprofile1", method = RequestMethod.GET)
	public  @ResponseBody String getprofile1( ModelMap model ) {
		
		user = userService.findById(idU);
		String jsonData = "[{"
				+ "\"name\":\""+user.getName()+"\","
				+ "\"surname\":\""+user.getSurname()+"\","
				+ "\"birthdate\":\""+user.getBirthdate()+"\","
				+ "\"insnumber\":\""+user.getInsnumber()+"\","
				+ "\"gender\":\""+user.getGender()+"\"}]";
			
		return jsonData;
	}
	
	@RequestMapping(value = "/saveprofile1", method = RequestMethod.POST)
	public  @ResponseBody String saveprofile1( @RequestParam("name") String name, @RequestParam("surname") String surname, 
			@RequestParam("insnumber") String insnumber, @RequestParam("gender") String gender, @RequestParam("birth") String birthdate) throws ParseException {		
		user.setName(name);
		user.setSurname(surname);
		
		
		user.setInsnumber(insnumber);
		if(insnumber != "")
    		user.setInsnumber(user.getInsnumber());
    	else
    		user.setInsnumber(null);
    	if(gender.equals("male")|| gender.equals("female"))
    		user.setGender(gender);
    	userService.updateUser(user);
		return "0";
	}
	
	//// 2/4 load and update data in profile/addresses
	
	 @RequestMapping(value = "/loadprofile2", method = RequestMethod.POST)
		public  @ResponseBody String loadprofile2( @RequestParam("id") String id) {		
			
			idU = Integer.valueOf(id);
			return "0";
		}
		
		@RequestMapping(value = "/getprofile2", method = RequestMethod.GET)
		public  @ResponseBody String getprofile2( ModelMap model ) {
			
			Telephone telephone = telephoneService.findByUser(idU);
			
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
				country2 = countryService.findById(city2.getiDCountry());
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
					if(userAddress != null)
					{
						if(uA1 != null)
						{
							if(uA2 != null)
							{
								jsonData = "[{"
										
										+ "\"telephone\":\""+telephone.getNumberphone()+"\","
																				
										+ "\"maincountry\":\""+country2.getiDCountry()+"\","
										+ "\"maincity\":\""+city2.getiDCity()+"\","
										+ "\"mainzip\":\""+zip2.getiDZIP()+"\","
										+ "\"mainzipNumber\":\""+zip2.getNumber()+"\","
										+ "\"textaddress\":\""+address.getTextAdress()+"\","
										+ "\"mainismain\":\""+userAddress.getIsMain()+"\","
										+ "\"maintype\":\""+userAddress.getType()+"\","
										
										+ "\"country1\":\""+coun1.getiDCountry()+"\","
										+ "\"city1\":\""+c1.getiDCity()+"\","
										+ "\"zip1\":\""+z1.getiDZIP()+"\","
										+ "\"zip1number\":\""+z1.getNumber()+"\","
										+ "\"textaddress1\":\""+add1.getTextAdress()+"\","
										+ "\"ismain1\":\""+uA1.getIsMain()+"\","
										+ "\"type1\":\""+uA1.getType()+"\","
										
										+ "\"country2\":\""+coun2.getiDCountry()+"\","
										+ "\"city2\":\""+c2.getiDCity()+"\","
										+ "\"zip2\":\""+z2.getiDZIP()+"\","
										+ "\"zip2number\":\""+z2.getNumber()+"\","
										+ "\"textaddress2\":\""+add2.getTextAdress()+"\","
										+ "\"ismain2\":\""+uA2.getIsMain()+"\","
										+ "\"type2\":\""+uA2.getType()+"\"}]";
							}
							else
							{
								jsonData = "[{"
										
										+ "\"telephone\":\""+telephone.getNumberphone()+"\","
										
										+ "\"maincountry\":\""+country2.getiDCountry()+"\","
										+ "\"maincity\":\""+city2.getiDCity()+"\","
										+ "\"mainzip\":\""+zip2.getiDZIP()+"\","
										+ "\"mainzipNumber\":\""+zip2.getNumber()+"\","
										+ "\"textaddress\":\""+address.getTextAdress()+"\","
										+ "\"mainismain\":\""+userAddress.getIsMain()+"\","
										+ "\"maintype\":\""+userAddress.getType()+"\","
										
										+ "\"country1\":\""+coun1.getiDCountry()+"\","
										+ "\"city1\":\""+c1.getiDCity()+"\","
										+ "\"zip1\":\""+z1.getiDZIP()+"\","
										+ "\"zip1number\":\""+z1.getNumber()+"\","
										+ "\"textaddress1\":\""+add1.getTextAdress()+"\","
										+ "\"ismain1\":\""+uA1.getIsMain()+"\","
										+ "\"type1\":\""+uA1.getType()+"\"}]";
							}
						}
						else
						{
							jsonData = "[{"
									
									+ "\"telephone\":\""+telephone.getNumberphone()+"\","
									
									+ "\"maincountry\":\""+country2.getiDCountry()+"\","
									+ "\"maincity\":\""+city2.getiDCity()+"\","
									+ "\"mainzip\":\""+zip2.getiDZIP()+"\","
									+ "\"mainzipNumber\":\""+zip2.getNumber()+"\","
									+ "\"textaddress\":\""+address.getTextAdress()+"\","
									+ "\"mainismain\":\""+userAddress.getIsMain()+"\","
									+ "\"maintype\":\""+userAddress.getType()+"\"}]";
						}
				}
				else
				{
					jsonData = "[{"
							
							+ "\"telephone\":\""+telephone.getNumberphone()+"\"}]";
				}
			return jsonData;
		}
		
		@RequestMapping(value = "/saveprofile2", method = RequestMethod.POST)
		public  @ResponseBody String saveprofile2(  @RequestParam("number") String number, @RequestParam("telephone") String phone,
													@RequestParam("maintext") String maintext, @RequestParam("maintype") String maintype,
													@RequestParam("mainismain") String mainismain, @RequestParam("mainzip") String mainzip,
													@RequestParam("text1") String text1, @RequestParam("type1") String type1,
													@RequestParam("ismain1") String ismain1, @RequestParam("zip1") String zip1,
													@RequestParam("text2") String text2, @RequestParam("type2") String type2,
													@RequestParam("ismain2") String ismain2, @RequestParam("zip2") String zip2) 
												throws ParseException {		
			
			Telephone telephone = telephoneService.findByUser(idU);
			if(!telephone.getNumberphone().equals(phone))
			{
				telephone.setNumberphone(phone);
				telephoneService.updateTelephone(telephone);
				System.out.println("upda");
			}
			if(number.equals("0"))
			{
				List<UserAddress> userAddress2 = new ArrayList<UserAddress>();
				userAddress2 = userAddressService.listNotMain(idU);
				if(userAddress2 != null)
				{
					for(UserAddress ua : userAddress2)
					{
						userAddressService.removeUserAddress(ua.getUser().getiDUser(), ua.getAddress().getiDAddress());
					}
				}
				UserAddress userAddress = userAddressService.findByUserMain(idU);
				Address address = addressService.fingById(userAddress.getAddress().getiDAddress());
				if(!maintext.equals(address.getTextAdress()) || Integer.valueOf(mainzip).compareTo(address.getiDZip()) != 0)
				{
					
					userAddressService.removeUserAddress(user.getiDUser(),address.getiDAddress());
					addressService.removeAddress(address.getiDAddress());
					Address add = new Address();
					add.setiDZip(Integer.valueOf(mainzip));
					add.setTextAdress(maintext);
					addressService.addAddress(add);
					add = addressService.fingByTextZip(maintext, Integer.valueOf(mainzip));
					UserAddress newAddress = new UserAddress(new UserAddressId(user,add),mainismain,maintype);
					userAddressService.addUserAddress(newAddress);
					
				}
				else
				{
					userAddress.setIsMain(mainismain);
					userAddress.setType(maintype);
					userAddressService.updateUserAddress(userAddress);
				}
			}
			if(number.equals("1"))
			{
				UserAddress userAddress = userAddressService.findByUserMain(idU);
				Address address = addressService.fingById(userAddress.getAddress().getiDAddress());
				if(!maintext.equals(address.getTextAdress()) || Integer.valueOf(mainzip).compareTo(address.getiDZip()) != 0)
				{
					userAddressService.removeUserAddress(user.getiDUser(),address.getiDAddress());
					addressService.removeAddress(address.getiDAddress());
					Address add = new Address();
					add.setiDZip(Integer.valueOf(mainzip));
					add.setTextAdress(maintext);
					addressService.addAddress(add);
					add = addressService.fingByTextZip(maintext, Integer.valueOf(mainzip));
					UserAddress newAddress = new UserAddress(new UserAddressId(user,add),mainismain,maintype);
					userAddressService.addUserAddress(newAddress);
					
				}
				else
				{
					userAddress.setIsMain(mainismain);
					userAddress.setType(maintype);
					userAddressService.updateUserAddress(userAddress);
				}
				
				List<UserAddress> userAddress2 = new ArrayList<UserAddress>();
				userAddress2 = userAddressService.listNotMain(idU);
				if(userAddress2.size() == 0)
				{
					Address add = new Address();
					add.setiDZip(Integer.valueOf(zip1));
					add.setTextAdress(text1);
					addressService.addAddress(add);
					add = addressService.fingByTextZip(text1, Integer.valueOf(zip1));
					UserAddress ua = new UserAddress(new UserAddressId(user, add),ismain1,type1);
					userAddressService.addUserAddress(ua);
				}
				if(userAddress2.size() == 1)
				{
					for(UserAddress u: userAddress2)
					{
						if(!u.getAddress().getTextAdress().equals(text1))
						{
							userAddressService.removeUserAddress(user.getiDUser(), u.getAddress().getiDAddress());
							addressService.removeAddress(u.getAddress().getiDAddress());
							Address add = new Address();
							add.setiDZip(Integer.valueOf(zip1));
							add.setTextAdress(text1);
							addressService.addAddress(add);
							add = addressService.fingByTextZip(text1, Integer.valueOf(zip1));
							UserAddress ua = new UserAddress(new UserAddressId(user, add),ismain1,type1);
							userAddressService.addUserAddress(ua);
							
						}
						else
						{
							u.setIsMain(ismain1);
							u.setType(type1);
							userAddressService.updateUserAddress(u);
						}
					}
				}
				
				int index = 0;
				if(userAddress2.size() == 2)
				{
					for(UserAddress u: userAddress2)
					{
						if(u.getAddress().getTextAdress().equals(text1))
						{
							u.setIsMain(ismain1);
							u.setType(type1);
							userAddressService.updateUserAddress(u);
							index++;
						}
						else
						{
							
							userAddressService.removeUserAddress(user.getiDUser(), u.getAddress().getiDAddress());
							addressService.removeAddress(u.getAddress().getiDAddress());
						}
					}
					if(index == 0)
					{
						Address add = new Address();
						add.setiDZip(Integer.valueOf(zip1));
						add.setTextAdress(text1);
						addressService.addAddress(add);
						add = addressService.fingByTextZip(text1, Integer.valueOf(zip1));
						UserAddress ua = new UserAddress(new UserAddressId(user, add),ismain1,type1);
						userAddressService.addUserAddress(ua);
					}
				}
			}
				if(number.equals("2"))
				{
					UserAddress userAddress3 = userAddressService.findByUserMain(idU);
					Address address2 = addressService.fingById(userAddress3.getAddress().getiDAddress());
					if(!maintext.equals(address2.getTextAdress()) || Integer.valueOf(mainzip).compareTo(address2.getiDZip()) != 0)
					{
						userAddressService.removeUserAddress(user.getiDUser(),address2.getiDAddress());
						addressService.removeAddress(address2.getiDAddress());
						Address add = new Address();
						add.setiDZip(Integer.valueOf(mainzip));
						add.setTextAdress(maintext);
						addressService.addAddress(add);
						add = addressService.fingByTextZip(maintext, Integer.valueOf(mainzip));
						UserAddress newAddress = new UserAddress(new UserAddressId(user,add),mainismain,maintype);
						userAddressService.addUserAddress(newAddress);
						
					}
					else
					{
						userAddress3.setIsMain(mainismain);
						userAddress3.setType(maintype);
						userAddressService.updateUserAddress(userAddress3);
					}
					
					List<UserAddress> userAddress4 = new ArrayList<UserAddress>();
					userAddress4 = userAddressService.listNotMain(idU);
					if(userAddress4.size() == 0)
					{
						Address add = new Address();
						add.setiDZip(Integer.valueOf(zip1));
						add.setTextAdress(text1);
						addressService.addAddress(add);
						add = addressService.fingByTextZip(text1, Integer.valueOf(zip1));
						UserAddress ua = new UserAddress(new UserAddressId(user, add),ismain1,type1);
						userAddressService.addUserAddress(ua);
						
						Address add2 = new Address();
						add2.setiDZip(Integer.valueOf(zip2));
						add2.setTextAdress(text2);
						addressService.addAddress(add2);
						add2 = addressService.fingByTextZip(text2, Integer.valueOf(zip2));
						UserAddress ua2 = new UserAddress(new UserAddressId(user, add2),ismain2,type2);
						userAddressService.addUserAddress(ua2);
					}
					if(userAddress4.size() == 1)
					{	
						int index3 = 0;
						for(UserAddress u: userAddress4)
						{
							
							if(u.getAddress().getTextAdress().equals(text1))
							{
								index3++;
								u.setIsMain(ismain1);
								u.setType(type1);
								userAddressService.updateUserAddress(u);
								
								Address add = new Address();
								add.setiDZip(Integer.valueOf(zip2));
								add.setTextAdress(text2);
								addressService.addAddress(add);
								add = addressService.fingByTextZip(text2, Integer.valueOf(zip2));
								UserAddress ua = new UserAddress(new UserAddressId(user, add),ismain2,type2);
								userAddressService.addUserAddress(ua);
							}
							if(u.getAddress().getTextAdress().equals(text2))
							{
								index3++;
								u.setIsMain(ismain2);
								u.setType(type2);
								userAddressService.updateUserAddress(u);
								
								Address add = new Address();
								add.setiDZip(Integer.valueOf(zip1));
								add.setTextAdress(text1);
								addressService.addAddress(add);
								add = addressService.fingByTextZip(text1, Integer.valueOf(zip1));
								UserAddress ua = new UserAddress(new UserAddressId(user, add),ismain1,type1);
								userAddressService.addUserAddress(ua);
							}
						}
						if(index3 == 0)
						{
							for(UserAddress u: userAddress4)
							{
								userAddressService.removeUserAddress(user.getiDUser(), u.getAddress().getiDAddress());
								addressService.removeAddress(u.getAddress().getiDAddress());
							}
							
							Address add = new Address();
							add.setiDZip(Integer.valueOf(zip1));
							add.setTextAdress(text1);
							addressService.addAddress(add);
							add = addressService.fingByTextZip(text1, Integer.valueOf(zip1));
							UserAddress ua = new UserAddress(new UserAddressId(user, add),ismain1,type1);
							userAddressService.addUserAddress(ua);
							
							Address add2 = new Address();
							add2.setiDZip(Integer.valueOf(zip2));
							add2.setTextAdress(text2);
							addressService.addAddress(add2);
							add2 = addressService.fingByTextZip(text2, Integer.valueOf(zip2));
							UserAddress ua2 = new UserAddress(new UserAddressId(user, add2),ismain2,type2);
							userAddressService.addUserAddress(ua2);
						}
					}
					
					int index2 = 0;
					if(userAddress4.size() == 2)
					{
						for(UserAddress u: userAddress4)
						{
							if(u.getAddress().getTextAdress().equals(text1))
							{
								index2++;
								u.setIsMain(ismain1);
								u.setType(type1);
								userAddressService.updateUserAddress(u);
							}
							if(u.getAddress().getTextAdress().equals(text2))
							{
								index2++;
								u.setIsMain(ismain2);
								u.setType(type2);
								userAddressService.updateUserAddress(u);
							}
						}
						if(index2 == 1)
						{
							int nummer = 0;
							int addnum = 0;
							for(UserAddress u: userAddress4)
							{
								if(u.getAddress().getTextAdress().equals(text1))
								{
									nummer = 1;
								}
								if(u.getAddress().getTextAdress().equals(text2))
								{
									nummer = 2;
								}
							}
							if(nummer == 1)
							{
								userAddressService.removeUserAddress(user.getiDUser(), userAddress4.get(1).getAddress().getiDAddress());
								addressService.removeAddress(userAddress4.get(1).getAddress().getiDAddress());
								
								Address add2 = new Address();
								add2.setiDZip(Integer.valueOf(zip2));
								add2.setTextAdress(text2);
								addressService.addAddress(add2);
								add2 = addressService.fingByTextZip(text2, Integer.valueOf(zip2));
								UserAddress ua2 = new UserAddress(new UserAddressId(user, add2),ismain2,type2);
								userAddressService.addUserAddress(ua2);
							}
							if(nummer == 2)
							{
								userAddressService.removeUserAddress(user.getiDUser(), userAddress4.get(0).getAddress().getiDAddress());
								addressService.removeAddress(userAddress4.get(0).getAddress().getiDAddress());
								
								Address add = new Address();
								add.setiDZip(Integer.valueOf(zip1));
								add.setTextAdress(text1);
								addressService.addAddress(add);
								add = addressService.fingByTextZip(text1, Integer.valueOf(zip1));
								UserAddress ua = new UserAddress(new UserAddressId(user, add),ismain1,type1);
								userAddressService.addUserAddress(ua);
							}
						}
						if(index2 == 0)
						{
							for(UserAddress u: userAddress4)
							{
								userAddressService.removeUserAddress(user.getiDUser(), u.getAddress().getiDAddress());
								addressService.removeAddress(u.getAddress().getiDAddress());
							}
							
							Address add = new Address();
							add.setiDZip(Integer.valueOf(zip1));
							add.setTextAdress(text1);
							addressService.addAddress(add);
							add = addressService.fingByTextZip(text1, Integer.valueOf(zip1));
							UserAddress ua = new UserAddress(new UserAddressId(user, add),ismain1,type1);
							userAddressService.addUserAddress(ua);
							
							Address add2 = new Address();
							add2.setiDZip(Integer.valueOf(zip2));
							add2.setTextAdress(text2);
							addressService.addAddress(add2);
							add2 = addressService.fingByTextZip(text2, Integer.valueOf(zip2));
							UserAddress ua2 = new UserAddress(new UserAddressId(user, add2),ismain2,type2);
							userAddressService.addUserAddress(ua2);
						}
					}
				
			}
			
			return "0";
		}
	
	//// 3/4 load and update data in profile/school
	
		
	 @RequestMapping(value = "/loadprofile3", method = RequestMethod.POST)
		public  @ResponseBody String loadprofile3( @RequestParam("id") String id) {		
			
			idU = Integer.valueOf(id);
			return "0";
		}
		
		@RequestMapping(value = "/getprofile3", method = RequestMethod.GET)
		public  @ResponseBody String getprofile3( ModelMap model ) throws UnsupportedEncodingException {
			
			UserSchool userSchool = userSchoolService.findByUser(user.getiDUser());
			School school = schoolService.findById(userSchool.getSchool().getiDSchool());
			
			byte[] bytes = school.getTitle().getBytes("UTF-8");
			String title = new String(bytes,"UTF-8");
		
			String jsonData = "[{"
					+ "\"zip\":\""+school.getiDZip()+"\","
					+ "\"school\":\""+title+"\","
					+ "\"id\":\""+school.getiDSchool()+"\","
					+ "\"description\":\""+userSchool.getDescription()+"\"}]";
				
			return jsonData;
		}
		
		@RequestMapping(value = "/saveprofile3", method = RequestMethod.POST)
		public  @ResponseBody String saveprofile3( @RequestParam("zip") String zip,
				@RequestParam("id") String id, @RequestParam("description") String description) throws ParseException {		
			
			UserSchool userSchool = userSchoolService.findByUser(user.getiDUser());
			if(Integer.valueOf(id).compareTo(userSchool.getSchool().getiDSchool()) == 0)
			{
				userSchool.setDescription(description);
				userSchoolService.updateUserSchool(userSchool);
			}
			else
			{
				
				userSchoolService.addUserSchool(
						new UserSchool(
								new UserSchoolId(user, schoolService.findById(Integer.valueOf(id))),description));
				userSchoolService.removeUserSchool(idU, userSchool.getSchool().getiDSchool());
			}
			
			return "0";
		}
	
	
	//// 4/4 load and update data in profile/wishes
	
	 @RequestMapping(value = "/loadprofile4", method = RequestMethod.POST)
		public  @ResponseBody String loadprofile4( @RequestParam("id") String id) {		
			
			idU = Integer.valueOf(id);
			return "0";
		}
		
		@RequestMapping(value = "/getprofile4", method = RequestMethod.GET)
		public  @ResponseBody String getprofile4( ModelMap model ) {
			
			Wishes wish = wishesService.findByUser(idU);
			String jsonData = "[{"
					+ "\"description\":\""+wish.getDescription()+"\"}]";
				
			return jsonData;
		}
		
		@RequestMapping(value = "/saveprofile4", method = RequestMethod.POST)
		public  @ResponseBody String saveprofile4( @RequestParam("wish") String wish) throws ParseException {	
			System.out.println(wish);
			Wishes wishU = wishesService.findByUser(idU);
			wishU.setDescription(wish);
			wishesService.updateWish(wishU);
			return "0";
		}
}