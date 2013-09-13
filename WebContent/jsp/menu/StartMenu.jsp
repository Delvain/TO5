<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
	<head>
        <title>MijnBieb: Menu</title>
        <link rel="stylesheet" type="text/css" href="css/plarktmaats.css" />
	</head>

	<body>
		<h4>Mijn Bieb.</h4> 
			
        <hr />
        
        <ul>
            <li><a href="<s:url action='BookList' namespace="/visitor"/>">Alle boeken</a></li>	
            <li><a href="<s:url action='BookInfoForm' namespace="/visitor"/>">Boekinformatie</a></li>	
            <li><a href="<s:url action='CreateAccountForm' namespace="/visitor"/>">Maak een account aan</a></li>
            <li><a href="<s:url action='LoginForm' namespace="/visitor"/>">Log in</a></li>
        </ul>
        
	</body>
</html>
