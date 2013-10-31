<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="/jsp/include/head.jsp" />
		<sx:head />
	</head>
	<body>
		<jsp:include page="/jsp/include/top.jsp" />
		<div class="container">
			<h1>Selecteer een persoon</h1>

			<s:form action="SelecteerGebruiker">
				<s:select name="gebruikersnaam" list="gebruikers" listKey = "gebruikersnaam" listValue = "gebruikersnaam" label="Gebruiker"></s:select>
				<s:submit value="Bewerk gebruikersgegevens" />
			</s:form>
		</div>
		<jsp:include page="/jsp/include/footer.jsp" />
	</body>
</html>