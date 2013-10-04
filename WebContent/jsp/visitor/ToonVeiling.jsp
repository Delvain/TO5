<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>

   <head>
      <title>Veilinginformatie</title>
      <link rel="stylesheet" type="text/css" href="../css/bieb.css" />
   </head>

   <body>
	    <h4>Informatie over veiling met naam <s:property value="veilingNaam" />.</h4>
	    <h4>Log in om te kunnen bieden</h4>
	    
	    <hr />

	    <table>
		    <tr class="thcolor">
			    <th>attribuut</th>
			    <th>waarde</th>
		    </tr>
		    <tr>
		    	<td>id</td>
		    	<td><s:property value="veilingId" /></td>
		    </tr>
		    <tr>
		    	<td>naam</td>
		    	<td><s:property value="veiling.naam" /></td>
		    </tr>
		    <tr>
		    	<td>omschrijving</td>
		    	<td><s:property value="veiling.veilingOmschrijving" /></td>
		    </tr>
		    <tr>
		    	<td>foto</td>
		    	<td><s:property value="veiling.foto" /></td>
		    </tr>
		    <tr>
		    	<td>minbedrag</td>
		    	<td><s:property value="veiling.minbedrag" /></td>
		    </tr>
		    <tr>
		    	<td>eindtijd</td>
		    	<td><s:property value="veiling.eindTijd" /></td>
		    </tr>
		    <tr>
		    	<td>aanbieder</td>
		    	<td><s:property value="veiling.aanbieder.gebruikersnaam" /></td>
		    </tr>
		    <tr>
		    	<td>categorie</td>
		    	<td><s:property value="veiling.deCategorie.naam" /></td>
		    </tr>
	    </table>
	</body> 
</html> 