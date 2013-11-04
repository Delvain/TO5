<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
	<head>
		<link rel="stylesheet" type="text/css" href="/TO5/style/veiling.css" />
		<jsp:include page="/jsp/include/head.jsp" />
		<script type="text/javascript">      
            function sendRequest(id){
            	var request = getRequestObject();
            	request.onreadystatechange = function() {
            		if((request.readyState == 4) && (request.status == 200)){
                   			var serverResponse = request.responseText;
                			document.getElementById("ajaxData").innerHTML=serverResponse;
                	}
            	};
            	request.open("GET", "/TO5/visitor/AlleVeilingenAJAX.action?id="+id, true);
            	request.send(null);
            	setTimeout(function() {sendRequest(id);},1000);
            }
		</script>
	</head>
	<body>
		<jsp:include page="/jsp/include/top.jsp" />
		<div class="container">
			<h1><s:property value="veiling.VeilingNaam" /></h1>
			<div class="left">
				<object data="<s:property value="veiling.foto" />">
    				<img src="/TO5/style/images/Vraagteken.png" />
  				</object>
				<s:form action="VoegBodToe" namespace="/member">
					<s:hidden name="id" value="%{veiling.veilingId}" />
					<s:textfield name="credits" />
					<s:submit value="Bieden" />
				</s:form>
			</div>
			<div id="ajaxData">Laden...</div>
			<script>sendRequest('<s:property value="veiling.VeilingId" />');</script>
			<div class="right">
				<p><b>Categorie:</b> <a href="<s:url action="ToonCategorie?cat=%{veiling.deCategorie.naam}" namespace="/visitor" />"><s:property value="veiling.deCategorie.naam" /></a></p>
				<p><b>Aangeboden door:</b> <s:property value="veiling.aanbieder.gebruikersnaam" /></p>
				<p><b>Omschrijving:</b></p>
				<p><s:property value="veiling.VeilingOmschrijving" /></p>
			</div>
			<div style="clear: both"></div>
		</div>
		<jsp:include page="/jsp/include/footer.jsp" />
	</body>
</html>