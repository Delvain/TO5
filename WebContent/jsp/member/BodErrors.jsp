<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="/jsp/include/head.jsp" />
	</head>
	<body>
		<jsp:include page="/jsp/include/top.jsp" />
		<div class="container">
			<h1>Er is iets mis gegaan...</h1>

			<s:fielderror fieldName="credits" />
			
			<a href="javascript: window.history.back()">Ga terug naar de veiling</a>
			
		</div>
		<jsp:include page="/jsp/include/footer.jsp" />
	</body>
</html>