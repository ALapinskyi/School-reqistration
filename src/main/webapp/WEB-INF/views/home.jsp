<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<t:template>

	<div class="singin" ng-controller="AlertCtrl">
		<h3><spring:message code="label.signin" /></h3>
		<c:if test="${not empty param.error}">
			<alert ng-repeat="alert in alerts" type="{{alert.type}}"
				close="closeAlert($index)">{{alert.msg}}</alert>
		</c:if>
		<form class="form-horizontal" role="form" id="login-form"
			method="POST" action="<c:url value="/j_spring_security_check" />">
			<div class="form-group">
				<label for="inputLogin3" class="col-sm-2 control-label"><spring:message code="label.login" /></label>
				<div class="col-sm-10">
					<input type="login" name="j_username" class="form-control"
						id="inputLogin3" >
				</div>
			</div>
			<div class="form-group">
				<label for="inputPassword3" class="col-sm-2 control-label"><spring:message code="label.password" /></label>
				<div class="col-sm-10">
					<input type="password" name="j_password" class="form-control"
						id="inputPassword3" >
				</div>
			</div>
			<div class="form-group">
				    <div class="col-sm-offset-2 col-sm-10">
				      <div class="checkbox">
				        <label>
				          <input type="checkbox" name="_spring_security_remember_me"> Remember me
				        </label>
				      </div>
				    </div>
				  </div> 
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-primary btn-lg btn-block"><spring:message code="label.signinB" /></button>
				</div>
			</div>

		</form>
		<h3><spring:message code="label.or" /></h3>
		<div class="btn-create">
			<a href="create-account-page-1"><button
					class="btn btn-default btn-lg" onlick="return false;"><spring:message code="label.createaccount" /></button></a>
		</div>

	</div>

</t:template>