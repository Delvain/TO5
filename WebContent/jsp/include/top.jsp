<%@ taglib prefix="s" uri="/struts-tags" %>
<script type="text/javascript">
            function sendRequestCredits(geb){
            	var request = getRequestObject();
            	request.onreadystatechange = function() {
            		if((request.readyState == 4) && (request.status == 200)){
                   			var serverResponse = request.responseText;
                			document.getElementById("creditsAJAX").innerHTML=serverResponse;
                	}
            	};
            	request.open("GET", "/TO5/visitor/CreditsAJAX.action?gebruikersnaam="+geb, true);
            	request.send(null);
            	setTimeout(function() {sendRequestCredits(geb);},5000);
            }
		</script>
<div class="header">
	<div class="content">
		<a class="logo" href="<s:url action="Menu" namespace="/visitor" />">&nbsp;</a>
		<div class="right">
			<p>
				<s:if test="%{#session.user instanceof plarktmaatsDomein.Beheerder}">
					<a href="<s:url action="Menu" namespace="/beheerder" />">Beheerdersmenu</a> |
					<a href="<s:url action="Logout" namespace="/member" />">Log uit</a>
				</s:if>
				<s:elseif test="%{#session.user instanceof plarktmaatsDomein.Gebruiker}">
					<a href="<s:url action='KoopCreditsForm' namespace="/member"/>" class="plaats_ad_but">Koop Credits</a> |
					<a href="<s:url action='PasGegevensAanForm' namespace="/member"/>" class="plaats_ad_but">Pas gegevens aan</a> |
					<a href="<s:url action='PersoonPagina' namespace="/member"/>">Mijn Account</a> |
					<a href="<s:url action="Logout" namespace="/member" />">Log uit</a>
				</s:elseif>
				<s:else>
					<a href="<s:url action='LoginForm' namespace="/visitor"/>">Log in</a> |
					<a href="<s:url action='CreateAccountForm' namespace="/visitor"/>">Registreer</a>
				</s:else>
			</p>
		</div>
		<div style="clear: both"></div>
	</div>
</div>
<div class="top_bar">
	<div class="content">
		<a href="<s:url action='VoegVeilingToeForm' namespace="/member"/>" class="plaats_ad_but">&nbsp;</a>
		<s:if test="%{#session.user instanceof plarktmaatsDomein.Beheerder}">
			<p class="welkom">Welkom, <s:property value="%{#session.user.gebruikersnaam}" />.</p>
		</s:if>
		<s:elseif test="%{#session.user instanceof plarktmaatsDomein.Gebruiker}">
			<p class="welkom">Welkom, <s:property value="%{#session.user.gebruikersnaam}" />.
			<script>sendRequestCredits('<s:property value="%{#session.user.gebruikersnaam}" />');</script>
			<span id="creditsAJAX">Laden...</span></p>
		</s:elseif>
		<s:else>
			<p class="welkom">Welkom, <a href="<s:url action="LoginForm" namespace="/visitor" />">log in</a> of <a href="<s:url action="CreateAccountForm" namespace="/visitor" />">maak een account</a>.</p>
		</s:else>
		<div class="search">
			<form method="post" action="#zoeken">
				<input type="text" name="q" /> <input type="submit" value="Zoek" />
			</form>
		</div>
		<div style="clear: both"></div>
	</div>
</div>