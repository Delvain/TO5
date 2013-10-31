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
			<h1>Verander hier de gegevens van <s:property value="gebruikersnaam" /></h1>
			<p>Lege velden worden niet veranderd</p>
			<s:form action="BewerkGegevens">
				<s:textfield name="voornaam" label="Voornaam" />
				<s:textfield name="achternaam" label="Achternaam" />
				<s:textfield name="email" label="E-mailadres" />
				<s:password name="wachtwoord" label="Wachtwoord" />
				<s:password name="wachtwoord2" label="Wachtwoord (controle)" />
				<s:textfield name="bankRekening" label="Bankrekeningnummer" />
				<s:textfield name="strGeboorteDatum" label="Geboortedatum (DD-MM-YYYY)" />
				<s:checkbox name="geblokkeerd" label="Geblokkeerd"/>
				<s:submit value="Verander gegevens" />
			</s:form>
		</div>
		<jsp:include page="/jsp/include/footer.jsp" />
	</body>
</html>