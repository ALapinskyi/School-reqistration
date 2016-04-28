<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<t:template>

	
	
	<div class="col-md-2">
		<a href="/schoolreg/list-of-students"><button type="button"
					class="btn btn-link">&larr; <spring:message code="label.backtolist" /></button></a>
		
		
	</div>
	<div class="col-md-8" ng-controller="TableCtrl" data-ng-init="loadFirst()"> 
		<div class="row" id="user-header">
			<div class="col-md-3"></div>
			<div class="col-md-6">
				<div >
					<h3>{{profiles[0].name}} {{profiles[0].surname}}</h3>
					<h3><small><spring:message code="label.birthdate" />: {{profiles[0].birthdate | date:'dd.MM.yyyy'}}</small></h3>
					<div ng-if="notNull(profiles[index].gender)">
						<h3><small><spring:message code="label.gender" />: {{profiles[0].gender}}</small></h3>
					</div>
					<div ng-if="notNull(profiles[index].insnumber)">
						<h3><small><spring:message code="label.insnumber" />: {{profiles[0].insnumber}}</small></h3>
					</div>
				</div>
			</div>
			<div class="col-md-2">
				<div class="row" style="text-align: center;">
					
					<button ng-show="(items.length == 1) || index == 0" ng-click="prevUser()" class="btn btn-default disabled">&larr;</button>
				
					<button ng-show="index > 0" ng-click="prevUser()" class=btn btn-default btn-round">&larr;</button>
			
					<button ng-show="(items.length == 1) || ((index+1) == items.length)" ng-click="prevUser()" class="btn btn-default disabled">&rarr;</button>
				
					<button ng-show="(index+1) < items.length" ng-click="nextUser()" class="btn btn-default">&rarr;</button>
				
					
				</div>
				<div class="row" style="text-align: center;">
						{{index+1}}/{{items.length}}
				</div>
			</div>
			
		</div>
		<div class="row">
			<hr>
			<div class="col-md-3">
				<div class="collapse navbar-collapse" ng-controller="TableCtrl">
				    <ul class="nav nav-pills nav-stacked">
				        <li class="{ active: isActive('/main')}"><a href="#/main"><spring:message code="label.addresses" /></a></li>
				        <li class="{ active: isActive('/school')}"><a href="#/school"><spring:message code="label.school" /></a></li>
				        <li class="{ active: isActive('/wishes')}"><a href="#/wishes"><spring:message code="label.wishes" /></a></li>
				    </ul>
				</div>
			</div>
			<div class="col-md-8">
				<div ng-view></div>
			</div>
			
		</div>
		
	</div>
	<div class="col-md-1"></div>
	<div class="col-md-1">
		<a href="<c:url value="/logout" />"><button type="button"
				class="btn btn-primary"><spring:message code="label.logout" /></button></a>
	</div>
	<script type="text/ng-template" id="part/school">
    <div class="panel panel-primary">
					<div class="panel-heading"><spring:message code="label.prevschool" /></div>
					<div class="panel-body">
						<table class="table">
							
						      <tbody>
						      	<tr>
						      		<td style="font-weight: bold; width: 30%"><spring:message code="label.city" /></td>
						      		<td ><input value="{{profiles[0].citytitle}}" readonly="true" style="border: 0px;"/></td>
						      	</tr>
						      	<tr>
						      		<td style="font-weight: bold; width: 30%"><spring:message code="label.zip" /></td>
						      		<td >
						      			<input  value="{{profiles[0].zipnumber}}" readonly="true" style="border: 0px;"/>
						      		</td>
						      	</tr>
						      	<tr>
						      		<td style="font-weight: bold; width: 30%"><spring:message code="label.school" /></td>
						      		<td>
						      			<input  value="{{profiles[0].schooltitle}}" readonly="true" style="border: 0px;"/>
						      		</td>
						      	</tr>
						      	<tr>
						      		<td style="font-weight: bold; width: 30%"><spring:message code="label.description" /></td>
						      		<td><input  value="{{profiles[0].schooldesc}}" readonly="true" style="border: 0px;"/></td>
						      	</tr>
						      </tbody>
						</table>
					</div>
				</div>
			</div>
	</script>
	
	<script type="text/ng-template" id="part/main">
				<div class="panel panel-primary">
					<div class="panel-heading"><spring:message code="label.addresses" /></div>
					<div class="panel-body">
						<table class="table">
							<tbody>
								<tr>
						      		<td style="font-weight: bold; width: 30%"><spring:message code="label.telephone" /></td>
						      		<td><input value="{{profiles[0].telephone}}" readonly="true" style="border: 0px;"/></td>
						      	</tr>
						      	<tr>
						      		<td style="font-weight: bold; width: 30%"><spring:message code="label.country" /></td>
						      		<td ><input  value="{{profiles[0].maincountry}}" readonly="readonly" style="border: 0px;"/></td>
						      	</tr>
						      	<tr>
						      		<td style="font-weight: bold; width: 30%"><spring:message code="label.city" /></td>
						      		<td ><input value="{{profiles[0].maincity}}" readonly="readonly" style="border: 0px;"/></td>
						      	</tr>
						      	<tr>
						      		<td style="font-weight: bold; width: 30%"><spring:message code="label.zip" /></td>
						      		<td >
						      			<input value="{{profiles[0].mainzip}}" readonly="true" style="border: 0px;"/>
						      		</td>
						      	</tr>
						      	
						      	<tr>
						      		<td style="font-weight: bold; width: 30%"><spring:message code="label.type" /></td>
						      		<td ><input  value="{{profiles[0].maintype}}" readonly="true" style="border: 0px;"/></td>
						      	</tr>
						      	<tr>
							      		<td style="font-weight: bold; width: 30%"><spring:message code="label.textaddress" /></td>
							      		<td ><input value="{{profiles[0].textaddress}}" readonly="true" style="border: 0px;"/></td>
							      	</tr>
						      	<tr>
						      		<td style="font-weight: bold; width: 30%"><spring:message code="label.mainaddress" /></td>
						      		<td ><input value="{{profiles[0].mainismain}}" readonly="true" style="border: 0px;"/></td>
						      	</tr>
						      </tbody>
						</table>
							
							<div ng-if="profiles[0].country1 != undefined">
								<hr>
								<table class="table">
								<tbody>
							      	<tr>
							      		<td style="font-weight: bold; width: 30%"><spring:message code="label.country" /></td>
							      		<td ><input value="{{profiles[0].country1}}" readonly="readonly" style="border: 0px;"/></td>
							      	</tr>
							      	<tr>
							      		<td style="font-weight: bold; width: 30%"><spring:message code="label.city" /></td>
							      		<td ><input value="{{profiles[0].city1}}" readonly="readonly" style="border: 0px;"/></td>
							      	</tr>
							      	<tr>
							      		<td style="font-weight: bold; width: 30%"><spring:message code="label.zip" /></td>
							      		<td >
							      			<input value="{{profiles[0].zip1}}" readonly="true" style="border: 0px;"/>
							      		</td>
							      	</tr>
							      	
							      	<tr>
							      		<td style="font-weight: bold; width: 30%"><spring:message code="label.type" /></td>
							      		<td ><input  value="{{profiles[0].type1}}" readonly="true" style="border: 0px;"/></td>
							      	</tr>
							      	<tr>
							      		<td style="font-weight: bold; width: 30%"><spring:message code="label.textaddress" /></td>
							      		<td ><input  value="{{profiles[0].textaddress1}}" readonly="true" style="border: 0px;"/></td>
							      	</tr>
							      	<tr>
							      		<td style="font-weight: bold; width: 30%"><spring:message code="label.mainaddress" /></td>
							      		<td ><input  value="{{profiles[0].ismain1}}" readonly="true" style="border: 0px;"/></td>
							      	</tr>
							      </tbody>
							</table>
							</div>
							
							<div ng-if="profiles[0].country2 != undefined">
								<hr>
								<table class="table">
								<tbody>
							      	<tr>
							      		<td style="font-weight: bold; width: 30%"><spring:message code="label.country" /></td>
							      		<td ><input value="{{profiles[0].country2}}" readonly="readonly" style="border: 0px;"/></td>
							      	</tr>
							      	<tr>
							      		<td style="font-weight: bold; width: 30%"><spring:message code="label.city" /></td>
							      		<td ><input value="{{profiles[0].city2}}" readonly="readonly" style="border: 0px;"/></td>
							      	</tr>
							      	<tr>
							      		<td style="font-weight: bold; width: 30%"><spring:message code="label.zip" /></td>
							      		<td >
							      			<input value="{{profiles[0].zip2}}" readonly="true" style="border: 0px;"/>
							      		</td>
							      	</tr>
							      	
							      	<tr>
							      		<td style="font-weight: bold; width: 30%"><spring:message code="label.type" /></td>
							      		<td ><input  value="{{profiles[0].type2}}" readonly="true" style="border: 0px;"/></td>
							      	</tr>
							      	<tr>
							      		<td style="font-weight: bold; width: 30%"><spring:message code="label.textaddress" /></td>
							      		<td ><input  value="{{profiles[0].textaddress2}}" readonly="true" style="border: 0px;"/></td>
							      	</tr>
							      	<tr>
							      		<td style="font-weight: bold; width: 30%"><spring:message code="label.mainaddress" /></td>
							      		<td ><input  value="{{profiles[0].ismain2}}" readonly="true" style="border: 0px;"/></td>
							      	</tr>
							      </tbody>
							</table>
							</div>
						</div>
						
					</div>
	</script>
	
	<script type="text/ng-template" id="part/wishes">
    <div class="panel panel-primary">
					<div class="panel-heading"><spring:message code="label.otherwishes" /></div>
					<div class="panel-body">
						<table class="table">
							<tbody>
						      	<tr>
						      		<td style="font-weight: bold; width: 30%"><spring:message code="label.wishes" /></td>
						      		<td ><input value="{{profiles[0].wishes}}" readonly="true" style="border: 0px;"/></td>
						      	</tr>
						    </tbody>
						 </table>
					</div>
				</div>
	</script>
</t:template>