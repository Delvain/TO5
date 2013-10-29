<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
	<head>
		<jsp:include page="/jsp/include/head.jsp" />
        <title>Account aangemaakt</title>
        <link rel="stylesheet" type="text/css" href="../css/plarktmaats.css" />
    </head>
	<body>
		<jsp:include page="/jsp/include/top.jsp" />
	    <h4>Account aangemaakt.</h4>
			Welkom <s:property value="username"/>!<br>
			Uw account is aangemaakt.
	    <hr />	    
	    <a href=" <s:url value="/"/> ">Terug naar het startmenu</a><br/>
	    <a href="<s:url action='LoginForm' namespace="/visitor"/>">Log in</a>
	    <jsp:include page="/jsp/include/footer.jsp" />
	</body>
</html>