<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<t:template>

	<div class="finish">
		<div class="jumbotron">
			<h1><spring:message code="label.accountcreate1" /></h1>
			<p>
				<small><spring:message code="label.accountcreate2" /></small>
			</p>
			<div class="panel panel-primary"  id="finish">
				<div class="panel-heading">
					<form class="form-horizontal">
						<div class="form-group">
							<label for="inputName" class="col-sm-2 control-label"><spring:message code="label.username" /></label>
							<div class="col-sm-10">
								<input type="text" value="${username}" class="form-control" id="inputName" readonly="true">
							</div>
						</div>
						<div class="form-group">
							<label for="inputName" class="col-sm-2 control-label"><spring:message code="label.password" /></label>
							<div class="col-sm-10">
								<input type="text" value="${password}" class="form-control" id="inputName" readonly="readonly">
							</div>
						</div>
					</form>
				</div>

			</div>
			<div class="finish-sing">
				<p>
					<a class="btn btn-primary btn-lg" role="button" href="/schoolreg/index"><spring:message code="label.gotosignin" /></a>
				</p>
			</div>
			
		</div>

	</div>

</t:template>