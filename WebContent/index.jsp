<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" errorPage="RequestError.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="css/common-layout.css" type="text/css" rel="stylesheet">


<title>Fiorazon</title>
</head>
<body>
	<div id="content">
		<%@include file="html/header.html"%>
		<nav>
			<ul>
				<li><a href="index.jsp?IdPage=0">Home</a></li>
				<li><a href="index.jsp?IdPage=1">Cerca</a></li>
				<li><a href="index.jsp?IdPage=2"> <%
 	if (session.getAttribute("studente")== null && session.getAttribute("professore") == null) {
 %> Login <%
 	} else {
 %> Utente <%
 	}
 %>
				</a></li>
				<li><a href="http://localhost:8080/PROGETTO_PW_INTERO/product">Negozio</a></li>
				<li><a href="index.jsp?IdPage=4">Fiorazon</a></li>
			</ul>
		</nav>

		<%
			if (request.getParameter("IdPage") == null) {
		%><%@include file="pages/HomePage.jsp"%>
		<%
			} else {
		%>
		<%
			switch (Integer.parseInt(request.getParameter("IdPage"))) {
				case 0:
		%><%@include file="pages/HomePage.jsp"%>
		<%
			;
					break;
				case 4:
		%><%@include file="pages/descrizione.jsp"%>
		<% 
			;
				break;
				}
				
			}
		%>

		<%@include file="html/footer.html"%>
	</div>
</body>
</html>