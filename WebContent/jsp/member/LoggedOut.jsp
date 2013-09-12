<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>

	<head>
        <title>Uitgelogd</title>
        <link rel="stylesheet" type="text/css" href="../css/bieb.css" />
	</head>

	<body>
	    <h4>Uitgelogd.</h4> 	
	    
	    <hr />
	    
		Tot ziens <s:property value="user.username"/>.<br>
		Je bent uitgelogd. 
		
	    <hr />
	    
	    <a href=" <s:url value="/"/> ">Het startmenu</a>
	</body> 
</html>