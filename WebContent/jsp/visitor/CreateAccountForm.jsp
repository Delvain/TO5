<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>

	<head>
         <title>Een account aanmaken</title>
         <link rel="stylesheet" type="text/css" href="../css/bieb.css" />
	</head>

	<body>
	    <h4>Een account aanmaken.</h4> 	
	    
	    <hr />
	    
		<s:form action="CreateAccount">
    	  <s:textfield name="username" label="Naam"/>
    	  <s:password name="password" label="Wachtwoord"/>
    	  <s:submit value="maak account aan"/>
		</s:form>
			
	    <hr />
	    
	    <a href=" <s:url value="/"/> ">Terug naar het startmenu</a>
	</body> 
</html>