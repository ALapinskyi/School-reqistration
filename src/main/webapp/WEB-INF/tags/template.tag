<%@ tag description="Template Tag" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!doctype>
<html ng-app="ui.bootstrap.schoolreg">
<head>
<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">

<link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/ng-table.css" />" rel="stylesheet">

<script src="<c:url value="/resources/js/angular.min.js"/>"></script>
<script src="<c:url value="/resources/js/angular-route.js"/>"></script>
<script src="<c:url value="/resources/js/angular-resource.js"/>"></script>
<script src="<c:url value="/resources/js/ui-bootstrap-tpls-0.12.0.min.js?2" />"></script>
<script src="<c:url value="/resources/js/ngStorage.js" />"></script>
<script src="<c:url value="/resources/js/ng-table.js" />"></script>

<script src="<c:url value="/resources/js/storage.js?30"/>"></script>
<script src="<c:url value="/resources/js/table.js?5"/>"></script>
<script src="<c:url value="/resources/js/profile.js?16"/>"></script>
<script src="<c:url value="/resources/js/main.js?55"/>"></script>
<meta http-equiv="Content-Type"
content="text/html; charset=UTF-8">
<title>School Registration for New Students</title>
</head>
<body>
<style>
	hr {
    background-color: #428bca;
    height: 1px;
   }
</style>
	<div class="container">
		<div class="row" id="header">
			<div class="col-md-10" id="header-text">
				<a href="/schoolreg/index" ><h2>School Registration for Students</h2></a>
			</div>
			<div class="col-md-2" id="l10n">
				<a href="?locale=en"><button class="btn btn-link">EN</button></a>
				<a href="?locale=ru"><button class="btn btn-link">RU</button></a>
			</div>
		</div>
		<hr>
		<div class="row" id="content">
			
			<jsp:doBody/>
		
		</div>
		<hr>
		<div class="row" id="footer">
			<div class="col-md-12">
				<p>Copyright &copy; 2015 All Rights Reserved</p>
			</div>
		</div>
	</div>
</body>
</html>
		