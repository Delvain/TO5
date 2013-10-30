<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
		<jsp:include page="/jsp/include/head.jsp" />
		<title>Plarktmaats - Mijn veilingen en biedingen</title>
	</head>
	<body>
		<jsp:include page="/jsp/include/top.jsp" />
		<div class="container">
			<h1>Mijn veilingen</h1>
			<s:iterator value = "data">
				<li><s:property /></li>
			</s:iterator>			
		</div>
		<jsp:include page="/jsp/include/footer.jsp" />
	</body>
</html>