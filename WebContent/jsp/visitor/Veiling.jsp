<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
	<head>
		<link rel="stylesheet" type="text/css" href="/TO5/style/veiling.css" />
		<jsp:include page="/jsp/include/head.jsp" />
		<script type="text/javascript">
            function getRequestObject(){
           		if (window.ActiveXObject){
            		return (new ActiveXObject("Microsoft.XMLHTTP"));
            	} else if (window.XMLHttpRequest){
            		return(new XMLHttpRequest());
            	}
            }
            
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
            	setTimeout(function() {sendRequest(id);},2000);
            }
		</script>
	</head>
	<body>
		<jsp:include page="/jsp/include/top.jsp" />
		<div class="container">
			<h1><s:property value="veiling.VeilingNaam" /></h1>
			<div class="left">
				<object data="<s:property value="veiling.foto" />">
    				<img src="http://upload.wikimedia.org/wikipedia/commons/5/56/Vraagteken.png" />
  				</object>
				<s:form action="">
					<s:textfield name="credits" />
					<s:submit value="Bieden" />
				</s:form>
			</div>
			<div id="ajaxData">Laden...</div>
			<script>sendRequest('<s:property value="veiling.VeilingId" />');</script>
			<div class="right">
				<p><b>Categorie:</b> <s:property value="veiling.deCategorie.naam" /></p>
				<p><b>Aangeboden door:</b> <s:property value="veiling.aanbieder.gebruikersnaam" /></p>
				<p><b>Omschrijving:</b></p>
				<p><s:property value="veiling.VeilingOmschrijving" /></p>
			</div>
			<div style="clear: both"></div>
		</div>
		<jsp:include page="/jsp/include/footer.jsp" />
	</body>
</html>