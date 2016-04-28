//module for storage data by registration in sessionStorage and scope
// 		ui.bootstrap	bootstrap library	
// 		ngStorage		store data in sessionStorage
//		ngRoute			provides routing and deeplinking services and directives
//		ngResource		send data by ajax

var storage = angular.module('storage', [ 'ui.bootstrap', 'ngStorage', 'ngResource']);

storage.config(['$httpProvider', function ($httpProvider) {    
	$httpProvider.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded; charset=UTF-8';
}]);



//ctrl for store data by registration or data editing in profile
storage.controller('StorageCtrl',function($scope, $sessionStorage) {

					$scope.selectedCity = "";
					$scope.visible = true;
					$scope.addresses = [];
					$scope.number = 0;
					$scope.open = function($event) {
						$event.preventDefault();
						$event.stopPropagation();

						$scope.opened = true;
					};

					$scope.today = function() {
						$scope.dt = new Date();
					};
					
//// save&load data from session storage					

					$scope.save1 = function(isValid) {
						if(isValid)
							$sessionStorage.submitForm1 = true;
						$sessionStorage.dataName = $scope.dataName;
						$sessionStorage.dataUsername = $scope.dataUsername;
						$sessionStorage.dataSurname = $scope.dataSurname;
						$sessionStorage.dataBirth = $scope.dataBirth;

						if ($scope.dataGenderM == "male") {
							if ($sessionStorage.dataGenger == "female")
								$scope.dataGenderF = undefined;
							$sessionStorage.dataGenger = $scope.dataGenderM;
						}
						if ($scope.dataGenderF == "female") {
							if ($sessionStorage.dataGenger == "male")
								$scope.dataGenderM = undefined;
							$sessionStorage.dataGenger = $scope.dataGenderF;
						}
						$sessionStorage.dataInsNumber = $scope.dataInsNumber;
					};
					
					$scope.load1 = function() {
						if($sessionStorage.submitForm1 != undefined)
							$scope.submit2 = $sessionStorage.submitForm1;
						if($sessionStorage.submitForm1 != undefined)
							$scope.submit3 = $sessionStorage.submitForm;
						if($sessionStorage.submitForm3 != undefined)
							$scope.submit4 = $sessionStorage.submitForm3;
						$scope.dataName = $sessionStorage.dataName;
						$scope.dataSurname = $sessionStorage.dataSurname;
						$scope.dataBirth = $sessionStorage.dataBirth;
						$scope.dataUsername = $sessionStorage.dataUsername;
						if ($sessionStorage.dataGenger == "option1")
							$scope.dataGenderM = $sessionStorage.dataGenger;
						else
							$scope.dataGenderF = $sessionStorage.dataGenger;
						$scope.dataInsNumber = $sessionStorage.dataInsNumber;
					};

					$scope.save2 = function() {
						$sessionStorage.dataPhone = $scope.dataPhone;
						$sessionStorage.dataAddressType = $scope.dataAddressType;
						$sessionStorage.dataTextAddress = $scope.dataTextAddress;
					}

					$scope.save2v2 = function(indexA, isValid) {
						if(indexA == 0)
							{
								$scope.isValid1 = isValid;
								$sessionStorage.addressType1 = $scope.addresses[0].type;
								$sessionStorage.addressText1 = $scope.addresses[0].text;
							}
						if(indexA == 1)
							{
								$scope.isValid2 = isValid;
								$sessionStorage.addressType2 = $scope.addresses[1].type;
								$sessionStorage.addressText2 = $scope.addresses[1].text;
							}
					}

					$scope.load2 = function() {
						
						if($sessionStorage.submitForm2 != undefined)
							$scope.submit3 = $sessionStorage.submitForm2;
						if($sessionStorage.submitForm3 != undefined)
							$scope.submit4 = $sessionStorage.submitForm3;
						if ($sessionStorage.dataCity != undefined)
							$scope.selectedCity = $sessionStorage.dataCity;
						$scope.selectedZIP = $sessionStorage.dataZIP;
						$scope.filterCountry.operator = String($scope.selectedCity.country);
						$scope.dataPhone = $sessionStorage.dataPhone;
						$scope.dataAddressType = $sessionStorage.dataAddressType;
						$scope.dataTextAddress = $sessionStorage.dataTextAddress;
						$scope.number = $sessionStorage.dataNumber;
						if( $sessionStorage.dataMain == "0")
							{
								$scope.dataMain = 'yes';
							}
							
						if ($scope.number == 1) {
							$scope.isValid1 = $sessionStorage.isValid1;
							if($sessionStorage.dataMain == "1")
								{
									$scope.addresses.push({
										country : $sessionStorage.addressCountry1,
										city : $sessionStorage.addressCity1,
										zip : $sessionStorage.addressZIP1,
										type : $sessionStorage.addressType1,
										text : $sessionStorage.addressText1,
										main : 'yes'
									});
								}
							else
								{
								$scope.addresses.push({
									country : $sessionStorage.addressCountry1,
									city : $sessionStorage.addressCity1,
									zip : $sessionStorage.addressZIP1,
									type : $sessionStorage.addressType1,
									text : $sessionStorage.addressText1,
									main : 'no'
								});
								}
							

						} else if ($scope.number == 2) {
							$scope.isValid1 = $sessionStorage.isValid1;
							$scope.isValid2 = $sessionStorage.isValid2;
							if($sessionStorage.dataMain == "1")
							{
								$scope.addresses.push({
									country : $sessionStorage.addressCountry1,
									city : $sessionStorage.addressCity1,
									zip : $sessionStorage.addressZIP1,
									type : $sessionStorage.addressType1,
									text : $sessionStorage.addressText1,
									main : 'yes'
								});
							}
							else
								{
								$scope.addresses.push({
									country : $sessionStorage.addressCountry1,
									city : $sessionStorage.addressCity1,
									zip : $sessionStorage.addressZIP1,
									type : $sessionStorage.addressType1,
									text : $sessionStorage.addressText1,
									main : 'no'
								});
								}

							if($sessionStorage.dataMain == "2")
							{
								$scope.addresses.push({
									country : $sessionStorage.addressCountry2,
									city : $sessionStorage.addressCity2,
									zip : $sessionStorage.addressZIP2,
									type : $sessionStorage.addressType2,
									text : $sessionStorage.addressText2,
									main : 'yes'
								});
							}
						else
							{
							$scope.addresses.push({
								country : $sessionStorage.addressCountry2,
								city : $sessionStorage.addressCity2,
								zip : $sessionStorage.addressZIP2,
								type : $sessionStorage.addressType2,
								text : $sessionStorage.addressText2,
								main : 'no'
							});
							}
							$scope.visible = false;
						}
					};

					$scope.load3 = function() {
						if($sessionStorage.submitForm3 != undefined)
							$scope.submit4 = $sessionStorage.submitForm3;
						if($sessionStorage.schoolCountry == undefined)
							{
								if($sessionStorage.dataMain == "0")
								{	
									$scope.selectedCity = $sessionStorage.dataCity;
									$sessionStorage.schoolCity = $sessionStorage.dataCity;
									$scope.selectedZIP = $sessionStorage.dataZIP;
									$sessionStorage.schoolZIP = $sessionStorage.dataZIP;
									if($scope.selectedCity != undefined)
										{
										$scope.filterCountry.operator = String($scope.selectedCity.country);
										$sessionStorage.schoolCountry = String($scope.selectedCity.country);
										}
									
								}
								if($sessionStorage.dataMain == "1")
								{	
									$scope.selectedCity = $sessionStorage.addressCity1;
									$sessionStorage.schoolCity = $sessionStorage.addressCity1;
									$scope.selectedZIP = $sessionStorage.addressZIP1;
									$sessionStorage.schoolZIP = $sessionStorage.addressZIP1;
									$scope.filterCountry.operator = String($scope.selectedCity.country);
									$sessionStorage.schoolCountry = String($scope.selectedCity.country);
								}
								if($sessionStorage.dataMain == "2")
								{	
									$scope.selectedCity = $sessionStorage.addressCity2;
									$sessionStorage.schoolCity = $sessionStorage.addressCity2;
									$scope.selectedZIP = $sessionStorage.addressZIP2;
									$sessionStorage.schoolZIP = $sessionStorage.addressZIP2;
									$scope.filterCountry.operator = String($scope.selectedCity.country);
									$sessionStorage.schoolCountry = String($scope.selectedCity.country);
								}
							}
						else
						{
							$scope.selectedCity = $sessionStorage.schoolCity;
							$scope.selectedZIP = $sessionStorage.schoolZIP;
							$scope.filterCountry.operator = String($scope.selectedCity.country);
							$scope.descriptionSchool = $sessionStorage.dataDescription;
							if($sessionStorage.dataSchool.zip == $scope.selectedZIP.id)
								$scope.selectedSchool = $sessionStorage.dataSchool;
						}
						
						
							
					}
					
					$scope.showfinish = true;
					
					$scope.finishForm = function()
					{
						return $scope.showfinish;
					}

					$scope.load4 = function() {
						$sessionStorage.submitForm4 = true;
						$scope.dataWishes = $sessionStorage.dataWishes;
					}

					$scope.loadFinish = function() {
						if($sessionStorage.submitForm1 && $sessionStorage.submitForm2 &&
								$sessionStorage.submitForm3 && $sessionStorage.submitForm4)
							{
							$scope.showfinish = false;
							}
							
						else
							{
							$scope.showfinish = true;
							}
						$scope.dataUsername = $sessionStorage.dataUsername;
						$scope.dataName = $sessionStorage.dataName;
						$scope.dataSurname = $sessionStorage.dataSurname;
						$scope.dataBirthdate = $sessionStorage.dataBirth;
						$scope.dataGender = $sessionStorage.dataGenger;
						$scope.dataInsNumber = $sessionStorage.dataInsNumber;

						$scope.schoolCity = $sessionStorage.schoolCity.title;
						$scope.schoolZIP = $sessionStorage.schoolZIP.number;
						$scope.dataSchool = $sessionStorage.dataSchool.title;
						$scope.dataSchoolid = $sessionStorage.dataSchool.id;
						$scope.dataDescription = $sessionStorage.dataDescription;

						$scope.dataCountry = $sessionStorage.dataCountry;
						$scope.dataCity = $sessionStorage.dataCity.title;
						$scope.dataZIP = $sessionStorage.dataZIP.number;
						$scope.dataZIPid = $sessionStorage.dataZIP.id;
						$scope.dataPhone = $sessionStorage.dataPhone;
						$scope.dataText = $sessionStorage.dataTextAddress;
						$scope.dataType = $sessionStorage.dataAddressType;
						$scope.number = $sessionStorage.dataNumber;
						if( $sessionStorage.dataMain == "0")
							{
								$scope.dataMain = 'yes';
							}
						else
							{
							$scope.dataMain = 'no';
							}
						if ($scope.number == 1) {
							if($sessionStorage.dataMain == "1")
								{
									$scope.addresses.push({
										country : $sessionStorage.addressCountry1,
										city : $sessionStorage.addressCity1,
										zip : $sessionStorage.addressZIP1,
										type : $sessionStorage.addressType1,
										text : $sessionStorage.addressText1,
										main : 'yes'
									});
								}
							else
								{
								$scope.addresses.push({
									country : $sessionStorage.addressCountry1,
									city : $sessionStorage.addressCity1,
									zip : $sessionStorage.addressZIP1,
									type : $sessionStorage.addressType1,
									text : $sessionStorage.addressText1,
									main : 'no'
								});
								}
							

						} else if ($scope.number == 2) {
							if($sessionStorage.dataMain == "1")
							{
								$scope.addresses.push({
									country : $sessionStorage.addressCountry1,
									city : $sessionStorage.addressCity1,
									zip : $sessionStorage.addressZIP1,
									type : $sessionStorage.addressType1,
									text : $sessionStorage.addressText1,
									main : 'yes'
								});
							}
							else
								{
								$scope.addresses.push({
									country : $sessionStorage.addressCountry1,
									city : $sessionStorage.addressCity1,
									zip : $sessionStorage.addressZIP1,
									type : $sessionStorage.addressType1,
									text : $sessionStorage.addressText1,
									main : 'no'
								});
								}

							if($sessionStorage.dataMain == "2")
							{
								$scope.addresses.push({
									country : $sessionStorage.addressCountry2,
									city : $sessionStorage.addressCity2,
									zip : $sessionStorage.addressZIP2,
									type : $sessionStorage.addressType2,
									text : $sessionStorage.addressText2,
									main : 'yes'
								});
							}
						else
							{
							$scope.addresses.push({
								country : $sessionStorage.addressCountry2,
								city : $sessionStorage.addressCity2,
								zip : $sessionStorage.addressZIP2,
								type : $sessionStorage.addressType2,
								text : $sessionStorage.addressText2,
								main : 'no'
							});
							}
							$scope.visible = false;
						}
						
						$scope.dataWishes = $sessionStorage.dataWishes;
					}
					
					$scope.saveDesc = function(isValid) {
						if (isValid) {
							$sessionStorage.submitForm3 = true;
						}
						$sessionStorage.dataDescription = $scope.descriptionSchool;
					}

					$scope.save4 = function() {
						$sessionStorage.dataWishes = $scope.dataWishes;
					}
					
////end data loading&saving
					
//// load data from db					

					$scope.countrys = [];
					$scope.addCountry = function(id, title) {
						$scope.countrys.push({
							'id' : id,
							'title' : title
						});

					};

					$scope.filterCountry = {
						operator : 0
					}

					$scope.cities = [];
					$scope.addCity = function(id, title, country) {
						$scope.cities.push({
							'id' : id,
							'title' : title,
							'country' : country
						});

					};

					$scope.zips = [];
					$scope.addZIP = function(id, number, city) {
						$scope.zips.push({
							'id' : id,
							'number' : number,
							'city' : city
						});

					};

					$scope.schools = [];
					$scope.addSchool = function(id, title, zip) {
						$scope.schools.push({
							'id' : id,
							'title' : title,
							'zip' : zip
						});

					};
					
//// end data loading					
					
					$scope.cityFilter = function(cityItem) {
						return (0 == $scope.filterCountry.operator || cityItem.country == $scope.filterCountry.operator);
					}
					
					$scope.zipFilter = function(zipItem) {
						return ( $scope.selectedCity == "" || zipItem.city == $scope.selectedCity.id);
					}
					
					$scope.schoolFilter = function(schoolItem) {
						return ( $scope.selectedZIP == "" || schoolItem.zip == $scope.selectedZIP.id);
					}
					
//// page 2/4				
					
					$scope.isValid1 = false;
					$scope.isValid2 = false;
					
					$scope.clearCityZIP = function() {
						$scope.selectedCity = "";
						$scope.selectedZIP = "";
						$sessionStorage.dataZIP = '';
						$sessionStorage.dataCity = '';
						$sessionStorage.dataCountry = $scope.filterCountry.operator;
					}

					$scope.clearCityZIP2 = function(indexA, isValid) {
						if (indexA == 0) {
							$scope.isValid1 = isValid;
							$scope.addresses[0].city = "";
							$scope.addresses[0].zip = "";
							$sessionStorage.addressCountry1 = $scope.addresses[0].country;
						}
						if (indexA == 1) {
							$scope.isValid2 = isValid;
							$scope.addresses[1].city = "";
							$scope.addresses[1].zip = "";
							$sessionStorage.addressCountry2 = $scope.addresses[1].country;
						}
					}
					
					$scope.countryByCity = function() {
						$scope.filterCountry.operator = String($scope.selectedCity.country);
						$sessionStorage.dataCity = {
							'id' : $scope.selectedCity.id,
							'title' : $scope.selectedCity.title,
							'country' : $scope.selectedCity.country
						};
						$sessionStorage.dataCountry = $scope.filterCountry.operator;
					}

					$scope.countryByCity2 = function(indexA, isValid) {
						$scope.addresses[indexA].country = String($scope.addresses[indexA].city.country);
						if (indexA == 0) {
							$scope.isValid1 = isValid;
							$sessionStorage.addressCity1 = {
								'id' : $scope.addresses[indexA].city.id,
								'title' : $scope.addresses[indexA].city.title,
								'country' : $scope.addresses[indexA].city.country
							};
							$sessionStorage.addressCountry1 = $scope.addresses[indexA].country;
						}
						if (indexA == 1) {
							$scope.isValid2 = isValid;
							$sessionStorage.addressCity2 = {
								'id' : $scope.addresses[indexA].city.id,
								'title' : $scope.addresses[indexA].city.title,
								'country' : $scope.addresses[indexA].city.country
							};
							$sessionStorage.addressCountry2 = $scope.addresses[indexA].country;
						}
					}
					
					$scope.cityByZIP = function() {
						$scope.selectedCity = $scope
								.cityById($scope.selectedZIP.city);
						$sessionStorage.dataZIP = {
							'id' : $scope.selectedZIP.id,
							'number' : $scope.selectedZIP.number,
							'city' : $scope.selectedZIP.city
						};
						$scope.countryByCity();
					}

					$scope.cityByZIP2 = function(indexA, isValid) {
						$scope.addresses[indexA].city = $scope
								.cityById($scope.addresses[indexA].zip.city);
						if (indexA == 0) {
							$scope.isValid1 = isValid;
							$sessionStorage.addressZIP1 = {
								'id' : $scope.addresses[indexA].zip.id,
								'number' : $scope.addresses[indexA].zip.number,
								'city' : $scope.addresses[indexA].zip.city
							};
						}
						if (indexA == 1) {
							$scope.isValid2 = isValid;
							$sessionStorage.addressZIP2 = {
								'id' : $scope.addresses[indexA].zip.id,
								'number' : $scope.addresses[indexA].zip.number,
								'city' : $scope.addresses[indexA].zip.city
							};
						}
						if((($scope.addresses[indexA].type == "")||($scope.addresses[indexA].type == undefined)) ||
								(($scope.addresses[indexA].text == "")||($scope.addresses[indexA].text == undefined)))
							$scope.countryByCity2(indexA, false);
						else
							$scope.countryByCity2(indexA, true);
					}
					

					$scope.getNumber = function(num) {
						return new Array(num);
					}

					$scope.addAddress = function(index) {
						if (index == 1 || index == 2) {
							$scope.addresses.push({
								country : '',
								city : '',
								zip : '',
								type : '',
								text : '',
								main : ''
							});
							$sessionStorage.dataNumber = $scope.number;
						}
						if (index == 2) {
							$scope.visible = false;
						}
						if (index > 2) {
							$scope.number = $scope.number - 1;
						}

					}

					$scope.deleteAddress = function(item) {
						var index = $scope.addresses.indexOf(item);
						if (index == 0) {
							$sessionStorage.addressCountry1 = '';
							$sessionStorage.addressCity1 = '';
							$sessionStorage.addressZIP1 = '';
							$sessionStorage.addressType1 = '';
							$sessionStorage.addressText1 = '';
							
							$sessionStorage.dataMain = "0";
							$scope.dataMain = 'yes';
						}
						if (index == 1) {
							$sessionStorage.addressCountry2 = '';
							$sessionStorage.addressCity2 = '';
							$sessionStorage.addressZIP2 = '';
							$sessionStorage.addressType2 = '';
							$sessionStorage.addressText2 = '';
							$sessionStorage.dataMain = "0";
							$scope.dataMain = 'yes';
						}
						$scope.addresses.splice(index, 1);
						$scope.number = $scope.number - 1;
						$scope.visible = true;
						$sessionStorage.dataNumber = $scope.number;
					}

					$scope.getAddresses = function() {
						return $scope.addresses;
					};
					
					$scope.addressInit = function(){
						if($sessionStorage.dataMain != "1" && $sessionStorage.dataMain != "2")
							{
								$scope.dataMain = 'yes';
								$sessionStorage.dataMain = "0";
							}
							
					}

					$scope.mainChange = function() {
						$sessionStorage.dataMain = "0";
						$scope.addresses[0].main = 'no';
						$scope.addresses[1].main = 'no';
						
					}

					$scope.addressChange = function(indexA, isValid) {
						if (indexA == 0) {
							$scope.isValid1 = isValid;
							$sessionStorage.dataMain = "1";
							$scope.dataMain = 'no';
							$scope.addresses[1].main = 'no';
							
						}
						if (indexA == 1) {
							$scope.isValid1 = isValid;
							$sessionStorage.dataMain = "2";
							$scope.dataMain = 'no';
							$scope.addresses[0].main = 'no';
							
						}
					}
					
//// end page 2/4					
					
//// page 3/4
					
					$scope.clearCityZIP3 = function(isValid) {
						if (isValid) {
							$sessionStorage.submitForm3 = true;
						}
						$scope.form3invalidCountry = false;
						$scope.selectedCity = "";
						$scope.selectedZIP = "";
						$scope.selectedSchool = "";
						$sessionStorage.schoolZIP = '';
						$sessionStorage.schoolCity = '';
						$sessionStorage.dataSchool = '';
						$sessionStorage.schoolCountry = $scope.filterCountry.operator;
					}
					
					$scope.countryByCity3 = function(isValid) {
						if (isValid) {
							$sessionStorage.submitForm3 = true;
						}
						$scope.form3invalidCity = false;
						$scope.selectedZIP = "";
						$scope.selectedSchool = "";
						$sessionStorage.schoolZIP = '';
						$sessionStorage.dataSchool = '';
						$scope.filterCountry.operator = String($scope.selectedCity.country);
						$sessionStorage.schoolCity = {
							'id' : $scope.selectedCity.id,
							'title' : $scope.selectedCity.title,
							'country' : $scope.selectedCity.country
						};
						$sessionStorage.schoolCountry = $scope.filterCountry.operator;
						$scope.form3invalidCountry = false;
						
					}
					
					$scope.cityByZIP3 = function(isValid) {
						if (isValid) {
							$sessionStorage.submitForm3 = true;
						}
						$scope.form3invalidZip = false;
						$scope.selectedCity = $scope.cityById($scope.selectedZIP.city);
						
						$scope.selectedSchool = "";
						$sessionStorage.dataSchool = '';
						
						$sessionStorage.schoolZIP = {
							'id' : $scope.selectedZIP.id,
							'number' : $scope.selectedZIP.number,
							'city' : $scope.selectedZIP.city
						};
						
						$scope.form3invalidCity = false;
						$scope.filterCountry.operator = String($scope.selectedCity.country);
						$sessionStorage.schoolCity = {
							'id' : $scope.selectedCity.id,
							'title' : $scope.selectedCity.title,
							'country' : $scope.selectedCity.country
						};
						$sessionStorage.schoolCountry = $scope.filterCountry.operator;
						$scope.form3invalidCountry = false;
						//$scope.countryByCity3();
					}
					
					$scope.saveSchool = function(isValid) {
						
						$scope.form3invalidSchool = false;
						
						$scope.selectedZIP = $scope.zipById($scope.selectedSchool.zip);
						
						$sessionStorage.dataSchool = {
							'id' : $scope.selectedSchool.id,
							'title' : $scope.selectedSchool.title,
							'zip' : $scope.selectedSchool.zip
						};
						
						$scope.form3invalidZip = false;
						$scope.selectedCity = $scope.cityById($scope.selectedZIP.city);
												
						$sessionStorage.schoolZIP = {
							'id' : $scope.selectedZIP.id,
							'number' : $scope.selectedZIP.number,
							'city' : $scope.selectedZIP.city
						};
						
						$scope.form3invalidCity = false;
						$scope.filterCountry.operator = String($scope.selectedCity.country);
						$sessionStorage.schoolCity = {
							'id' : $scope.selectedCity.id,
							'title' : $scope.selectedCity.title,
							'country' : $scope.selectedCity.country
						};
						$sessionStorage.schoolCountry = $scope.filterCountry.operator;
						$scope.form3invalidCountry = false;
						//$scope.cityByZIP3();
							$sessionStorage.submitForm3 = true;
					}
					
//// end page 3/4
					
					$scope.cityById = function(id) {
						for (index = 0; index < $scope.cities.length; index++) {
							if (id == $scope.cities[index].id)
								return $scope.cities[index];
						}
						return 0;
					}

					$scope.zipById = function(id){
						for(index = 0; index < $scope.zips.length; index++)
							{
								if($scope.zips[index].id == id)
									{
									return $scope.zips[index];
									}
									
							}
					}

					

//// validation methods
					
					
					$scope.submitForm = function(isValid) {
						if (isValid) {
							$sessionStorage.submitForm1 = true;
							window.location = "create-account-page-2";
						}
						else
						{
							$scope.form3invalidUsername = true;
							$scope.form3invalidName = true;
							$scope.form3invalidSurname = true;
							$scope.form3invalidBirthdate = true;
						}
					};
					
					
					$scope.submitForm2 = function(isValid) {
						if(($scope.number == 0) || ($scope.number == undefined)){
							if (isValid) {
								$sessionStorage.submitForm2 = true;
								window.location = "create-account-page-3";
							}
							else
							{
								$scope.form3invalidTelephone = true;
								$scope.form3invalidCountry = true;
								$scope.form3invalidCity = true;
								$scope.form3invalidZip = true;
								$scope.form3invalidType = true;
								$scope.form3invalidText = true;
							}
						}
						if($scope.number == 1){
							if (isValid && $scope.isValid1) {
								$sessionStorage.submitForm2 = true;
								$sessionStorage.isValid1 = $scope.isValid1;
								window.location = "create-account-page-3";
							}
							else
							{
								$scope.form3invalidTelephone = true;
								$scope.form3invalidCountry = true;
								$scope.form3invalidCity = true;
								$scope.form3invalidZip = true;
								$scope.form3invalidType = true;
								$scope.form3invalidText = true;
								
								$scope.form2Country1 = true;
								$scope.form2City1 = true;
								$scope.form2Zip1 = true;
								$scope.form2Type1 = true;
								$scope.form2Text1 = true;
							}
						}
						if($scope.number == 2){
						if (isValid && $scope.isValid1 && $scope.isValid2) {
							$sessionStorage.submitForm2 = true;
							$sessionStorage.isValid1 = $scope.isValid1;
							$sessionStorage.isValid2 = $scope.isValid2;
							window.location = "create-account-page-3";
							}	
							else
							{
								$scope.form3invalidTelephone = true;
								$scope.form3invalidCountry = true;
								$scope.form3invalidCity = true;
								$scope.form3invalidZip = true;
								$scope.form3invalidType = true;
								$scope.form3invalidText = true;
								
								$scope.form2Country1 = true;
								$scope.form2City1 = true;
								$scope.form2Zip1 = true;
								$scope.form2Type1 = true;
								$scope.form2Text1 = true;
								
								$scope.form2Country2 = true;
								$scope.form2City2 = true;
								$scope.form2Zip2 = true;
								$scope.form2Type2 = true;
								$scope.form2Text2= true;
							}
						}
					};
										
					
					$scope.submitForm3 = function(isValid) {
						if (isValid) {
							$sessionStorage.submitForm3 = true;
							window.location = "create-account-page-4";
						}
						else
							{
							$scope.form3invalidCountry = true;
							$scope.form3invalidCity = true;
							$scope.form3invalidZip = true;
							$scope.form3invalidSchool = true;
							}
					};
					
					$scope.submitForm4 = function(isValid) {
						if (isValid) {
							$sessionStorage.submitForm4 = true;
							window.location = "finish-reg";
						}
					};
					
});



