<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="/jsp/include/head.jsp" />
		<sx:head />
	</head>
	<body>
		<jsp:include page="/jsp/include/top.jsp" />
		<div class="container">
			<h1>Beheerdersmenu</h1>

			<a href="<s:url action='BlokkeerVerwijderForm' namespace="/beheerder"/>" class="plaats_ad_but">Blokkeer/verwijder veilingen</a><br/>
			<a href="<s:url action='SelecteerGebruikerForm' namespace="/beheerder"/>">Bewerk Gebruiker</a><br/>
			
			<p>Statistieken</p>
			<a href="<s:url action='StatistiekHoogsteBieding' namespace="/beheerder"/>">Hoogste biedingen</a><br/>
			<a href="<s:url action='StatistiekInkomstenOverzicht' namespace="/beheerder"/>">Inkomstenoverzicht</a><br/>
			
			
			<p>Veilingen zijn verwerkt tot: <s:property value="datum"/></p>
			<a href="<s:url action='VerwerkVeilingen' namespace="/beheerder"/>" class="plaats_ad_but">Verwerk veilingen</a><br/>
		</div>
		<jsp:include page="/jsp/include/footer.jsp" />
	</body>
</html>