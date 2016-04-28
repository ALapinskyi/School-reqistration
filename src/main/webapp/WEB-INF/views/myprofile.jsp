<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<t:template>

	
	<div class="col-md-2" >
		
	</div>
	<div class="col-md-7" ng-controller="ProfileCtrl" > 
	
		<c:forEach var="country" items="${countrys}" varStatus="totalRow"
			step="1">
			<div
				data-ng-init="addCountry(${country.iDCountry}, '${country.title}')">
			</div>
		</c:forEach>
		<c:forEach var="city" items="${cities}" varStatus="totalRow" step="1">
			<div
				data-ng-init="addCity(${city.iDCity}, '${city.title}',${city.iDCountry})">
			</div>
		</c:forEach>
		<c:forEach var="zip" items="${zips}" varStatus="totalRow" step="1">
			<div
				data-ng-init="addZIP(${zip.iDZIP}, '${zip.number}', ${zip.iDCity})">
			</div>
		</c:forEach>
		<c:forEach var="school" items="${schools}" varStatus="totalRow"
			step="1">
			<div
				data-ng-init="addSchool(${school.iDSchool}, '${school.title}', ${school.iDZip})">
			</div>
		</c:forEach>
		<ul class="nav nav-tabs">
						<li class="{ active: isActive('/main')}"><a href="#/main"><spring:message code="label.maininfo" /></a></li>
				        <li class="{ active: isActive('/addresses')}"><a href="#/addresses"><spring:message code="label.addresses" /></a></li>
				        <li class="{ active: isActive('/schools')}"><a href="#/schools"><spring:message code="label.prevschool" /></a></li>
				        <li class="{ active: isActive('/wish')}"><a href="#/wish"><spring:message code="label.otherwishes" /></a></li>
				    </ul>
			<div ng-view >
			
			</div>	    
		
	</div>
	<div class="col-md-2"></div>
	<div class="col-md-1">
		<a href="<c:url value="/logout" />"><button type="button"
				class="btn btn-primary">Logout</button></a>
	</div>
	<script type="text/ng-template" id="part/main">		    
		<form class="form-horizontal" id="my-profile" name="userForm" ng-submit="submitForm(userForm.$valid)" data-ng-init="loadprofile1(${user.iDUser})" novalidate>
			<div ng-if="mainUpdate">
				<alert ng-repeat="alert in alerts" type="{{alert.type}}"
				close="closeAlert($index)">{{alert.msg}}</alert>
			</div>
			<div class="form-group" >
				<label for="inputName" class="col-sm-2 control-label"><spring:message code="label.name" />*</label>
				<div class="col-sm-10" ng-class="{ 'has-error' : (userForm.dataName.$invalid && !userForm.dataName.$pristine)
														|| (userForm.dataName.$invalid && form3invalidName)}">
					<input ng-model="dataName" type="text" name="dataName" class="form-control"
						id="inputName"  ng-change="save1(userForm.$valid)" ng-pattern="/^[a-zA-Z]*$/" ng-minlength="2" ng-maxlength="20"  required>
					<p ng-show="(userForm.dataName.$error.required && !userForm.dataName.$pristine)
							|| (userForm.dataName.$error.required && form3invalidName)" class="help-block"><spring:message code="label.required" /></p>
					<p ng-show="userForm.dataName.$error.pattern" class="help-block"><spring:message code="label.namevalid" /></p>
					<p ng-show="userForm.dataName.$error.minlength" class="help-block"><spring:message code="label.nameshort" /></p>
            		<p ng-show="userForm.dataName.$error.maxlength" class="help-block"><spring:message code="label.namelong" /></p>
				</div>
			</div>
			<div class="form-group" >
				<label for="inputSurname" class="col-sm-2 control-label"><spring:message code="label.surname" />*</label>
				<div class="col-sm-10" ng-class="{ 'has-error' : (userForm.dataSurname.$invalid && !userForm.dataSurname.$pristine)
														|| (userForm.dataSurname.$invalid && form3invalidSurname) }">
					<input ng-model="dataSurname" type="text" name="dataSurname" class="form-control"
						id="inputSurname"  ng-change="save1(userForm.$valid)" ng-pattern="/^[a-zA-Z]*$/" ng-minlength="2" ng-maxlength="20"  required>
					<p ng-show="(userForm.dataSurname.$error.required && !userForm.dataSurname.$pristine)
							|| (userForm.dataSurname.$error.required && form3invalidSurname)" class="help-block"><spring:message code="label.required" /></p>
					<p ng-show="userForm.dataSurname.$error.pattern" class="help-block"><spring:message code="label.surnamevalid" /></p>
					<p ng-show="userForm.dataSurname.$error.minlength" class="help-block"><spring:message code="label.surnameshort" /></p>
            		<p ng-show="userForm.dataSurname.$error.maxlength" class="help-block"><spring:message code="label.surnamelong" /></p>
				</div>
			</div>
			<div class="form-group">
				<label for="inputBirthdate" class="col-sm-2 control-label"><spring:message code="label.birthdate" />*</label>
				<div class="col-sm-10" ng-class="{ 'has-error' : (userForm.dataBirth.$invalid && !userForm.dataBirth.$pristine)
															|| (userForm.dataBirth.$invalid && form3invalidBirthdate)}">
					<p class="input-group">
						<input id="inputBirthdate" ng-model="dataBirth" name="dataBirth"
							ng-change="save1(userForm.$valid)" type="text" class="form-control"
							datepicker-popup="dd.MM.yyyy" ng-model="dt" is-open="opened"
							min-date="'1981-01-01'" max-date="today" 
							close-text="Close" required/> <span class="input-group-btn">
							<button type="button" class="btn btn-default btn-md"
								ng-click="open($event)">
								<i class="glyphicon glyphicon-calendar"></i>
							</button>
						</span>
					</p>
					<p ng-show="(userForm.dataBirth.$error.required && !userForm.dataBirth.$pristine)
							|| (userForm.dataBirth.$error.required && form3invalidBirthdate)" class="help-block"><spring:message code="label.required" /></p>
					<p ng-show="userForm.dataBirth.$error.date" class="help-block"><spring:message code="label.datevalid" /></p>
				</div>
			</div>
			<div class="form-group">
				<label for="inputGenger" class="col-sm-2 control-label"><spring:message code="label.gender" /></label>
				<div class="col-sm-10 form-inline">
					<label class="radio"> <input ng-model="dataGenderM"
						type="radio" name="optionsRadios" id="optionsRadios1"
						value="male" ng-change="genderChange()"> male
					</label> <label class="radio"> <input ng-model="dataGenderF"
						type="radio" name="optionsRadios" id="optionsRadios2"
						value="female" ng-change="genderChange()"> female
					</label>
				</div>
			</div>
			<div class="form-group">
				<label for="inputInsNumber" class="col-sm-2 control-label"><spring:message code="label.insnumber" /></label>
				<div class="col-sm-10" ng-class="{ 'has-error' : userForm.insnumber.$invalid && !userForm.insnumber.$pristine }">
					<input type="text" name="insnumber" class="form-control" ng-change="save1(userForm.$valid)" ng-model="dataInsNumber" 
					ng-pattern="/^[0-9]*$/" ng-minlength="4" ng-maxlength="20" id="inputInsNumber"/>
					<p ng-show="userForm.insnumber.$error.pattern" class="help-block"><spring:message code="label.numbervalid" /></p>
					<p ng-show="userForm.insnumber.$error.minlength" class="help-block"><spring:message code="label.numbershort" /></p>
            		<p ng-show="userForm.insnumber.$error.maxlength" class="help-block"><spring:message code="label.numberlong" /></p>
				</div>
			</div> 
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-success"><spring:message code="label.confirm" /></button>
				</div>
			</div>
		</form>
		</script>
		
	
		<script type="text/ng-template" id="part/addresses">
		<c:forEach var="country" items="${countrys}" varStatus="totalRow"
			step="1">
			<div
				data-ng-init="addCountry(${country.iDCountry}, '${country.title}')">
			</div>
		</c:forEach>
		<c:forEach var="city" items="${cities}" varStatus="totalRow" step="1">
			<div
				data-ng-init="addCity(${city.iDCity}, '${city.title}',${city.iDCountry})">
			</div>
		</c:forEach>
		<c:forEach var="zip" items="${zips}" varStatus="totalRow" step="1">
			<div
				data-ng-init="addZIP(${zip.iDZIP}, '${zip.number}', ${zip.iDCity})">
			</div>
		</c:forEach>
		<c:forEach var="school" items="${schools}" varStatus="totalRow"
			step="1">
			<div
				data-ng-init="addSchool(${school.iDSchool}, '${school.title}', ${school.iDZip})">
			</div>
		</c:forEach>
		<form class="form-horizontal" name="userForm" ng-submit="submitForm2(userForm.$valid)" data-ng-init="loadprofile2(${user.iDUser})"  id="my-profile" novalidate>
			<h2><spring:message code="label.mainaddress" /></h2>
			<div class="form-group">
				<label for="inputTelephone" class="col-sm-2 control-label"><spring:message code="label.telephone" />*</label>
				<div class="col-sm-10"
					ng-class="{ 'has-error' : userForm.dataPhone.$invalid && (!userForm.dataPhone.$pristine || form3invalidTelephone)}">
					<input type="text" name="dataPhone" ng-model="dataPhone"
						ng-change="save2()"
						ng-pattern="/^[0-9]{3}-[0-9]{3}-[0-9]{2}-[0-9]{2}$/"
						ng-maxlength="20" class="form-control" id="inputTelephone"
						required>
					<p ng-show="userForm.dataPhone.$error.required && (!userForm.dataPhone.$pristine || form3invalidTelephone )"
						class="help-block"><spring:message code="label.required" /></p>
					<p ng-show="userForm.dataPhone.$error.maxlength" class="help-block"><spring:message code="label.telephonelong" /></p>
					<p ng-show="userForm.dataPhone.$error.pattern" class="help-block"><spring:message code="label.telephonevalid" /></p>
				</div>
			</div>
			<div class="form-group">
				<label for="inputCountry" class="col-sm-2 control-label"><spring:message code="label.country" />*</label>
				<div class="col-sm-10"
					ng-class="{ 'has-error' : userForm.country.$invalid && (!userForm.country.$pristine || form3invalidCountry) }">
					<select id="inputCountry" class="form-control" name="country"
						ng-model="filterCountry.operator" ng-change="clearCityZIP()"
						isselected>
						<option ng-selected="{{operator.id == filterCountry.operator}}"
							ng-repeat="operator in countrys" value="{{operator.id}}">{{operator.title}}</option>
					</select>
					<p ng-show="userForm.country.$error.isselected && (!userForm.country.$pristine || form3invalidCountry)"
						class="help-block"><spring:message code="label.selectcountry" /></p>
				</div>
			</div>
			<div class="form-group">
				<label for="inputCity" class="col-sm-2 control-label"><spring:message code="label.city" />*</label>
				<div class="col-sm-10"
					ng-class="{ 'has-error' : userForm.selectedCity.$invalid && (!userForm.selectedCity.$pristine || form3invalidCity) }">
					<input type="text" ng-model="selectedCity" name="selectedCity"
						typeahead-on-select="countryByCity()" modelcity required
						typeahead="city as city.title for city in cities | filter: {title:$viewValue}| filter:cityFilter | limitTo:8"
						class="form-control" id="inputCity">
					<p
						ng-show="userForm.selectedCity.$error.modelcity && (!userForm.selectedCity.$pristine || form3invalidCity)"
						class="help-block"><spring:message code="label.selectcity" /></p>
				</div>
			</div>
			<div class="form-group">
				<label for="inputZIP" class="col-sm-2 control-label"><spring:message code="label.zip" />*</label>
				<div class="col-sm-10"
					ng-class="{ 'has-error' : userForm.selectedZIP.$invalid && (!userForm.selectedZIP.$pristine || form3invalidZip)}">
					<input type="text" ng-model="selectedZIP" name="selectedZIP"
						typeahead-on-select="cityByZIP()" modelzip required
						typeahead="zip as zip.number for zip in zips | filter: {number:$viewValue}| filter:zipFilter | limitTo:8"
						class="form-control" id="inputZIP">
					<p
						ng-show="userForm.selectedZIP.$error.modelzip && (!userForm.selectedZIP.$pristine || form3invalidZip)"
						class="help-block"><spring:message code="label.selectzip" /></p>
				</div>
			</div>

			<div class="form-group">
				<label class="col-sm-2 control-label" for="Type"><spring:message code="label.type" />*</label>
				<div class="col-sm-10"
					ng-class="{ 'has-error' : userForm.type.$invalid && (!userForm.type.$pristine || form3invalidType) }">
					<select id="Type" name="type" ng-model="dataAddressType"
						ng-change="save2()" typeselected class="form-control">
						<!-- <option><spring:message code="label.parentsaddress" /></option>
							<option><spring:message code="label.motheraddress" /></option>
							<option><spring:message code="label.fatheraddress" /></option> -->
							<option>Parents address</option>
							<option>Mother address</option>
							<option>Father address</option>
					</select>
					<p ng-show="userForm.type.$error.typeselected && (!userForm.type.$pristine || form3invalidType)"
						class="help-block"><spring:message code="label.selecttype" /></p>
				</div>
			</div>
			<div class="form-group">
				<label for="inputTextAddress" class="col-sm-2 control-label"><spring:message code="label.textaddress" />*</label>
				<div class="col-sm-10"
					ng-class="{ 'has-error' : userForm.dataTextAddress.$invalid && (!userForm.dataTextAddress.$pristine || form3invalidText) }">
					<input ng-model="dataTextAddress" name="dataTextAddress"
						type="text" class="form-control" id="inputTextAddress"
						ng-change="save2()" ng-maxlength="50" required>
					<p ng-show="userForm.dataTextAddress.$error.maxlength"class="help-block"><spring:message code="label.textlong" /></p>
					<p ng-show="userForm.dataTextAddress.$error.required && (!userForm.dataTextAddress.$pristine || form3invalidText)"
						class="help-block"><spring:message code="label.required" /></p>
				</div>
			</div>
			
			
			
		</form>

		<form class="form-horizontal" id="my-profile"
			ng-repeat="(indexA, address) in addresses" name="userForms"
			ng-submit="submitForm2v2(userForms.$valid)" novalidate>
			<hr>
			<div ng-if="indexA == 0">
				<div class="form-group">
					<label for="inputCountry" class="col-sm-2 control-label"><spring:message code="label.country" />*</label>
					<div class="col-sm-10"
					ng-class="{ 'has-error' : userForms.country1.$invalid && (!userForms.country1.$pristine || form2Country1) }">
						<select id="inputCountry" class="form-control" name="country1"
							ng-model="address.country" ng-change="clearCityZIP2(indexA, userForms.$valid)" isselected required>
							<option ng-selected="{{country.id == address.country}}"
								ng-repeat="country in countrys" value="{{country.id}}">{{country.title}}</option>
						</select>
						<p ng-show="userForms.country1.$error.isselected && (!userForms.country1.$pristine || form2Country1)"
						class="help-block"><spring:message code="label.selectcountry" /></p>
					</div>
				</div>
				<div class="form-group">
					<label for="inputCity" class="col-sm-2 control-label"><spring:message code="label.city" />*</label>
					<div class="col-sm-10"
					ng-class="{ 'has-error' : userForms.city1.$invalid && (!userForms.city1.$pristine || form2City1) }">
						<input type="text" ng-model="address.city" name="city1"
							typeahead-on-select="countryByCity2(indexA, userForms.$valid)"
							ng-change="city1change()"
							typeahead="city as city.title for city in cities | filter: {title:$viewValue}| cityFilter : address | limitTo:8"
							class="form-control" id="inputCity" modelcity required>
							<p ng-show="userForms.city1.$error.modelcity && (!userForms.city1.$pristine || form2City1)"
							class="help-block"><spring:message code="label.selectcity" /></p>
					</div>
				</div>
				<div class="form-group">
					<label for="inputZIP" class="col-sm-2 control-label"><spring:message code="label.zip" />*</label>
					<div class="col-sm-10"
					ng-class="{ 'has-error' : userForms.zip1.$invalid && (!userForms.zip1.$pristine || form2Zip1) }">
						<input type="text" ng-model="address.zip" name="zip1"
							typeahead-on-select="cityByZIP2(indexA, userForms.$valid)" modelzip required
							ng-change="city1change()"
							typeahead="zip as zip.number for zip in zips | filter: {number:$viewValue}| zipFilter : address | limitTo:8"
							class="form-control" id="inputZIP">
							<p ng-show="userForms.zip1.$error.modelzip && (!userForms.zip1.$pristine || form2Zip1)"
							class="help-block"><spring:message code="label.selectzip" /></p>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label" for="Type"><spring:message code="label.type" />*</label>
					<div class="col-sm-10"
					ng-class="{ 'has-error' : userForms.type1.$invalid && (!userForms.type1.$pristine || form2Type1) }">
						<select id="Type" ng-model="address.type" ng-change="save2v2(indexA, userForms.$valid)"
							class="form-control" name="type1" typeselected required>
							<!-- <option><spring:message code="label.parentsaddress" /></option>
							<option><spring:message code="label.motheraddress" /></option>
							<option><spring:message code="label.fatheraddress" /></option> -->
							<option>Parents address</option>
							<option>Mother address</option>
							<option>Father address</option>
						</select>
						<p ng-show="userForms.type1.$error.typeselected && (!userForms.type1.$pristine || form2Type1)"
						class="help-block"><spring:message code="label.selecttype" /></p>
					</div>
				</div>
				<div class="form-group">
					<label for="inputTextAddress" class="col-sm-2 control-label"><spring:message code="label.textaddress" />*</label>
					<div class="col-sm-10"
					ng-class="{ 'has-error' : userForms.text1.$invalid && (!userForms.text1.$pristine || form2Text1) }">
						<input ng-model="address.text" type="text" class="form-control" name="text1"
							id="inputTextAddress" ng-change="save2v2(indexA, userForms.$valid)"ng-maxlength="50" required>
						<p ng-show="userForms.text1.$error.maxlength"class="help-block"><spring:message code="label.textlong" /></p>
						<p ng-show="userForms.text1.$error.required && (!userForms.text1.$pristine || form2Text1)"
							class="help-block"><spring:message code="label.required" /></p>
					</div>
				</div>
				
				<button class="btn btn-link btn-dlt"
					ng-click="deleteAddress(address)">Delete</button>
			</div>
			<div ng-if="indexA == 1">
				<div class="form-group">
					<label for="inputCountry" class="col-sm-2 control-label"><spring:message code="label.country" />*</label>
					<div class="col-sm-10"
					ng-class="{ 'has-error' : userForms.country2.$invalid && (!userForms.country2.$pristine || form2Country2) }">
						<select id="inputCountry" class="form-control" name="country2"
							ng-model="address.country" ng-change="clearCityZIP2(indexA, userForms.$valid)" isselected required>
							<option ng-selected="{{country.id == address.country}}"
								ng-repeat="country in countrys" value="{{country.id}}">{{country.title}}</option>
						</select>
						<p ng-show="userForms.country2.$error.isselected && (!userForms.country2.$pristine || form2Country2)"
						class="help-block"><spring:message code="label.selectcountry" /></p>
					</div>
				</div>
				<div class="form-group">
					<label for="inputCity" class="col-sm-2 control-label"><spring:message code="label.city" />*</label>
					<div class="col-sm-10"
					ng-class="{ 'has-error' : userForms.city2.$invalid && (!userForms.city2.$pristine || form2City2) }">
						<input type="text" ng-model="address.city" name="city2"
							typeahead-on-select="countryByCity2(indexA, userForms.$valid)"
							ng-change="city1change()"
							typeahead="city as city.title for city in cities | filter: {title:$viewValue}| cityFilter : address | limitTo:8"
							class="form-control" id="inputCity" modelcity required>
							<p ng-show="userForms.city2.$error.modelcity && (!userForms.city2.$pristine || form2City2)"
							class="help-block"><spring:message code="label.selectcity" /></p>
					</div>
				</div>
				<div class="form-group">
					<label for="inputZIP" class="col-sm-2 control-label"><spring:message code="label.zip" />*</label>
					<div class="col-sm-10"
					ng-class="{ 'has-error' : userForms.zip2.$invalid && (!userForms.zip2.$pristine || form2Zip2) }">
						<input type="text" ng-model="address.zip" name="zip2"
							typeahead-on-select="cityByZIP2(indexA, userForms.$valid)" modelzip required
							ng-change="city1change()"
							typeahead="zip as zip.number for zip in zips | filter: {number:$viewValue}| zipFilter : address | limitTo:8"
							class="form-control" id="inputZIP">
							<p ng-show="userForms.zip2.$error.modelzip && (!userForms.zip2.$pristine || form2Zip2)"
							class="help-block"><spring:message code="label.selectzip" /></p>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label" for="Type"><spring:message code="label.type" />*</label>
					<div class="col-sm-10"
					ng-class="{ 'has-error' : userForms.type2.$invalid && (!userForms.type2.$pristine || form2Type2) }">
						<select id="Type" ng-model="address.type" ng-change="save2v2(indexA, userForms.$valid)"
							class="form-control" name="type2" typeselected required>
							<!-- <option><spring:message code="label.parentsaddress" /></option>
							<option><spring:message code="label.motheraddress" /></option>
							<option><spring:message code="label.fatheraddress" /></option> -->
							<option>Parents address</option>
							<option>Mother address</option>
							<option>Father address</option>
						</select>
						<p ng-show="userForms.type2.$error.typeselected && (!userForms.type2.$pristine || form2Type2)"
						class="help-block"><spring:message code="label.selecttype" /></p>
					</div>
				</div>
				<div class="form-group">
					<label for="inputTextAddress" class="col-sm-2 control-label"><spring:message code="label.textaddress" />*</label>
					<div class="col-sm-10"
					ng-class="{ 'has-error' : userForms.text2.$invalid && (!userForms.text2.$pristine || form2Text2) }">
						<input ng-model="address.text" type="text" class="form-control" name="text2"
							id="inputTextAddress" ng-change="save2v2(indexA, userForms.$valid)"ng-maxlength="50" required>
						<p ng-show="userForms.text2.$error.maxlength"class="help-block"><spring:message code="label.textlong" /></p>
						<p ng-show="userForms.text2.$error.required && (!userForms.text2.$pristine || form2Text2)"
							class="help-block"><spring:message code="label.required" /></p>
					</div>
				</div>
				<button class="btn btn-link btn-dlt"
					ng-click="deleteAddress(address)"><spring:message code="label.delete" /></button>
			</div>
		</form>

		<hr>
		<div class="btn-add" ng-show="visible">
			<button class="btn btn-link"
				ng-click="number=number+1;addAddress(number)"><spring:message code="label.addonemore" /></button>
		</div>
			<div style="padding-left : 165px; padding-top: 20px;">
					<button type="submit" form="my-profile" class="btn btn-success"><spring:message code="label.confirm" /></button>
			</div>
		</script>
		<script type="text/ng-template" id="part/schools">
		<c:forEach var="country" items="${countrys}" varStatus="totalRow"
			step="1">
			<div
				data-ng-init="addCountry(${country.iDCountry}, '${country.title}')">
			</div>
		</c:forEach>
		<c:forEach var="city" items="${cities}" varStatus="totalRow" step="1">
			<div
				data-ng-init="addCity(${city.iDCity}, '${city.title}',${city.iDCountry})">
			</div>
		</c:forEach>
		<c:forEach var="zip" items="${zips}" varStatus="totalRow" step="1">
			<div
				data-ng-init="addZIP(${zip.iDZIP}, '${zip.number}', ${zip.iDCity})">
			</div>
		</c:forEach>
		<c:forEach var="school" items="${schools}" varStatus="totalRow"
			step="1">
			<div
				data-ng-init="addSchool(${school.iDSchool}, '${school.title}', ${school.iDZip})">
			</div>
		</c:forEach>
		<form class="form-horizontal"id="my-profile" name="userForm" ng-submit="submitForm3(userForm.$valid)" data-ng-init="loadprofile3(${user.iDUser})" novalidate>
		
			<div class="form-group">
				<label for="inputCountry" class="col-sm-2 control-label"><spring:message code="label.country" />*</label>
				<div class="col-sm-10" ng-class="{ 'has-error' : (userForm.country.$invalid && !userForm.country.$pristine) 
														|| (userForm.country.$error.isselected && form3invalidCountry) }">
					<select id="inputCountry" class="form-control" name="country" isselected
						ng-model="filterCountry.operator" ng-change="clearCityZIP3(userForm.$valid)"> 
						<option ng-selected="{{operator.id == filterCountry.operator}}"
							ng-repeat="operator in countrys" value="{{operator.id}}">{{operator.title}}</option>
					</select>
					<p ng-show="(userForm.country.$error.isselected && !userForm.country.$pristine) 
							|| (userForm.country.$error.isselected && form3invalidCountry)" class="help-block"><spring:message code="label.selectcountry" /></p>
				</div>
			</div>
			<div class="form-group">
				<label for="inputCity" class="col-sm-2 control-label"><spring:message code="label.city" />*</label>
				<div class="col-sm-10" ng-class="{ 'has-error' : (userForm.selectedCity.$invalid && !userForm.selectedCity.$pristine) 
															|| (userForm.selectedCity.$error.modelcity && form3invalidCity) }">
					<input type="text" ng-model="selectedCity" name="selectedCity"
						typeahead-on-select="countryByCity3(userForm.$valid)" modelcity required
						typeahead="city as city.title for city in cities | filter: {title:$viewValue}| filter:cityFilter | limitTo:8"
						class="form-control" id="inputCity" >
						<p ng-show="(userForm.selectedCity.$error.modelcity && !userForm.selectedCity.$pristine) 
						|| (userForm.selectedCity.$error.modelcity && form3invalidCity)" class="help-block"><spring:message code="label.selectcity" /></p>
				</div>
			</div>
			<div class="form-group">
				<label for="inputZIP" class="col-sm-2 control-label"><spring:message code="label.zip" />*</label>
				<div class="col-sm-10" ng-class="{ 'has-error' : (userForm.selectedZIP.$invalid && !userForm.selectedZIP.$pristine) 
														|| (userForm.selectedZIP.$error.modelzip && form3invalidZip) }">
					<input type="text" ng-model="selectedZIP" name="selectedZIP"
						typeahead-on-select="cityByZIP3(userForm.$valid)" modelzip required
						typeahead="zip as zip.number for zip in zips | filter: {number:$viewValue}| filter:zipFilter | limitTo:8"
						class="form-control" id="inputZIP" >
						<p ng-show="(userForm.selectedZIP.$error.modelzip && !userForm.selectedZIP.$pristine) 
								|| (userForm.selectedZIP.$error.modelzip && form3invalidZip)" class="help-block"><spring:message code="label.selectzip" /></p>
				</div>
			</div>
			
			<div class="form-group">
				<label for="inputSchool" class="col-sm-2 control-label"><spring:message code="label.school" />*</label>
				<div class="col-sm-10" ng-class="{ 'has-error' : (userForm.selectedSchool.$invalid && !userForm.selectedSchool.$pristine) 
														|| (userForm.selectedSchool.$error.modelschool && form3invalidSchool) }">
					<input type="text" ng-model="selectedSchool" name="selectedSchool"
						typeahead-on-select="saveSchool(userForm.$valid)" modelschool required
						typeahead="school as school.title for school in schools | filter: {title:$viewValue} | filter: schoolFilter | limitTo:8"
						class="form-control" id="inputSchool" >
						<p ng-show="(userForm.selectedSchool.$error.modelschool && !userForm.selectedSchool.$pristine) 
							|| (userForm.selectedSchool.$error.modelschool && form3invalidSchool)" class="help-block"><spring:message code="label.selectschool" /></p>
				</div>
			</div>
			<div class="form-group">
				<label for="inputDescription" class="col-sm-2 control-label"><spring:message code="label.description" /></label>
				<div class="col-sm-10" ng-class="{ 'has-error' : userForm.descriptionSchool.$invalid && !userForm.descriptionSchool.$pristine }">
					<textarea ng-model="descriptionSchool" ng-change="saveDesc(userForm.$valid)" name="descriptionSchool" ng-maxlength="300"
						class="form-control" id="inputDescription" rows="5"></textarea>
						<p ng-show="userForm.descriptionSchool.$error.maxlength" class="help-block"><spring:message code="label.descriptionlong" /></p>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-success"><spring:message code="label.confirm" /></button>
				</div>
			</div>
		</form>

		</script>
	
		
		<script type="text/ng-template" id="part/wish">
		<form class="form-horizontal" role="form" id="my-profile" name="userForm" ng-submit="submitForm4(userForm.$valid)" data-ng-init="loadprofile4(${user.iDUser})"  novalidate>
			<div ng-if="wishUpdate">
				<alert ng-repeat="alert in alerts" type="{{alert.type}}"
				close="closeAlert($index)">{{alert.msg}}</alert>
			</div>
			<div class="form-group">
				<label for="inputWishes" class="col-sm-2 control-label"><spring:message code="label.otherwishes" /></label>
				<div class="col-sm-10" ng-class="{ 'has-error' : userForm.dataWishes.$invalid && !userForm.dataWishes.$pristine }">
					<textarea ng-model="dataWishes" name="dataWishes" ng-change="save4()" ng-maxlength="300" 
						class="form-control" id="inputWishes" rows="10"></textarea>
						<p ng-show="userForm.dataWishes.$error.maxlength" class="help-block"><spring:message code="label.textlong" /></p>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-success"><spring:message code="label.confirm" /></button>
				</div>
			</div>
		</form>
		</script>




</t:template>