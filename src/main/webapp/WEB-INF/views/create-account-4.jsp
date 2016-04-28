<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<t:template>

	<div class="profile-adress" ng-controller="StorageCtrl" 
		data-ng-init="load4()">
		<h3><spring:message code="label.createaccountpage" /> 4/4</h3>
		<form class="form-horizontal" role="form" id="profile-main" name="userForm" ng-submit="submitForm4(userForm.$valid)" novalidate>
			<div class="form-group">
				<label for="inputWishes" class="col-sm-2 control-label"><spring:message code="label.otherwishes" /></label>
				<div class="col-sm-10" ng-class="{ 'has-error' : userForm.dataWishes.$invalid && !userForm.dataWishes.$pristine }">
					<textarea ng-model="dataWishes" name="dataWishes" ng-change="save4()" ng-maxlength="300" 
						class="form-control" id="inputWishes" rows="10"></textarea>
						<p ng-show="userForm.dataWishes.$error.maxlength" class="help-block"><spring:message code="label.textlong" /></p>
				</div>
			</div>
			<div style="width: 400px; margin: 0 auto;">
			<ul class="pagination">
			  <li><a href="create-account-page-3">&larr; <spring:message code="label.prevstep" /></a></li>
		      <li><a href="create-account-page-1">1 <span class="sr-only">(current)</span></a></li>
		      <li><a href="create-account-page-2">2</a></li>
		      <li><a href="create-account-page-3">3</a></li>
		      <li class="active"><a href="#">4</a></li>
		      <li><button type="submit" class="btn-next"><spring:message code="label.tofinish" />	&rarr;</button></li>
		   </ul>
		   </div>
		</form>
		
	</div>
</t:template>