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
			<h1>Statistiek: Hoogste Biedingen</h1>
			
			<div>Hoogste bieding de laatste 24 uur: <s:property value="dag" /></div>
			<div>Hoogste bieding de laatste 7 dagen: <s:property value="week" /></div>
			<div>Hoogste bieding de laatste maand: <s:property value="maand" /></div>
			<div>Hoogste bieding het laatste jaar: <s:property value="jaar" /></div>
		</div>
		<jsp:include page="/jsp/include/footer.jsp" />
	</body>
</html>