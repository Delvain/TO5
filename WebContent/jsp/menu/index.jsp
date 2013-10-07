<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
	<head>
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
               			if(request.responseText != "Error") {
                   			var serverResponse = request.responseText;
                			document.getElementById("ajaxData"+index).innerHTML="<p class=\"prijs\">"+serverResponse+"</p>";
               			}
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
			<s:iterator value="items" status="status">
				<div class="item">
					<a href="#"><s:property value="veilingNaam" /></a>
					<div class="imgBox">
						<img src="http://fotos.marktplaats.com/kopen/e/85/LHqziqxnW3bz1amvmtSiNg==.jpg" />
					</div>
					<input type="button" class="bieden" value="Bieden" />
					<s:div id="ajaxData%{#status.index}" />
					<script>sendRequest('<s:property value="VeilingId" />', '<s:property value="#status.index" />');</script>
				</div>
			</s:iterator>			
			<div style="clear: both"></div>
		</div>
		<jsp:include page="/jsp/include/footer.jsp" />
	</body>
</html>