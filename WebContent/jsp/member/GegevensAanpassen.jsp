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
			<h1>Template</h1>
			<p>Dit is de template. Maak er wat leuks van ;)</p>
			<p>Laat het aub even weten aan Maarten als er problemen of suggesties zijn voor de lay out. Ga niet zelf
			dingen veranderen en voeg zelf geen CSS toe aan je JSP's.</p>
			<s:form>
				<s:textfield label="Textfield" />
				<s:textfield label="Textfield 2" />
				<s:submit value="Knop" />
			</s:form>
		</div>
		<jsp:include page="/jsp/include/footer.jsp" />
	</body>
</html>