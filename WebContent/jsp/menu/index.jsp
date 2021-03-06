<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="/jsp/include/head.jsp" />
		<script type="text/javascript">
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
            	setTimeout(function() {sendRequest(id, index);},1000);
            }
		</script>
	</head>
	<body>
		<jsp:include page="/jsp/include/top.jsp" />
		<div class="container">
			<s:iterator value="items" status="status">
				<s:if test="%{#status.index < 3}">
					<div class="item">
						<a class="link" href="<s:url namespace="/visitor" action="ToonVeiling?id=%{VeilingId}" />"><s:property value="veilingNaam" /></a>
						<div class="imgBox">
						<a href="<s:url namespace="/visitor" action="ToonVeiling?id=%{VeilingId}" />">
							<object data="<s:property value="foto" />">
    							<img src="/TO5/style/images/Vraagteken.png" />
  							</object>
  						</a>
						</div>
						<input type="button" class="bieden" value="Bieden" onclick="javascript: window.location='<s:url namespace="/visitor" action="ToonVeiling"><s:param name="id" value="%{VeilingId}" /></s:url>'" />
						<s:div id="ajaxData%{#status.index}" />
						<script>sendRequest('<s:property value="VeilingId" />', '<s:property value="#status.index" />');</script>
					</div>
				</s:if>
				<s:elseif test="%{#status.index == 3}">
					<div style="float: left; margin-right: 30px">
					<h2>Meer veilingen:</h2>
					<p class="meer">- <a href="<s:url namespace="/visitor" action="ToonVeiling"><s:param name="id" value="%{VeilingId}" /></s:url>"><s:property value="veilingNaam" /></a></p>
				</s:elseif>
				<s:else>
					<p class="meer">- <a href="<s:url namespace="/visitor" action="ToonVeiling"><s:param name="id" value="%{VeilingId}" /></s:url>"><s:property value="veilingNaam" /></a></p>
				</s:else>
				<s:if test="%{#status.isLast()}">
					<s:if test="%{#status.index > 2}" >
						</div>
					</s:if>
				</s:if>
			</s:iterator>
			<div style="float: left">
				<h2>Categorieën:</h2>
				<s:action name="CategorieBox" executeResult="true" namespace="/visitor"></s:action>
			</div>	
			<div style="clear: both"></div>
		</div>
		<jsp:include page="/jsp/include/footer.jsp" />
	</body>
</html>