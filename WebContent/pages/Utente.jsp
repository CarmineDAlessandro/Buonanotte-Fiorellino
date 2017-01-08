<%Utente bean = (Utente) request.getSession().getAttribute("utente");%>
<head>
<link rel="stylesheet" href="css/Utente.css" type="text/css">
</head>
<body>
<%@page import="utentipackage.Utente" %>
<%if (bean != null) { %>
	<form action="ControlloLogoutUtenteServlet" method="post">
	<input type="submit" value ="Logout">
	</form>
	<div id="content-studente">
		<h1>Benvenuto nella tua Pagina Utente</h1>
		<h2>Informazioni Personali</h2>
		<table>
			<tr>
				<td>
			<tr>
				<th>User:</th>
				<td><%=bean.getUsername() %></td>
			</tr>
			<tr>
				<th>Nome:</th>
				<td><%=bean.getNome() %></td>
			</tr>
			<tr>
				<th>Cognome:</th>
				<td><%=bean.getCognome() %></td>
			</tr>
			<tr>
				<th>Data di nascita:</th>
				<td><%=bean.getDataDiNascita() %></td>
			</tr>
			<tr>
				<th>Città di nascita:</th>
				<td><%=bean.getCittaDiNascita() %></td>
			</tr>
			<tr>
				<th>Codice fiscale:</th>
				<td><%=bean.getCodiceFiscale() %></td>
			</tr>
			<tr>
				<th>Città di residenza:</th>
				<td><%=bean.getCittaResidenza() %></td>
			</tr>
			<tr id="email">
				<th>E-Mail:</th>
				<td><%=bean.geteMail() %></td>
			</tr>
			<tr>
				<th>Provincia:</th>
				<td><%=bean.getProvincia() %></td>
			</tr>
			<tr>
				<th>Via:</th>
				<td><%=bean.getVia() %></td>
			</tr>
			<tr>
				<th>Numero civico:</th>
				<td><%=bean.getNumeroCivico() %></td>
			</tr>
			<tr>
				<th>Cap:</th>
				<td><%=bean.getCap() %></td>
			</tr>
		</table>
<% } else {
	
response.sendRedirect("http://localhost:8080/Fiorazon/index.jsp?IdPage=1");

%>
<%} %>
</body>
