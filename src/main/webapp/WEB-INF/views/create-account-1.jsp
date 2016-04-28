<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<t:template>

	<div class="profile-adress" ng-controller="StorageCtrl"
		data-ng-init="load1()">
		<h3><spring:message code="label.createaccountpage" /> 1/4</h3>
		
    
		<form class="form-horizontal"id="profile-main" name="userForm" ng-submit="submitForm(userForm.$valid)" novalidate>

			<div class="form-group">
				<label for="inputUsername" class="col-sm-2 control-label"><spring:message code="label.username" />*</label>
				<div class="col-sm-10" ng-class="{ 'has-error' : (userForm.dataUsername.$invalid && !userForm.dataUsername.$pristine)
																|| (userForm.dataUsername.$invalid && form3invalidUsername)}">
					<input ng-model="dataUsername" type="text" name="dataUsername" class="form-control"
						id="inputUsername"  ng-change="save1(userForm.$valid)" ensure-unique="{{dataUsername}}" ng-pattern="/^[a-zA-Z0-9]*$/" ng-minlength="4" ng-maxlength="20" required>
					<p ng-show="(userForm.dataUsername.$error.required && !userForm.dataUsername.$pristine)
								|| (userForm.dataUsername.$error.required && form3invalidUsername)" class="help-block"><spring:message code="label.required" /></p>
					<p ng-show="userForm.dataUsername.$error.pattern" class="help-block"><spring:message code="label.usernamevalid" /></p>
					<p ng-show="userForm.dataUsername.$error.minlength" class="help-block"><spring:message code="label.usernameshort" /></p>
            		<p ng-show="userForm.dataUsername.$error.maxlength" class="help-block"><spring:message code="label.usernamelong" /></p>
            		<p ng-show="userForm.dataUsername.$error.unique" class="help-block"><spring:message code="label.usernametaken" /></p>
				</div>
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
						value="male" ng-change="save1(userForm.$valid)"> <spring:message code="label.male" />
					</label> <label class="radio"> <input ng-model="dataGenderF"
						type="radio" name="optionsRadios" id="optionsRadios2"
						value="female" ng-change="save1(userForm.$valid)"> <spring:message code="label.female" />
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
			<div style="width: 400px; margin: 0 auto;">
			<ul class="pagination" >
			  <li class="disabled"><a href="#">&larr; <spring:message code="label.prevstep" /></a></li>
		      <li class="active"><a href="#">1 <span class="sr-only">(current)</span></a></li>
		      
		      <li ng-show="submit2"><a href="create-account-page-2">2</a></li>
		      <li ng-show="submit3"><a href="create-account-page-3">3</a></li>
		      <li ng-show="submit4"><a href="create-account-page-4">4</a></li>
		      <li><button type="submit" class="btn-next"><spring:message code="label.nextstep" />
					&rarr;</button></li>
		  	 </ul>
		   	</div>
		</form>
		
	</div>
</t:template>