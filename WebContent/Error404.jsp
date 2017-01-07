<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" errorPage="RequestError.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="css/common-layout.css" type="text/css" rel="stylesheet">
<link href="css/error.css" type="text/css" rel="stylesheet">
<title>Progetto PW</title>
</head>
<body>
	<div id="content">
		<%@include file="html/header.html"%>
		<nav>
			<ul>
				<li><a href="index.jsp?IdPage=0">Home</a></li>
				<li><a href="index.jsp?IdPage=1">Cerca</a></li>
				<li><a href="index.jsp?IdPage=2">Login</a></li>
				<li><a href="index.jsp?IdPage=3">Negozio</a></li>
				<li><a href="index.jsp?IdPage=4">Conservatorio</a></li>
			</ul>
		</nav>

		<div class="error">
			<h1>Ops...! (Errore 404)</h1>
			<p>Questo è imbarazzante...<br>
			Purtroppo la risorsa cercata non è attualmente disponibile.<br>
			Ti invitiamo a riprovare più tardi...</p>
		</div>

		<%@include file="html/footer.html"%>
	</div>
</body>
</html>