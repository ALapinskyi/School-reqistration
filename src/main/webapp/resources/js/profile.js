//module for storage data by profile editing and validation
// 		ui.bootstrap	bootstrap library	
// 		ngStorage		store data in sessionStorage
//		ngResource		send data by ajax

var profile = angular.module('profile', [ 'ui.bootstrap', 'ngStorage','ngRoute', 'ngResource']);

profile.config(['$httpProvider', function ($httpProvider) {    
	$httpProvider.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded; charset=UTF-8';
}]);



//ctrl for data editing in profile
profile.controller('ProfileCtrl',function($scope, $sessionStorage, $http) {

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
					
					$scope.clearCityZIP3 = function() {
						$scope.selectedCity = "";
						$scope.selectedZIP = "";
						$sessionStorage.schoolZIP = '';
						$sessionStorage.schoolCity = '';
						$sessionStorage.schoolCountry = $scope.filterCountry.operator;
					}
					
					$scope.cityFilter = function(cityItem) {
						return (cityItem.country == $scope.filterCountry.operator || 0 == $scope.filterCountry.operator);
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
					
					$scope.countryByCity3 = function() {
						$scope.filterCountry.operator = String($scope.selectedCity.country);
						$sessionStorage.schoolCity = {
							'id' : $scope.selectedCity.id,
							'title' : $scope.selectedCity.title,
							'country' : $scope.selectedCity.country
						};
						$sessionStorage.schoolCountry = $scope.filterCountry.operator;
					}

					$scope.zipFilter = function(zipItem) {
						return (zipItem.city == $scope.selectedCity.id || $scope.selectedCity == "");
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
					
					$scope.cityByZIP3 = function() {
						$scope.selectedCity = $scope.cityById($scope.selectedZIP.city);
						$sessionStorage.schoolZIP = {
							'id' : $scope.selectedZIP.id,
							'number' : $scope.selectedZIP.number,
							'city' : $scope.selectedZIP.city
						};
						$scope.countryByCity3();
					}
					
					$scope.cityById = function(id) {
						for (index = 0; index < $scope.cities.length; index++) {
							if (id == $scope.cities[index].id)
								return $scope.cities[index];
						}
						return 0;
					}

					$scope.schoolFilter = function(schoolItem) {
						return schoolItem.zip == $scope.selectedZIP.id;
					}

					$scope.saveSchool = function() {
						$sessionStorage.dataSchool = {
							'id' : $scope.selectedSchool.id,
							'title' : $scope.selectedSchool.title,
							'zip' : $scope.selectedSchool.zip
						};
					}

					$scope.saveDesc = function() {
						$sessionStorage.dataDescription = $scope.descriptionSchool;
					}

					$scope.save4 = function() {
						$sessionStorage.dataWishes = $scope.dataWishes;
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
								main : 'no'
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

					$scope.addressChange = function(indexA) {
						if (indexA == 0) {
							$sessionStorage.dataMain = "1";
							$scope.dataMain = 'no';
							$scope.addresses[1].main = 'no';
							
						}
						if (indexA == 1) {
							$sessionStorage.dataMain = "2";
							$scope.dataMain = 'no';
							$scope.addresses[0].main = 'no';
							
						}
					}
					
					
					$scope.genderChange = function(){
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
					}
					
					$scope.address1change = function(){
						$scope.isValid1 = false;
					}

					$scope.submitForm = function(isValid) {
						if (isValid) {
							//$sessionStorage.submitForm1 = true;
							if($scope.dataGenderM == "male")
								var data = 'name=' + $scope.dataName + '&surname=' + $scope.dataSurname
								+ '&birth=' + $scope.dataBirth + '&insnumber=' + $scope.dataInsNumber
								+ '&gender=' + $scope.dataGenderM;
							if($scope.dataGenderF == "female")
								var data = 'name=' + $scope.dataName + '&surname=' + $scope.dataSurname
								+ '&birth=' + $scope.dataBirth + '&insnumber=' + $scope.dataInsNumber
								+ '&gender=' + $scope.dataGenderF;
							$http.post('/schoolreg/saveprofile1', data )
							.success(function(data1, status, headers, config) {
								$scope.message = data1;
								$scope.mainUpdate = true;
							})
							.error(function(data, status, headers, config) {
								alert( "failure message: " + JSON.stringify({data: data}));
								
							});
							//window.location = "create-account-page-2";
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
						if(($scope.number == 0 )||($scope.number == undefined)){
							//alert("0");
							if (isValid) {
								
								//alert("amazing");
								var data = 'number=' + $scope.number + '&telephone=' + $scope.dataPhone
								+ '&mainzip=' + $scope.selectedZIP.id + '&maintext=' + $scope.dataTextAddress
								+ '&maintype=' + $scope.dataAddressType + '&mainismain=' + $scope.dataMain
								+ '&zip1=' + '0' + '&text1=' + '0'
								+ '&type1=' + '0' + '&ismain1=' + '0'
								+ '&zip2=' + '0' + '&text2=' + '0'
								+ '&type2=' + '0' + '&ismain2=' + '0';
								$http.post('/schoolreg/saveprofile2', data )
								.success(function(data1, status, headers, config) {
									$scope.message = data1;
									$scope.mainUpdate = true;
								})
								.error(function(data, status, headers, config) {
									alert( "failure message: " + JSON.stringify({data: data}));
									
								});
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
							//alert("1");
							if (isValid && $scope.isValid1) {
								//$sessionStorage.submitForm2 = true;
								//$sessionStorage.isValid1 = $scope.isValid1;
								//alert("amazing");
								var data = 'number=' + $scope.number + '&telephone=' + $scope.dataPhone
								+ '&mainzip=' + $scope.selectedZIP.id + '&maintext=' + $scope.dataTextAddress
								+ '&maintype=' + $scope.dataAddressType + '&mainismain=' + $scope.dataMain
								+ '&zip1=' + $scope.addresses[0].zip.id + '&text1=' + $scope.addresses[0].text
								+ '&type1=' + $scope.addresses[0].type + '&ismain1=' + $scope.addresses[0].main
								+ '&zip2=' + '0' + '&text2=' + '0'
								+ '&type2=' + '0' + '&ismain2=' + '0';
								$http.post('/schoolreg/saveprofile2', data )
								.success(function(data1, status, headers, config) {
									$scope.message = data1;
									$scope.mainUpdate = true;
								})
								.error(function(data, status, headers, config) {
									alert( "failure message: " + JSON.stringify({data: data}));
									
								});
								//window.location = "create-account-page-3";
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
							//alert("2");
						if (isValid && $scope.isValid1 && $scope.isValid2) {
							//$sessionStorage.submitForm2 = true;
							//$sessionStorage.isValid1 = $scope.isValid1;
							//$sessionStorage.isValid2 = $scope.isValid2;
							//alert("amazing");
							var data = 'number=' + $scope.number + '&telephone=' + $scope.dataPhone
							+ '&mainzip=' + $scope.selectedZIP.id + '&maintext=' + $scope.dataTextAddress
							+ '&maintype=' + $scope.dataAddressType + '&mainismain=' + $scope.dataMain
							+ '&zip1=' + $scope.addresses[0].zip.id + '&text1=' + $scope.addresses[0].text
							+ '&type1=' + $scope.addresses[0].type + '&ismain1=' + $scope.addresses[0].main
							+ '&zip2=' + $scope.addresses[1].zip.id + '&text2=' + $scope.addresses[1].text
							+ '&type2=' + $scope.addresses[1].type + '&ismain2=' + $scope.addresses[1].main;
							$http.post('/schoolreg/saveprofile2', data )
							.success(function(data1, status, headers, config) {
								$scope.message = data1;
								$scope.mainUpdate = true;
							})
							.error(function(data, status, headers, config) {
								alert( "failure message: " + JSON.stringify({data: data}));
								
							});
							//window.location = "create-account-page-3";
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
							//$sessionStorage.submitForm3 = true;
							var data = 'zip=' + $scope.selectedZIP.id + '&id=' + $scope.selectedSchool.id +'&description=' + $scope.descriptionSchool;
							$http.post('/schoolreg/saveprofile3', data )
							.success(function(data1, status, headers, config) {
								$scope.message = data1;
								$scope.wishUpdate = true;
							})
							.error(function(data, status, headers, config) {
								alert( "failure message: " + JSON.stringify({data: data}));
								
							});
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
							//$sessionStorage.submitForm4 = true;
							//window.location = "finish-reg";
							var data = 'wish=' + $scope.dataWishes;
						$http.post('/schoolreg/saveprofile4', data )
						.success(function(data1, status, headers, config) {
							$scope.message = data1;
							$scope.wishUpdate = true;
						})
						.error(function(data, status, headers, config) {
							alert( "failure message: " + JSON.stringify({data: data}));
							
						});
						}
					};

					$scope.profileBirth = undefined;
					
					$scope.zipById = function(id){
						for(index = 0; index < $scope.zips.length; index++)
							{
								if($scope.zips[index].id == id)
									{
									return $scope.zips[index];
									}
									
							}
					}

					$scope.loadprofile1 = function(id) {
						var data = 'id='+id;	
						$http.post('/schoolreg/loadprofile1', data )
						.success(function(data1, status, headers, config) {
							$scope.message = data1;
							$http({
								method : 'GET',
								url : '/schoolreg/getprofile1'
							}).success(function(data, status, headers, config) {
								$scope.profiles = data;
								$scope.dataName = $scope.profiles[0].name;
								$scope.dataSurname = $scope.profiles[0].surname;
								$scope.dataBirth = $scope.profiles[0].birthdate;
								if(($scope.profiles[0].insnumber != undefined) ||($scope.profiles[0].insnumber != "null"))
									$scope.dataInsNumber = $scope.profiles[0].insnumber;
								else
									$scope.dataInsNumber = "";
								if($scope.profiles[0].gender == "male")
									$scope.dataGenderM = "male";
								else
									$scope.dataGenderF = "female";
							}).error(function(data, status, headers, config) {
								$scope.profiles = data;
							});
						})
						.error(function(data, status, headers, config) {
							alert( "failure message: " + JSON.stringify({data: data}));
							
						});
					}
					
					$scope.loadprofile2 = function(id) {
						var data = 'id='+id;	
						$http.post('/schoolreg/loadprofile2', data )
						.success(function(data1, status, headers, config) {
							$scope.message = data1;
							$http({
								method : 'GET',
								url : '/schoolreg/getprofile2'
							}).success(function(data, status, headers, config) {
								$scope.profiles = data;
								$scope.dataPhone = $scope.profiles[0].telephone;
								$scope.dataAddressType = $scope.profiles[0].maintype;
								$scope.dataTextAddress  = $scope.profiles[0].textaddress;
								$scope.dataMain = $scope.profiles[0].mainismain;
								$scope.selectedZIP = {
										'id' : $scope.profiles[0].mainzip,
										'number' : $scope.profiles[0].mainzipNumber,
										'city' : $scope.profiles[0].maincity
									};
								if($scope.profiles[0].city1 != null)
								{
									$scope.number = 1;
									$scope.isValid1 = true;
									$scope.addresses.push({
										country : $scope.profiles[0].country1,
										city : $scope.cityById($scope.profiles[0].city1),
										zip : $scope.zipById($scope.profiles[0].zip1),
										type : $scope.profiles[0].type1,
										text : $scope.profiles[0].textaddress1,
										main : $scope.profiles[0].ismain1
									});
									if($scope.profiles[0].city2 != undefined)
										{
										$scope.isValid2 = true;
										$scope.number = 2;
										$scope.addresses.push({
											country : $scope.profiles[0].country2,
											city : $scope.cityById($scope.profiles[0].city2),
											zip : $scope.zipById($scope.profiles[0].zip2),
											type : $scope.profiles[0].type2,
											text : $scope.profiles[0].textaddress2,
											main : $scope.profiles[0].ismain2
										});
										}
								}
								$scope.cityByZIP();
							}).error(function(data, status, headers, config) {
								$scope.profiles = data;
							});
						})
						.error(function(data, status, headers, config) {
							alert( "failure message: " + JSON.stringify({data: data}));
							
						});
					}
					
					$scope.loadprofile3 = function(id) {
						var data = 'id='+id;	
						$http.post('/schoolreg/loadprofile3', data )
						.success(function(data1, status, headers, config) {
							$scope.message = data1;
							$http({
								method : 'GET',
								url : '/schoolreg/getprofile3'
							}).success(function(data, status, headers, config) {
								$scope.profiles = data;
								if(($scope.profiles[0].description != "null") || ($scope.profiles[0].description != undefined))
									$scope.descriptionSchool = $scope.profiles[0].description;
								$scope.selectedSchool = {
										'id' : $scope.profiles[0].id,
										'title' : $scope.profiles[0].school,
										'zip' : $scope.profiles[0].zip
									};
								
								$scope.selectedZIP = $scope.zipById($scope.profiles[0].zip);
								$scope.cityByZIP3();
								
							}).error(function(data, status, headers, config) {
								$scope.profiles = data;
							});
						})
						.error(function(data, status, headers, config) {
							alert( "failure message: " + JSON.stringify({data: data}));
							
						});
					}
					
					$scope.loadprofile4 = function(id) {
						var data = 'id='+id;	
						$http.post('/schoolreg/loadprofile4', data )
						.success(function(data1, status, headers, config) {
							$scope.message = data1;
							$http({
								method : 'GET',
								url : '/schoolreg/getprofile4'
							}).success(function(data, status, headers, config) {
								$scope.profiles = data;
								if(($scope.profiles[0].description != "null") || ($scope.profiles[0].description != undefined))
									$scope.dataWishes = $scope.profiles[0].description;
							}).error(function(data, status, headers, config) {
								$scope.profiles = data;
							});
						})
						.error(function(data, status, headers, config) {
							alert( "failure message: " + JSON.stringify({data: data}));
							
						});
					}
					
					$scope.initProfile3 = function(id, idS) {
						$scope.selectedSchool = $scope.findInSchool(idS);
						$scope.selectedZIP = $scope.findInZIP(id);
						$scope.selectedCity  = $scope.cityById($scope.selectedZIP.city);
						$scope.filterCountry.operator = String($scope.selectedCity.country);
					}
					
					$scope.findInZIP = function(id){
						for(index = 0; index < $scope.zips.length; index++)
							{
								if($scope.zips[index].id == id)
									{
									return $scope.zips[index];
									}
									
							}
					}
					$scope.findInSchool = function(id){
						for(index = 0; index < $scope.schools.length; index++)
							{
								if($scope.schools[index].id == id)
									{
									return $scope.schools[index];
									}
									
							}
					}
					
					$scope.alerts = [ {
							type : 'success',
							msg : 'Data updated'
						} ];

						$scope.closeAlert = function(index) {
							$scope.alerts.splice(index, 1);
						};
				});



//filter for searching city only by country
profile.filter('cityFilter', function() {

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


//filter for searching zip only by city
profile.filter('zipFilter', function() {

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

profile.config(function($routeProvider, $locationProvider){
	var rootUrl = '';
	$routeProvider
	.when('/main', 
	{
		templateUrl: 'part/main', 
		controller: 'ProfileCtrl'
	})
	.when('/addresses', 
	{
		templateUrl: 'part/addresses', 
		controller: 'ProfileCtrl'
	})
	.when('/schools', 
	{
		templateUrl: 'part/schools', 
		controller: 'ProfileCtrl'
	})
	.when('/wish', 
	{
		templateUrl: 'part/wish', 
		controller: 'ProfileCtrl'
	})
	
	.otherwise({redirectTo:'/main'});
	//$locationProvider.html5Mode(true);
	
});
