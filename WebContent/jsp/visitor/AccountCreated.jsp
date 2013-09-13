<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>

	<head>
        <title>Account aangemaakt</title>
        <link rel="stylesheet" type="text/css" href="../css/bieb.css" />
    </head>

	<body>
	    <h4>Account aangemaakt.</h4>
	     	
	    <hr />
	    
		Welkom <s:property value="username"/>!<br>
		Je account is aangemaakt.
		
	    <hr />
	    
	    <a href=" <s:url value="/"/> ">Terug naar het startmenu</a><br/>
	    <a href="<s:url action='LoginForm' namespace="/visitor"/>">Log in</a>
	</body> 
</html>