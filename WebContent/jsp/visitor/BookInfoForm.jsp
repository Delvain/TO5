<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>

   <head>
      <title>Boekenlijst</title>
      <link rel="stylesheet" type="text/css" href="../css/bieb.css" />
   </head>

   <body>
	    <h4>Boekenlijst.</h4>
	    
	    <hr />

	    <table>
		    <tr class="thcolor">
			    <th>id</th>
			    <th>titel</th>
			    <th>status</th>
		    </tr>
		    <s:iterator value="books">
			    <tr class="tdcolor">
				    <td><s:property value="id" />
					<td><s:property value="titel" />
		     		<td><s:property value="status" />
		        </tr>
 		    </s:iterator>
	    </table>
  		
	    <hr />
	    
	    <s:form action ="BookInfo">
	    	<s:textfield name="bookId" label="id" />
	    	<s:submit value="toon info" />
	    </s:form>
	    
	    <hr />
	    
        <a href="<s:url value="/"/>">Terug naar het startmenu</a>
	</body> 
</html> 