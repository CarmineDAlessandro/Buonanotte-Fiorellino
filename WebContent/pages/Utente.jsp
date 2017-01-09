<%Utente bean = (Utente) request.getSession().getAttribute("utente");%>
<head>
<link rel="stylesheet" href="css/Utente.css" type="text/css">
</head>
<body>
<%@page import="utentipackage.Utente" %>
<%if (bean != null) { %>
	<table>
		<tr>
			<td>
			<form action="ControlloLogoutUtenteServlet" method="post">
			<input type="submit" value ="Logout">
			</form>
			</td>
			<td>
			<form action="ControlloCancellazioneUtenteServlet" method="post">
			<input type="submit" value="Elimina il tuo profilo utente">
			</form>
			</td>
		</tr>
	</table>
	<div id="content-studente">
		<h1>Benvenuto nella tua Pagina Utente</h1>
		<h2>Informazioni Personali</h2>
		<table>
			<tr>
				<td>
			<tr>
				<th>User:</th>
				<td><%=bean.getUsername() %></td>
				<td>
					
			</tr>
			<tr>
				<th>Nome:</th>
				<td><%=bean.getNome() %></td>
				<td>
					<form action="ControlloModificaDatiUtentiServlet" method="post">
						<input type="hidden" name = "action" value = "nome">
						<input type="text" name="dato" required>
						<input type="submit" value="Modifica">
					</form>
				</td>
			</tr>
			<tr>
				<th>Cognome:</th>
				<td><%=bean.getCognome() %></td>
				<td>
					<form action="ControlloModificaDatiUtentiServlet" method="post">
						<input type="hidden" name = "action" value = "cognome">
						<input type="text" name="dato" required>
						<input type="submit" value="Modifica">
					</form>
				</td>
			</tr>
			<tr>
				<th>Data di nascita:</th>
				<td><%=bean.getDataDiNascita() %></td>
				<td>
					<form action="ControlloModificaDatiUtentiServlet" method="post">
						<input type="hidden" name = "action" value = "data">
						<input type="date" placeholder="gg/mm/aaaa" name="dato" required>
						<input type="submit" value="Modifica">
					</form>
				</td>
			</tr>
			<tr>
				<th>Città di nascita:</th>
				<td><%=bean.getCittaDiNascita() %></td>
				<td>
					<form action="ControlloModificaDatiUtentiServlet" method="post">
						<input type="hidden" name = "action" value = "cittàN">
						<input type="text" name="dato" required>
						<input type="submit" value="Modifica">
					</form>
				</td>
			</tr>
			<tr>
				<th>Codice fiscale:</th>
				<td><%=bean.getCodiceFiscale() %></td>
				<td>
					<form action="ControlloModificaDatiUtentiServlet" method="post">
						<input type="hidden" name = "action" value = "cf">
						<input type="text" name="dato" required>
						<input type="submit" value="Modifica">
					</form>
				</td>
			</tr>
			<tr>
				<th>Città di residenza:</th>
				<td><%=bean.getCittaResidenza() %></td>
				<td>
					<form action="ControlloModificaDatiUtentiServlet" method="post">
						<input type="hidden" name = "action" value = "cittàR">
						<input type="text" name="dato" required>
						<input type="submit" value="Modifica">
					</form>
				</td>
			</tr>
			<tr id="email">
				<th>E-Mail:</th>
				<td><%=bean.geteMail() %></td>
				<td>
					<form action="ControlloModificaDatiUtentiServlet" method="post">
						<input type="hidden" name = "action" value = "eMail">
						<input type="text" name="dato" required>
						<input type="submit" value="Modifica">
					</form>
				</td>
			</tr>
			<tr>
				<th>Provincia:</th>
				<td><%=bean.getProvincia() %></td>
				<td>
					<form action="ControlloModificaDatiUtentiServlet" method="post">
						<input type="hidden" name = "action" value = "provincia">
						<input type="text" name="dato" required>
						<input type="submit" value="Modifica">
					</form>
				</td>
			</tr>
			<tr>
				<th>Via:</th>
				<td><%=bean.getVia() %></td>
				<td>
					<form action="ControlloModificaDatiUtentiServlet" method="post">
						<input type="hidden" name = "action" value = "via">
						<input type="text" name="dato" required>
						<input type="submit" value="Modifica">
					</form>
				</td>
			</tr>
			<tr>
				<th>Numero civico:</th>
				<td><%=bean.getNumeroCivico() %></td>
				<td>
					<form action="ControlloModificaDatiUtentiServlet" method="post">
						<input type="hidden" name = "action" value = "civico">
						<input type="text" name="dato" required>
						<input type="submit" value="Modifica">
					</form>
				</td>
			</tr>
			<tr>
				<th>Cap:</th>
				<td><%=bean.getCap() %></td>
				<td>
					<form action="ControlloModificaDatiUtentiServlet" method="post">
						<input type="hidden" name = "action" value = "cap">
						<input type="text" name="dato" required>
						<input type="submit" value="Modifica">
					</form>
				</td>
			</tr>
			<tr>
				<th>Modifica password:</th>
				<td>
				 <form action="ControlloModificaDatiUtentiServlet" method = "post">
				 	<input type ="hidden" name ="action" value="password">
				 	<input type="password" name="dato" required>
				 	<input type="submit" value="Modifica">
				 </form>
				 </td>
		</table>
		</div>
		<% if(response.getStatus() == HttpServletResponse.SC_FORBIDDEN) { %>
			<script>
				alert("Modifica non riuscita.");
			</script>
			<% } %>
		
<% } else {
	
response.sendRedirect("http://localhost:8080/Fiorazon/index.jsp?IdPage=1");

%>
<%} %>
</body>
