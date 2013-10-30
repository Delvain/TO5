<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="/jsp/include/head.jsp" />
		<title>Plarktmaats - Mijn veilingen en biedingen</title>
	</head>
	<body>
		<jsp:include page="/jsp/include/top.jsp" />
		<div class="container">
			<h1>Mijn veilingen</h1>
			<s:iterator value = "mijnVeilingen">
				<a href="<s:url namespace="/member" action="PersoonPagina"></s:url>"><s:property value="veilingNaam" /></a>
			</s:iterator>			
		</div>
		<div class="container">
			<h1>Mijn biedingen</h1>
			<s:iterator value = "mijnBiedingen">
				<li><s:property /></li>
			</s:iterator>			
		</div>
		<jsp:include page="/jsp/include/footer.jsp" />
	</body>
</html>