<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
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
				<sx:datetimepicker name="eindDatum" label="Eindtijdstip (yyyy-MM-dd)" displayFormat="yyyy-MM-dd" value="todayDate" /><br/>
				<s:submit value="Maak veiling aan" />
			</s:form>
		</div>
		<jsp:include page="/jsp/include/footer.jsp" />
	</body>
</html>