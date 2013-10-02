<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
	<jsp:include page="/jsp/include/head.jsp" />
	<body>
		<jsp:include page="/jsp/include/top.jsp" />
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
				<a href="#">Mark Rutte</a>
				<div class="imgBox">
					<img src="http://www.gewoon-nieuws.nl/wp-content/uploads/2013/03/mark-rutte31.jpg" />
				</div>
				<input type="button" class="bieden" value="Bieden" />
				<p class="timer">00:21</p>
				<p class="prijs">0</p>
			</div>
			<div style="clear: both"></div>
		</div>
		<jsp:include page="/jsp/include/footer.jsp" />
	</body>
</html>