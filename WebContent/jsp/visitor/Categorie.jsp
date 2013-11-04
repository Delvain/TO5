<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
	<head>
		<link rel="stylesheet" type="text/css" href="/TO5/style/veiling.css" />
		<jsp:include page="/jsp/include/head.jsp" />
	</head>
	<body>
		<jsp:include page="/jsp/include/top.jsp" />
		<div class="container">
			<h1><s:property value="cat" /></h1>
			<br />
			<s:if test="%{veilingen.isEmpty()}">
				<p>Er zijn geen veilingen in deze categorie.</p>
			</s:if>
			<s:iterator value="veilingen" status="status">
				<p>- <a href="<s:url action="ToonVeiling?id=%{veilingId}" />"><s:property value="veilingNaam" /></a></p>
			</s:iterator>
			
		</div>
		<jsp:include page="/jsp/include/footer.jsp" />
	</body>
</html>