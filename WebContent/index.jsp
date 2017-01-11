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
				<li><a href="index.jsp?IdPage=1"> <%
 	if (session.getAttribute("utente")== null && session.getAttribute("amministratore") == null) {
 %> Login <%
 	} else {
 %> Utente <%
 	}
 %>
				</a></li>
				<li><a href="index.jsp?IdPage=8">Catalogo</a></li>
				<li><a href="index.jsp?IdPage=3">Fiorazon</a></li>
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
				case 1:
		%><%@include file="pages/Login.jsp"%>
		<%
			;
					break;
				case 2:
		%><%@include file="pages/Registrazione.jsp"%>
		<%
			;
					break;
				case 3:
		%><%@include file="pages/descrizione.jsp"%>
		<% 
			;
				break;
				case 8:
		%><%@include file="pages/Catalogo.jsp"%>
		<% 
			;
				break;
				case 9:
		%><%@include file="pages/ModificaProdotto.jsp"%>	
		<% 
			;
				break;
				case 10:
		%><%@include file="pages/Carrello.jsp"%>
		<%
		 	;
				}
				
			}
		%>

		<%@include file="html/footer.html"%>
	</div>
</body>
</html>