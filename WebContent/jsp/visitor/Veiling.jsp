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
            
            function sendRequest(id, index){
            	var request = getRequestObject();
            	request.onreadystatechange = function() {
            		if((request.readyState == 4) && (request.status == 200)){
                   			var serverResponse = request.responseText;
                			document.getElementById("ajaxData"+index).innerHTML=serverResponse;
                	}
            	};
            	request.open("GET", "/TO5/visitor/AlleVeilingenAJAX.action?id="+id, true);
            	request.send(null);
            	setTimeout(function() {sendRequest(id, index);},2000);
            }
		</script>
	</head>
	<body>
		<jsp:include page="/jsp/include/top.jsp" />
		<div class="container">
			<h1><s:property value="veiling.VeilingNaam" /></h1>
			<img src="http://upload.wikimedia.org/wikipedia/commons/5/56/Vraagteken.png" />
			<p><s:property value="veiling.VeilingOmschrijving" /></p>
			<div style="clear: both"></div>
			<s:form action="">
				<s:textfield name="credits" />
				<s:submit value="Bieden" />
			</s:form>
		</div>
		<jsp:include page="/jsp/include/footer.jsp" />
	</body>
</html>