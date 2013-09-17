<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
		<title>Menu voor leden</title>
		<link rel="stylesheet" type="text/css" href="../css/plarktmaats.css" />
	</head>
	<body>
		<h4>
			Ingelogd:
			<s:property value="user.username" />
		</h4>
		<ul>
			<li><a href="<s:url action='BookList' namespace="/member"/>">Alle boeken</a></li>
			<li><a href="<s:url action='BookInfoForm' namespace="/member"/>">Boekinformatie</a></li>
			<li><a href="<s:url action='BookReservationForm' namespace="/member"/>">Reserveer boek (nyi)</a></li>
			<li><a href="<s:url action='CancelReservationForm' namespace="/member"/>">Annuleer reservering (nyi)</a></li>
			<li><a href="<s:url action='MyBookList' namespace="/member"/>">Alle gereserveerde en geleende boeken (nyi)</a></li>
			<li><a href="<s:url action='ChangePasswordForm' namespace="/member"/>">Wijzig wachtwoord</a></li>
			<li><a href="<s:url action='Logout' namespace="/member"/>">Log uit</a></li>
		</ul>
	</body>
</html>
