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
			<s:iterator value="mijnVeilingen">
				<s:property value="veilingNaam" /><br />
			</s:iterator>			
		</div>
		<div class="container">
			<h1>Mijn biedingen</h1>
			<s:iterator value="mijnBiedingenData">
				<s:property/><br />
			</s:iterator>
		</div>
		<jsp:include page="/jsp/include/footer.jsp" />
	</body>
</html>