<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<t:template>


	<div class="profile-adress" ng-controller="StorageCtrl"
		data-ng-init="load2()">
		<h3><spring:message code="label.createaccountpage" /> 2/4</h3>

		<c:forEach var="country" items="${countrys}" varStatus="totalRow"
			step="1">
			<div
				data-ng-init="addCountry(${country.iDCountry}, '${country.title}')">
			</div>
		</c:forEach>
		<c:forEach var="city" items="${citys}" varStatus="totalRow" step="1">
			<div
				data-ng-init="addCity(${city.iDCity}, '${city.title}',${city.iDCountry})">
			</div>
		</c:forEach>
		<c:forEach var="zip" items="${zips}" varStatus="totalRow" step="1">
			<div
				data-ng-init="addZIP(${zip.iDZIP}, '${zip.number}', ${zip.iDCity})">
			</div>
		</c:forEach>


		<form class="form-horizontal" id="profile-main"name="userForm"
			ng-submit="submitForm2(userForm.$valid)" novalidate>
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
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<div class="checkbox">
						<label> <input ng-model="dataMain"
							ng-change='mainChange()' type="checkbox" ng-init="addressInit()"
							ng-true-value="'yes'" ng-false-value="'no'"> <spring:message code="label.mainaddress" />
						</label>
					</div>
				</div>
			</div>
			
		</form>


		<form class="form-horizontal" id="profile-address"
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
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<div class="checkbox">
							<label> <input ng-model="address.main"
								ng-change='addressChange(indexA)' type="checkbox"
								ng-true-value="'yes'" ng-false-value="'no'"> <spring:message code="label.mainaddress" />
							</label>
						</div>
					</div>
				</div>
				<button class="btn btn-link btn-dlt"
					ng-click="deleteAddress(address)"><spring:message code="label.delete" /></button>
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
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<div class="checkbox">
							<label> <input ng-model="address.main"
								ng-change='addressChange(indexA)' type="checkbox"
								ng-true-value="'yes'" ng-false-value="'no'"> <spring:message code="label.mainaddress" />
							</label>
						</div>
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
		<div style="width: 400px; margin: 0 auto;">
		<ul class="pagination">
			  <li><a href="create-account-page-1">&larr; <spring:message code="label.prevstep" /></a></li>
		      <li><a href="create-account-page-1">1 <span class="sr-only">(current)</span></a></li>
		      <li class="active"><a href="#">2</a></li>
		      <li ng-show="submit3"><a href="create-account-page-3">3</a></li>
		      <li ng-show="submit4"><a href="create-account-page-4">4</a></li>
		      <li><button type="submit" form="profile-main" class="btn-next"><spring:message code="label.nextstep" />
					&rarr;</button></li>
		   </ul>
		   </div>
	</div>
</t:template>