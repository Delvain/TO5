<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>

   <head>
      <title>Boekinformatie</title>
      <link rel="stylesheet" type="text/css" href="../css/bieb.css" />
   </head>

   <body>
	    <h4>Informatie over boek met id <s:property value="bookId" />.</h4>
	    
	    <hr />

	    <table>
		    <tr class="thcolor">
			    <th>attribuut</th>
			    <th>waarde</th>
		    </tr>
		    <tr>
		    	<td>id</td>
		    	<td><s:property value="bookId" /></td>
		    </tr>
		    <tr>
		    	<td>titel</td>
		    	<td><s:property value="book.titel" /></td>
		    </tr>
		    <tr>
		    	<td>auteur</td>
		    	<td><s:property value="book.auteur" /></td>
		    </tr>
		    <tr>
		    	<td>isbn</td>
		    	<td><s:property value="book.isbn" /></td>
		    </tr>
		    <tr>
		    	<td>pages</td>
		    	<td><s:property value="book.pages" /></td>
		    </tr>
		    <tr>
		    	<td>status</td>
		    	<td><s:property value="book.status" /></td>
		    </tr>
	    </table>
  		
	    <hr />
	    
        <a href="<s:url value="/"/>">Terug naar het startmenu</a>
	</body> 
</html> 