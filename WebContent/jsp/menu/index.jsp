<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
	<head>
		<title>PlarktMaats</title>
		<link rel="stylesheet" type="text/css" href="style/style.css" />
		<link rel="stylesheet" type="text/css" href="style/container.css" />
	</head>
	<body>
		<div class="header">
			<div class="content">
				<div class="logo"></div>
				<div class="right">
					<p><a href="#">Inloggen</a> | <a href="#">Registreren</a></p>
				</div>
				<div style="clear: both"></div>
			</div>
		</div>
		<div class="top_bar">
			<div class="content">
				<a href="#" class="plaats_ad_but">&nbsp;</a>
				<p class="welkom">Welkom, Piet. U heeft 290 Credits.</p>
				<div class="search">
					<form method="post" action="#zoeken">
						<input type="text" name="q" />
						<input type="submit" value="Zoek" />
					</form>
				</div>
				<div style="clear: both"></div>
			</div>
		</div>
		<div class="container">
			<s:iterator value="items">
				<div class="item">
					<a href="#"><s:property value="titel" /></a>
					<div class="imgBox">
						<img src="http://fotos.marktplaats.com/kopen/e/85/LHqziqxnW3bz1amvmtSiNg==.jpg" />
					</div>
					<input type="button" class="bieden" value="Bieden" />
					<p class="timer">23:23:59</p>
					<p class="prijs">9000</p>
				</div>
			</s:iterator>
			<div class="item">
				<a href="#">Fiets</a>
				<div class="imgBox">
					<img src="http://carpediem2.punt.nl/_files/2012-09-22/img-1308.JPG" />
				</div>
				<input type="button" class="bieden" value="Bieden" />
				<p class="timer">2 Dagen</p>
				<p class="prijs">20</p>
			</div>
			<div class="item">
				<a href="#">Vijftig tinten grijs</a>
				<div class="imgBox">
					<img src="http://s.s-bol.com/imgbase0/imagebase/large/FC/3/2/8/0/9200000002310823.jpg" />
				</div>
				<input type="button" class="bieden" value="Bieden" />
				<p class="timer">00:15</p>
				<p class="prijs">7</p>
			</div>
			<div class="item">
				<a href="#">Mark Rutte</a>
				<div class="imgBox">
					<img src="http://www.gewoon-nieuws.nl/wp-content/uploads/2013/03/mark-rutte31.jpg" />
				</div>
				<input type="button" class="bieden" value="Bieden" />
				<p class="timer">00:21</p>
				<p class="prijs">0</p>
			</div>
			<div class="item">
				<a href="#">Niksssssssssssssssssssssssssssssssssss</a>
				<div class="imgBox">
					<img src="http://whitsblog.com/wp-content/uploads/2013/05/Troll_Face.png" />
				</div>
				<input type="button" class="bieden" value="Bieden" />
				<p class="timer">Gesloten</p>
				<p class="prijs">99999999</p>
			</div>
			<div class="item">
				<a href="#">Jonathan Karssen</a>
				<div class="imgBox">
					<img src="https://fbcdn-sphotos-b-a.akamaihd.net/hphotos-ak-prn1/465749_10150922873310550_1527239559_o.jpg" />
				</div>
				<input type="button" class="bieden" value="Bieden" />
				<p class="timer">00:01</p>
				<p class="prijs">0</p>
			</div>
			<div style="clear: both"></div>
		</div>
		<div class="footer">
			<div class="content">
				<h1>Themaopdracht 5</h1>
				<p>Freek de Bruin</p>
				<p>Jonathan Karssen</p>
				<p>Jordan Melendez</p>
				<p>Ridley Ruiz</p>
				<p>Maarten Zonneveld</p>
				<p style="text-align: center"><i>&copy;2013<br/>Team 4 | V2B | Hogeschool Utrecht</i></p>
			</div>
		</div>
	</body>
</html>