<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
	<head>
        <title>Medewerker menu</title>
        <link rel="stylesheet" type="text/css" href="../css/bieb.css" />
	</head>

	<body>
		<h4>Medewerker menu. Ingelogd: <s:property value="user.username"/>.</h4> 	
		
        <hr />   
          
        <ul>
            <li><a href="<s:url action='LendBookForm' namespace="/coworker"/>">Leen boek uit</a></li>
            <li><a href="<s:url action='TakeBackBookForm' namespace="/coworker"/>">Neem boek in</a></li>
            <li><a href="<s:url action='Logout' namespace="/member"/>">Log uit</a></li>	
        </ul>
           
	</body>
</html>
