<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<t:template>

	<div class="col-md-1"></div>
	
	<div class="col-md-10" ng-controller="TableCtrl" > 
		
		<c:forEach var="user" items="${users}" varStatus="totalRow"
			step="1">
			<div
				data-ng-init="addUsers(${user.iDUser}, '${user.name}', '${user.surname}', '${user.birthdate}', 
				'${user.gender}', '${user.insnumber}', ${user.iDRole})">
				
			</div>
		</c:forEach>
		<h2>All registered students</h2>
		<div ng-show="lists.length == 0">
			<a href="<c:url value="/profiles" />"><button class="btn btn-default disabled"><spring:message code="label.process" /></button></a>
			<button ng-click="tableParams.sorting({})" class="btn btn-default"><spring:message code="label.clearsorting" /></button>
		</div>
		<div ng-show="lists.length >= 1">
			<a href="<c:url value="/profiles" />"><button class="btn btn-default"><spring:message code="label.process" /></button></a>
			<button ng-click="tableParams.sorting({})" class="btn btn-default"><spring:message code="label.clearsorting" /></button>
		</div>
		
		

	    <!-- {{checkboxes.item}}/{{length}} -->
	
	    <table ng-table="tableParams" show-filter="true" class="table" data-ng-init="getLength()">
	        <tr ng-repeat="user in $data | filter: {role:2}" ng-class="{ 'emphasis': user.money > 500 }">
	            <td width="30" style="text-align: left" header="'ng-table/headers/checkbox.html'">
	                <input type="checkbox" ng-model="checkboxes.items[user.id]" ng-change="checkboxChanged()"/>
	            </td>
	            <td data-title="'<spring:message code="label.name" />'" filter="{ 'name': 'text' }" sortable="'name'">
	                {{user.name}}
	            </td>
	            <td data-title="'<spring:message code="label.surname" />'" filter="{ 'surname': 'text' }" sortable="'surname'">
	                {{user.surname}}
	            </td>
	            <td data-title="'<spring:message code="label.birthdate" />'" sortable="'birthdate'">
	                {{user.birthdate}}
	            </td>
	            <td data-title="'<spring:message code="label.gender" />'"  sortable="'gender'">
	                {{user.gender}}
	            </td>
	            <td data-title="'<spring:message code="label.insnumber" />'" sortable="'insnumber'">
	                {{user.insnumber}}
	            </td>
	        </tr>
	    </table>
	    <script type="text/ng-template" id="ng-table/headers/checkbox.html">
        	<input type="checkbox" ng-model="checkboxes.checked"  id="select_all" name="filter-checkbox" value="" />
    	</script>
		
	</div>
	<div class="col-md-1">
		<a href="<c:url value="/logout" />"><button type="button"
				class="btn btn-primary"><spring:message code="label.logout" /></button></a>
	</div>

</t:template>