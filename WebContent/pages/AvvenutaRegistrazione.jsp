<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" errorPage="RequestError.jsp"%>

<!DOCTYPE html>
<html>
<head>
<style>
div#content-avvenuta {
	background-color:white;
	padding:5px 5px 5px 20px;
	height:300px;
	
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="css/common-layout.css" type="text/css" rel="stylesheet">
<title>Fiorazon</title>
</head>
<body>
	<div id="content">
		<%@include file="../html/header.html"%>
		<nav>
			<ul>
				<li><a href="index.jsp?IdPage=0">Home</a></li>
				
				<li><a href="index.jsp?IdPage=1">Login</a></li>
				<li><a href="index.jsp?IdPage=8">Catalogo</a></li>
				<li><a href="index.jsp?IdPage=3">Descrizione</a></li>
			</ul>
		</nav>

		
		<div id="content-avvenuta">
			
			<h1>BENVENUTO!</h1>
			<p>Ti sei correttamente registrato!</p>
			
		</div>

		<%@include file="../html/footer.html"%>
	</div>
</body>
</html>

