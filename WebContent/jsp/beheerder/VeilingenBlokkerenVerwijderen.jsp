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
			<s:iterator value="items" status="status">
					<p class="meer">- <a href="<s:url namespace="/visitor" action="ToonVeiling"><s:param name="id" value="%{VeilingId}" /></s:url>"><s:property value="veilingNaam" /></a>
					<a href="<s:url namespace="/beheerder" action="BlokkeerVeiling"><s:param name="id" value="%{VeilingId}" /></s:url>">Blokkeren</a>
					<a href="<s:url namespace="/beheerder" action="VerwijderVeiling"><s:param name="id" value="%{VeilingId}" /></s:url>">Verwijderen</a>
					</p>
			</s:iterator>			
			<div style="clear: both"></div>
		</div>
		<jsp:include page="/jsp/include/footer.jsp" />
	</body>
</html>