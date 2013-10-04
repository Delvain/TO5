<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="/jsp/include/head.jsp" />
		<script type="text/javascript">
						
			var request;
            function getRequestObject(){
            	setInterval(function() {sendRequest();},5000);
           		if (window.ActiveXObject){
            		return (new ActiveXObject("Microsoft.XMLHTTP"));
            	} else if (window.XMLHttpRequest){
            		return(new XMLHttpRequest());
            	} else {
            		return (null);
            	}
            }
            function sendRequest(){
            	request = getRequestObject();
            	request.onreadystatechange = handleResponse;
            	request.open("GET", "/TO5/visitor/AlleVeilingenAJAX.action?id=1", true);
            	request.send(null);
            }
            function handleResponse(){
            	if((request.readyState == 4) && (request.status == 200)){
           			var serverResponse = request.responseText;
            		document.getElementById("ajaxData").innerHTML=serverResponse;
            }
            }
		</script>
	</head>
	<body onload="getRequestObject()">
		<jsp:include page="/jsp/include/top.jsp" />
		<div class="container">
			<s:iterator value="items" status="status">
				<div class="item">
					<a href="#"><s:property value="veilingNaam" /></a>
					<div class="imgBox">
						<img src="http://fotos.marktplaats.com/kopen/e/85/LHqziqxnW3bz1amvmtSiNg==.jpg" />
					</div>
					<input type="button" class="bieden" value="Bieden" />
					<s:div id="ajaxData%{#status.index}" />
				</div>
			</s:iterator>
			<div class="item">
				<a href="#">Fiets</a>
				<div class="imgBox">
					<img src="http://carpediem2.punt.nl/_files/2012-09-22/img-1308.JPG" />
				</div>
				<input type="button" class="bieden" value="Bieden" />
				<div id="ajaxData">
					Laden...
				</div>
			</div>
			
			<div style="clear: both"></div>
		</div>
		<jsp:include page="/jsp/include/footer.jsp" />
	</body>
</html>