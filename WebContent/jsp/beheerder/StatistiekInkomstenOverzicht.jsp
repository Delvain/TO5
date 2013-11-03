<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="/jsp/include/head.jsp" />
	</head>
	<body>
		<jsp:include page="/jsp/include/top.jsp" />
		<div class="container">
			<h1>Statistiek: Inkomstenoverzicht</h1>
			
			<p>Voer hier een maand in. Als u geen geldige maand invoert wordt de laatste maand getoond!</p>
			
			<s:form action="StatistiekInkomstenOverzicht">
				<s:textfield name="maand" label="Maand (mm-yyyy)"/>
				<s:submit value="Inkomsten van deze maand" />
			</s:form>
			<table>
				<thead>
					<tr>
						<th>Veilingnaam</th>
						<th>Bedrag</th>
						<th>Einddatum</th>
					</tr>
				</thead>
				<s:iterator value="veilingen" status="status" var="name">
					<tbody>
						<tr>
							<td><a href="<s:url namespace="/visitor" action="ToonVeiling"><s:param name="id" value="%{#name[0]}" /></s:url>"><s:property value="%{#name[1]}" /></a></td>
							<td><s:property value="%{#name[2]}"/></td>
							<td><s:property value="%{#name[3]}"/></td>
						</tr>
					</tbody>
				</s:iterator>
			</table>
		</div>
		<jsp:include page="/jsp/include/footer.jsp" />
	</body>
</html>
