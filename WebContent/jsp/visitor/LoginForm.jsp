<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>

	<head>
        <title>Inloggen</title>
        <link rel="stylesheet" type="text/css" href="../css/bieb.css" />
	</head>

	<body>
	    <h4>Inloggen.</h4> 	
	    
	    <hr />
	    
		<s:form action="Login">
    	  <s:textfield name="username" label="Naam"/>
    	  <s:password name="password" label="Wachtwoord"/>
    	  <s:submit value="log in"/>
		</s:form>
			
	    <hr />
	    
	    <a href="<s:url value="/"/>">Terug naar het startmenu</a>
	</body> 
</html>