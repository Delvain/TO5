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
			<h1>Voeg een veiling toe</h1>

			<s:form namespace="/member" action="VoegVeilingToe">
				<s:select name="categorie" list="categorieen" listKey = "naam" listValue = "naam" label="Categorie"></s:select>
				<s:textfield name="productnaam" label="Naam"/>
				<s:textarea name="productomschrijving" label="Omschrijving" />
				<s:textfield name="minbedrag" label="Minimumbod" />
				<s:textfield name="strDuur" label="Duur van de veiling (in uren)" />
				<s:textfield name="img" label="URL van foto" />
				<s:submit value="Maak veiling aan" />
			</s:form>
		</div>
		<jsp:include page="/jsp/include/footer.jsp" />
	</body>
</html>