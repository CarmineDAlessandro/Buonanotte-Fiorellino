<%Amministratore bean = (Amministratore) request.getSession().getAttribute("amministratore");%>
<head>
<link rel="stylesheet" href="css/Utente.css" type="text/css">
</head>
<body>
<%@page import="utentipackage.Amministratore" %>
<%if (bean != null) { %>
	<form action="ControlloLogoutAmministratoreServlet" method="post">
	<input type="submit" value ="Logout">
	</form>
	
	<div id="content-studente">
		<h1>Benvenuto nella tua Pagina Amministratore</h1>
		<h2>Informazioni Personali</h2>
		<table>
			<tr>
				<td>
			<tr>
				<th>User:</th>
				<td><%=bean.getUsername() %></td>
			</tr>
			
			<tr id="email">
				<th>E-Mail:</th>
				<td><%=bean.geteMail() %></td>
			</tr>
		
			
		</table>
<% } else {
	
response.sendRedirect("http://localhost:8080/Fiorazon/index.jsp?IdPage=1");

%>
<%} %>
</body>