//filter for searching city by country
storage.filter('cityFilter', function() {

	return function(items, address) {

		var arrayToReturn = [];
		for (var i = 0; i < items.length; i++) {
			if (items[i].country == address.country || 0 == address.country) {
				arrayToReturn.push(items[i]);
			}
			
		}

		return arrayToReturn;
	};
});


//filter for searching zip by city
storage.filter('zipFilter', function() {

	return function(items, address) {
		var arrayToReturn = [];
		for (var i = 0; i < items.length; i++) {
			if (items[i].city == address.city.id || 0 == address.city) {
				arrayToReturn.push(items[i]);
			}
		}

		return arrayToReturn;
	};
});




//directives for validation by registration

storage.directive('ensureUnique', ['$http', function($http) {
	  return {
	    require: 'ngModel',
	    link: function(scope, ele, attrs, c) {
	      scope.$watch(attrs.ngModel, function() {
	    	  var data = 'username=' + attrs.ensureUnique;
	    	  $http.post('/schoolreg/checkusername', data)
	      .success(function(data, status, headers, cfg) {
	          c.$setValidity('unique', true);
	        }).error(function(data, status, headers, cfg) {
	          c.$setValidity('unique', false);
	        });
	    });
	  }
	}}]);

storage.directive("isselected", function() {
    return {
      restrict: "A",
      require: "?ngModel",
      link: function(scope, element, attributes, ngModel) {
        ngModel.$validators.isselected = function(modelValue) {
          
          return modelValue > 0;
        };
      }
    };
  });

