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
				    <td><s:property value="id" /></td>
					<td><s:property value="titel" /></td>
		     		<td><s:property value="status" /></td>
		        </tr>
 		    </s:iterator>
	    </table>
  		
	    <hr /> 
	    
	    <a href=" <s:url value="/"/> ">Terug naar het startmenu</a>
	</body> 
</html>
 