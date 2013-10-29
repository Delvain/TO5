<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
		<jsp:include page="/jsp/include/head.jsp" />
		<title>Inloggen</title>
		<link rel="stylesheet" type="text/css" href="../css/style.css" />
	</head>	
	<body>
		<jsp:include page="/jsp/include/top.jsp" />
		<div class="container">
			<h4>Inloggen</h4>
			<hr />
				<s:form action="Login">
					<s:textfield name="username" label="Naam" />
					<s:password name="password" label="Wachtwoord" />
					<s:submit value="Log in" />
				</s:form>
			<hr />
			<a href="<s:url value="/"/>">Terug naar het startmenu</a>
		</div>
		<jsp:include page="/jsp/include/footer.jsp" />
	</body>
</html>