<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
	<head>
        <title>Manager menu</title>
        <link rel="stylesheet" type="text/css" href="../css/bieb.css" />
	</head>

	<body>
		<h4>Manager menu. Ingelogd: <s:property value="user.username"/>.</h4> 	
		
        <hr />
          
        <ul>
            <li><a href="<s:url action='UserList' namespace="/manager"/>">Alle users</a></li>	
            <li><a href="<s:url action='AddCoworkerForm' namespace="/manager"/>">Voeg medewerker toe</a></li>	
            <li><a href="<s:url action='DeleteUserForm' namespace="/manager"/>">Verwijder user</a></li>	
            <li><a href="<s:url action='BookList' namespace="/manager"/>">Alle boeken</a></li>	
            <li><a href="<s:url action='BookInfoForm' namespace="/manager"/>">Boekinformatie</a></li>
            <li><a href="<s:url action='AddBookForm' namespace="/manager"/>">Voeg boek toe</a></li>	
            <li><a href="<s:url action='DeleteBookForm' namespace="/manager"/>">Verwijder boek</a></li>	
            <li><a href="<s:url action='Logout' namespace="/member"/>">Log uit</a></li>	
        </ul>
           
	</body>
</html>
