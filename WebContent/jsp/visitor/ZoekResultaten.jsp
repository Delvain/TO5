<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="/jsp/include/head.jsp" />
		<title>Plarktmaats - Zoekresultaten</title>
	</head>
	<body>
		<jsp:include page="/jsp/include/top.jsp" />
		<div class="container">
			<h1>Zoekresultaten</h1><br>
			<s:if test="%{zoekResultaten.isEmpty()}">
				<p>Uw zoekterm heeft geen resultaten opgeleverd.</p>
			</s:if>
			<s:iterator value="zoekResultaten">
				<p>- <a href="<s:url namespace="/visitor" action="ToonVeiling"><s:param name="id" value="%{VeilingId}" /></s:url>"><s:property value="veilingNaam" /></a></p>
			</s:iterator>			
		</div>
		<jsp:include page="/jsp/include/footer.jsp" />
	</body>
</html>