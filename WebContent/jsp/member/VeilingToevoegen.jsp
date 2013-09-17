<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
	<head>
		<title>Maak een veiling</title>
		<link rel="stylesheet" type="text/css" href="../css/plarktmaats.css" />
	</head>
	<body>
		<s:textfield name="productnaam" value="productnaam"/><br/>
		<s:textfield name="productomschrijving" value="productomschrijving" /><br/>
		<s:file type="img" /><br/>
		<s:textfield name="minbedrag" value="minbedrag" /><br/>
		<s:date name="eindDatum" nice="true" /><br/>
	</body>
</html>