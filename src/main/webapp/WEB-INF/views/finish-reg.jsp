<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<t:template>
<div ng-controller="StorageCtrl">
	<div ng-show="showfinish">
		<div class="row" id="finish-up">
		<div class="col-md-2">
			<a href="create-account-page-4"><button type="button"
					class="btn btn-link">&larr; <spring:message code="label.backtoedit" /></button></a>
		</div>
		<div class="col-md-8" id="finish-up" >
			<h2><spring:message code="label.error" />!</h2>
			<h3>
				<small><spring:message code="label.finishreg2" /></small>
			</h3>
		</div>
	</div>
	</div>
	<div ng-hide="showfinish">
	<div class="row" id="finish-up">
		<div class="col-md-2">
			<a href="create-account-page-4"><button type="button"
					class="btn btn-link">&larr; <spring:message code="label.backtoedit" /></button></a>
		</div>
		<div class="col-md-8" id="finish-up" >
			<h2><spring:message code="label.finishreg" />!</h2>
			<h3>
				<small><spring:message code="label.finishreg1" /></small>
			</h3>
		</div>
	</div>
	
	<form:form method="post"  action="accountcreation" commandName="reg">
	<div  class="row" id="finish-content"  data-ng-init="loadFinish()">

		<div class="col-md-1"></div>
		
			<div class="col-md-5">
				<div class="panel panel-primary">
					<div class="panel-heading"><spring:message code="label.persinfo" /></div>
					<div class="panel-body">
						<table class="table">
							
						      <tbody>
						      	<tr>
						      		<td style="font-weight: bold; width: 30%"><spring:message code="label.username" /></td>
						      		<td ><form:input path="user.username" value="{{dataUsername}}" readonly="true" style="border: 0px;"/></td>
						      	</tr>
						      	<tr>
						      		<td style="font-weight: bold; width: 30%"><spring:message code="label.name" /></td>
						      		<td><form:input path="user.name" value="{{dataName}}" readonly="true" style="border: 0px;"/></td>
						      	</tr>
						      	<tr>
						      		<td style="font-weight: bold; width: 30%"><spring:message code="label.surname" /></td>
						      		<td ><form:input path="user.surname" value="{{dataSurname}}" readonly="true" style="border: 0px;"/></td>
						      	</tr>
						      	<tr>
						      		<td style="font-weight: bold; width: 30%"><spring:message code="label.birthdate" /></td>
						      		<td><form:input path="user.birthdate" value="{{dataBirthdate | date:'yyyy-MM-dd' }}" readonly="true" style="border: 0px;"/></td>
						      	</tr>
						      	<tr>
						      		<td style="font-weight: bold; width: 30%"><spring:message code="label.gender" /></td>
						      		<td ><form:input path="user.gender" value="{{dataGender}}" readonly="true" style="border: 0px;"/></td>
						      	</tr>
						      	<tr>
						      		<td style="font-weight: bold; width: 30%"><spring:message code="label.insnumber" /></td>
						      		<td ><form:input path="user.insnumber" value="{{dataInsNumber}}" readonly="true" style="border: 0px;"/></td>
						      	</tr>
						      </tbody>
					</table>
					</div>
				</div>

				<div class="panel panel-primary">
					<div class="panel-heading"><spring:message code="label.prevschool" /></div>
					<div class="panel-body">
						<table class="table">
							
						      <tbody>
						      	<tr>
						      		<td style="font-weight: bold; width: 30%"><spring:message code="label.city" /></td>
						      		<td ><input value="{{schoolCity}}" readonly="true" style="border: 0px;"/></td>
						      	</tr>
						      	<tr>
						      		<td style="font-weight: bold; width: 30%"><spring:message code="label.zip" /></td>
						      		<td >
						      			<input  value="{{schoolZIP}}" readonly="true" style="border: 0px;"/>
						      		</td>
						      	</tr>
						      	<tr>
						      		<td style="font-weight: bold; width: 30%"><spring:message code="label.school" /></td>
						      		<td>
						      			<input  value="{{dataSchool}}" readonly="true" style="border: 0px;"/>
						      			<form:input type="hidden" path="school.iDSchool" value="{{dataSchoolid}}" readonly="true" style="border: 0px;"/>
						      		</td>
						      	</tr>
						      	<tr>
						      		<td style="font-weight: bold; width: 30%"><spring:message code="label.description" /></td>
						      		<td>{{dataDescription}}
						      		<form:input path="userschool.description" type="hidden" value="{{dataDescription}}" readonly="true" style="border: 0px;"/>
						      		</td>
						      	</tr>
						      </tbody>
						</table>
					</div>
				</div>
			</div>
			<div class="col-md-5">
				<div class="panel panel-primary">
					<div class="panel-heading"><spring:message code="label.addresses" /></div>
					<div class="panel-body">
						<table class="table">
							<tbody>
								<tr>
						      		<td style="font-weight: bold; width: 30%"><spring:message code="label.telephone" /></td>
						      		<td><form:input path="telephone.numberphone" value="{{dataPhone}}" readonly="true" style="border: 0px;"/></td>
						      	</tr>
						      	<tr>
						      		<td style="font-weight: bold; width: 30%"><spring:message code="label.country" /></td>
						      		<td ><input  value="{{dataCountry}}" readonly="readonly" style="border: 0px;"/></td>
						      	</tr>
						      	<tr>
						      		<td style="font-weight: bold; width: 30%"><spring:message code="label.city" /></td>
						      		<td ><input value="{{dataCity}}" readonly="readonly" style="border: 0px;"/></td>
						      	</tr>
						      	<tr>
						      		<td style="font-weight: bold; width: 30%"><spring:message code="label.zip" /></td>
						      		<td >
						      			<input value="{{dataZIP}}" readonly="true" style="border: 0px;"/>
						      			<form:input type="hidden" path="dataAddress.iDZip" value="{{dataZIPid}}" readonly="true" style="border: 0px;"/>
						      		</td>
						      	</tr>
						      	
						      	<tr>
						      		<td style="font-weight: bold; width: 30%"><spring:message code="label.textaddress" /></td>
						      		<td ><form:input path="dataUserAddress.type" value="{{dataType}}" readonly="true" style="border: 0px;"/></td>
						      	</tr>
						      	<tr>
							      		<td style="font-weight: bold; width: 30%"><spring:message code="label.textaddress" /></td>
							      		<td ><form:input path="dataAddress.textAdress" value="{{dataText}}" readonly="true" style="border: 0px;"/></td>
							      	</tr>
						      	<tr>
						      		<td style="font-weight: bold; width: 30%"><spring:message code="label.mainaddress" /></td>
						      		<td ><form:input path="dataUserAddress.isMain" value="{{dataMain}}" readonly="true" style="border: 0px;"/></td>
						      	</tr>
						      </tbody>
						</table>
						<div ng-repeat="(index, address) in addresses">
							<hr>
							<div ng-if="index == 0">
								<table class="table">
								<tbody>
							      	<tr>
							      		<td style="font-weight: bold; width: 30%"><spring:message code="label.country" /></td>
							      		<td ><input value="{{address.country}}" readonly="readonly" style="border: 0px;"/></td>
							      	</tr>
							      	<tr>
							      		<td style="font-weight: bold; width: 30%"><spring:message code="label.city" /></td>
							      		<td ><input value="{{address.city.title}}" readonly="readonly" style="border: 0px;"/></td>
							      	</tr>
							      	<tr>
							      		<td style="font-weight: bold; width: 30%"><spring:message code="label.zip" /></td>
							      		<td >
							      			<input value="{{address.zip.number}}" readonly="true" style="border: 0px;"/>
							      			<form:input type="hidden" path="address1.iDZip" value="{{address.zip.id}}" readonly="true" style="border: 0px;"/>
							      		</td>
							      	</tr>
							      	
							      	<tr>
							      		<td style="font-weight: bold; width: 30%"><spring:message code="label.type" /></td>
							      		<td ><form:input path="userAddress1.type" value="{{address.type}}" readonly="true" style="border: 0px;"/></td>
							      	</tr>
							      	<tr>
							      		<td style="font-weight: bold; width: 30%"><spring:message code="label.textaddress" /></td>
							      		<td ><form:input path="address1.textAdress" value="{{address.text}}" readonly="true" style="border: 0px;"/></td>
							      	</tr>
							      	<tr>
							      		<td style="font-weight: bold; width: 30%"><spring:message code="label.mainaddress" /></td>
							      		<td ><form:input path="userAddress1.isMain"  value="{{address.main}}" readonly="true" style="border: 0px;"/></td>
							      	</tr>
							      </tbody>
							</table>
							</div>
							<div ng-if="index == 1">
								<table class="table">
								<tbody>
							      	<tr>
							      		<td style="font-weight: bold; width: 30%"><spring:message code="label.country" /></td>
							      		<td ><input value="{{address.country}}" readonly="readonly" style="border: 0px;"/></td>
							      	</tr>
							      	<tr>
							      		<td style="font-weight: bold; width: 30%"><spring:message code="label.city" /></td>
							      		<td ><input value="{{address.city.title}}" readonly="readonly" style="border: 0px;"/></td>
							      	</tr>
							      	<tr>
							      		<td style="font-weight: bold; width: 30%"><spring:message code="label.zip" /></td>
							      		<td >
							      			<input value="{{address.zip.number}}" readonly="true" style="border: 0px;"/>
							      			<form:input type="hidden" path="address2.iDZip" value="{{address.zip.id}}" readonly="true" style="border: 0px;"/>
							      		</td>
							      	</tr>
							      	
							      	<tr>
							      		<td style="font-weight: bold; width: 30%"><spring:message code="label.type" /></td>
							      		<td ><form:input path="userAddress2.type" value="{{address.type}}" readonly="true" style="border: 0px;"/></td>
							      	</tr>
							      	<tr>
							      		<td style="font-weight: bold; width: 30%"><spring:message code="label.textaddress" /></td>
							      		<td ><form:input path="address2.textAdress" value="{{address.text}}" readonly="true" style="border: 0px;"/></td>
							      	</tr>
							      	<tr>
							      		<td style="font-weight: bold; width: 30%"><spring:message code="label.mainaddress" /></td>
							      		<td ><form:input path="userAddress2.isMain"  value="{{address.main}}" readonly="true" style="border: 0px;"/></td>
							      	</tr>
							      </tbody>
							</table>
							</div>
						</div>
						
					</div>
				</div>
				<div class="panel panel-primary">
					<div class="panel-heading"><spring:message code="label.otherwishes" /></div>
					<div class="panel-body">
						<table class="table">
							<tbody>
						      	<tr>
						      		<td style="font-weight: bold; width: 30%"><spring:message code="label.wishes" /></td>
						      		<td >{{dataWishes}}
						      		<form:input path="wish.description" type="hidden" value="{{dataWishes}}" readonly="true" style="border: 0px;"/>
						      		</td>
						      	</tr>
						    </tbody>
						 </table>
					</div>
				</div>
			</div>
			
	</div>
	<div class="row" id="finish-down" >
		<input class="btn btn-primary btn-lg btn-block" type="submit" value="<spring:message code="label.finish" />!"/>
	</div>
	</form:form>
	</div>
	</div>
</t:template>