storage.directive("typeselected", function() {
    return {
      restrict: "A",
      require: "?ngModel",
      link: function(scope, element, attributes, ngModel) {
        ngModel.$validators.typeselected = function(modelValue) {
          if(modelValue == "Parents address" || modelValue == "Mother address" || modelValue == "Father address"||
        		  modelValue == "Адрес родителей" || modelValue == "Адрес мамы" || modelValue == "Адрес папы")
        	  return true;
          else
        	  return false;
        };
      }
    };
  });

storage.directive("modelzip", function() {
    return {
      restrict: "A",
      require: "?ngModel",
      link: function(scope, element, attributes, ngModel) {
        ngModel.$validators.modelzip = function(modelValue) {
        	if(modelValue != undefined)
        		return modelValue.id > 0;
        	else
        		return false;
        };
      }
    };
  });

storage.directive("modelcity", function() {
    return {
      restrict: "A",
      require: "?ngModel",
      link: function(scope, element, attributes, ngModel) {
        ngModel.$validators.modelcity = function(modelValue) {
        	if(modelValue != undefined)
        		return modelValue.id > 0;
        	else
        		return false;
        };
      }
    };
  });

storage.directive("modelschool", function() {
    return {
      restrict: "A",
      require: "?ngModel",
      link: function(scope, element, attributes, ngModel) {
        ngModel.$validators.modelschool = function(modelValue) {
        	if(modelValue != undefined)
        		return modelValue.id > 0;
        	else
        		return false;
        };
      }
    };
  });

