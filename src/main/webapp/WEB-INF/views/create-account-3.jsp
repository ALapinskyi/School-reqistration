<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<t:template>

	<div class="profile-adress" ng-controller="StorageCtrl"
		data-ng-init="load3()">
		<h3><spring:message code="label.createaccountpage" /> 3/4</h3>
		
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
		<c:forEach var="school" items="${schools}" varStatus="totalRow"
			step="1">
			<div
				data-ng-init="addSchool(${school.iDSchool}, '${school.title}', ${school.iDZip})">
			</div>
		</c:forEach>

		<form class="form-horizontal"id="profile-main" name="userForm" ng-submit="submitForm3(userForm.$valid)" novalidate>
		
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
			<div style="width: 400px; margin: 0 auto;">
			<ul class="pagination">
			  <li><a href="create-account-page-2">&larr; <spring:message code="label.prevstep" /></a></li>
		      <li><a href="create-account-page-1">1 <span class="sr-only">(current)</span></a></li>
		      <li><a href="create-account-page-2">2</a></li>
		      <li class="active"><a href="#">3</a></li>
		      <li ng-show="submit4"><a href="create-account-page-4">4</a></li>
		      <li><button type="submit" class="btn-next"><spring:message code="label.nextstep" /> &rarr;</button></li>
		   </ul>
		   </div>
		</form>

	</div>
</t:template>