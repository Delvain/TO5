<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
	<head>
		<title>Maak een veiling</title>
		<link rel="stylesheet" type="text/css" href="../css/plarktmaats.css" />
	</head>
	<body>
		<s:textfield name="productnaam" value="productnaam"/>
		<s:textfield name="productomschrijving" value="productomschrijving" />
		<s:file type="img" />
		<s:textfield name="minbedrag" value="minbedrag" />
		<s:date name="eindDatum" nice=true />
	</body>
</html>