<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<jsp:include page="/jsp/include/head.jsp" />
<title>Plarktmaats - Registreren</title>
<link rel="stylesheet" type="text/css" href="../css/plarktmaats.css" />
</head>
<body>
	<jsp:include page="/jsp/include/top.jsp" />
	<div class="container">
		<h4>Registreer uw account</h4>
		<hr />
		<s:form action="CreateAccount">
			<s:textfield name="gebruikersnaam" label="Gebruikersnaam" />
			<s:textfield name="voornaam" label="Voornaam" />
			<s:textfield name="achternaam" label="Achternaam" />
			<s:textfield name="email" label="E-mailadres" />
			<s:password name="wachtwoord" label="Wachtwoord" />
			<s:password name="wachtwoord2" label="Hehaal wachtwoord" />
			<s:textfield name="bankRekening" label="Bankrekeningnummer" />
			<s:textfield name="strGeboorteDatum"
				label="Geboortedatum (DD-MM-YYYY)" />
			<s:submit value="Registreer" />
		</s:form>
		<s:actionerror />
		<hr />
		<a href=" <s:url value="/"/> ">Terug naar het startmenu</a>
	</div>
	<jsp:include page="/jsp/include/footer.jsp" />
</body>
</html>