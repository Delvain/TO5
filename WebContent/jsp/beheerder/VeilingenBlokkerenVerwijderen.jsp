<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="/jsp/include/head.jsp" />
	</head>
	<body>
		<jsp:include page="/jsp/include/top.jsp" />
		<div class="container">
			<table>
				<thead>
					<tr>
						<th>Veilingnaam</th>
						<th>Blokkeren</th>
						<th>Verwijderen</th>
					</tr>
				</thead>
				<s:iterator value="items" status="status">
					<tbody>
						<tr>
							<td><a href="<s:url namespace="/visitor" action="ToonVeiling"><s:param name="id" value="%{VeilingId}" /></s:url>"><s:property value="veilingNaam" /></a></td>
							<s:if test="geblokkeerd == true">
								<td>Al geblokkeerd</td>
							</s:if>
							<s:else>
								<td><a href="<s:url namespace="/beheerder" action="BlokkeerVeiling"><s:param name="id" value="%{VeilingId}" /></s:url>">Blokkeren <s:property value="%{items.geblokkeerd}" /></a></td>
							</s:else>
							<td><a href="<s:url namespace="/beheerder" action="VerwijderVeiling"><s:param name="id" value="%{VeilingId}" /></s:url>">Verwijderen</a></td>
						</tr>
					</tbody>
				</s:iterator>
			</table>
			<div style="clear: both"></div>
		</div>
		<jsp:include page="/jsp/include/footer.jsp" />
	</body>
</html>