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
			<h1>Voeg een veiling toe</h1>

			<s:form action="VoegVeilingToe">
				<s:textfield name="categorie" label="categorie"/><br/>
				<s:textfield name="productnaam" label="productnaam"/><br/>
				<s:textfield name="productomschrijving" label="productomschrijving" /><br/>

				<s:textfield name="minbedrag" label="minbedrag" /><br/>
				<s:date name="eindDatum" nice="true" /><br/>
				<s:submit value="Maak veiling aan" />
			</s:form>
		</div>
		<jsp:include page="/jsp/include/footer.jsp" />
	</body>
</html>