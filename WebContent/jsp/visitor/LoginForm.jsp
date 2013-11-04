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
			<h1>Inloggen</h1>
			<s:form action="Login">
				<s:textfield name="username" label="Gebruikersnaam" />
				<s:password name="password" label="Wachtwoord" />
				<s:submit value="Log in" />
			</s:form>
		</div>
		<jsp:include page="/jsp/include/footer.jsp" />
	</body>
</html>