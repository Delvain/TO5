<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="header">
	<div class="content">
		<a class="logo" href="<s:url action="Menu" namespace="/visitor" />">&nbsp;</a>
		<div class="right">
			<p>
				<a href="<s:url action='BlokkeerVerwijderForm' namespace="/beheerder"/>" class="plaats_ad_but">Blokkeren/verwijderen</a> |
				<a href="<s:url action='PasGegevensAanForm' namespace="/member"/>" class="plaats_ad_but">Pas gegevens aan</a> |
				<a href="<s:url action='SelecteerGebruikerForm' namespace="/beheerder"/>">Selecteer Gebruiker</a> |
				<a href="<s:url action='LoginForm' namespace="/visitor"/>">Log in</a> |
				<a href="<s:url action='CreateAccountForm' namespace="/visitor"/>">Registreer</a>
			</p>
		</div>
		<div style="clear: both"></div>
	</div>
</div>
<div class="top_bar">
	<div class="content">
		<a href="<s:url action='VoegVeilingToeForm' namespace="/member"/>" class="plaats_ad_but">&nbsp;</a>
		<p class="welkom">Welkom, Piet. U heeft 290 Credits.</p>
		<div class="search">
			<form method="post" action="#zoeken">
				<input type="text" name="q" /> <input type="submit" value="Zoek" />
			</form>
		</div>
		<div style="clear: both"></div>
	</div>
</div>