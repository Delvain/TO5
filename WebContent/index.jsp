<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Refresh" content="0;URL=Menu.action" />
    	<jsp:include page="/jsp/include/head.jsp" />
    	<style type="text/css">
    		div.container {
    			text-align: center;
  				width: 100%;
    		}
    		div.tekst {
    			z-index: 1000;
    			position: relative;
    			font-weight: bold;
    			font-size: 17px;
    		}
    		img {
    			margin-top: -100px;
    			z-index: 50;
    			position: relative;
    			height: 300px;
    		}
    		div.footer {
    			z-index: 900;
    		}
    	</style>
	</head>
	<body>
		<div class="header">
			<div class="content">
				<a href="#" class="logo">&nbsp;</a>
				<div style="clear: both"></div>
			</div>
		</div>
		<div class="top_bar">
			<div class="content">
				&nbsp;	
			</div>
		</div>
		<div class="container">
			<div class="tekst">
				<h3>One moment please</h3>
				<h3>Un momento por favor</h3>
				<h3>Einen Moment bitte</h3>
				<h3>Een oomblik asseblief</h3>
				<h3>Odota hetki</h3>
				<h3>Augnablik</h3>
				<h3>Ang isa sandali mangyaring</h3>
				<h3>Jedan trenutak molim</h3>
				<h3>Een ogenblik geduld alstublieft</h3>
			</div>
			<img src="/TO5/style/images/spinner.gif" />
		</div>
		<jsp:include page="/jsp/include/footer.jsp" />
	</body>	
</html>
