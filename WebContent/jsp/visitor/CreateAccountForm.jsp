<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>

	<head>
         <title>Plarktmaats</title>
         <link rel="stylesheet" type="text/css" href="../css/plarktmaats.css" />
	</head>

	<body>
	    <h4>Maak een wachtwoord aan om toegang te krijgen tot Plarktmaats</h4> 	
	    
	    <hr />
	    
		<s:form action="CreateAccount">
    	  <s:textfield name="email" label="E-mailadres"/>
    	  <s:textfield name="voornaam" label="Voornaam"/>
    	  <s:textfield name="achternaam" label="Achternaam"/>
    	  <s:password name="password" label="Wachtwoord"/>
    	  <s:password name="password2" label="Hehaal wachtwoord"/>
    	  <s:submit value="maak account aan"/>
		</s:form>
			
	    <hr />
	    
	    <a href=" <s:url value="/"/> ">Terug naar het startmenu</a>
	</body> 
</html>