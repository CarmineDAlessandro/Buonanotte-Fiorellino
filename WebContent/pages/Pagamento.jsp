

<%Ordine ordine = (Ordine) request.getSession().getAttribute("ordine");%>
<%Utente utente = (Utente) request.getSession().getAttribute("utente"); %>
<head>
<link rel="stylesheet" href="css/Pagamento.css" type="text/css">
</head>
<body>
<%@page import = "ordinepackage.Ordine" %>
<%@page import = "utentipackage.Utente" %>
<%if (ordine != null) {%>
<div id="content-riepilogo">
	<h1>Informazioni di riepilogo</h1>
		<h2>Informazioni del destinatario</h2>
		<table>
			<tr>
				<td>
			<tr>
				<th>Nome:</th>
				<td><%=utente.getNome() %></td>
				<td>
					
			</tr>
			<tr>
				<th>Cognome:</th>
				<td><%=utente.getCognome() %></td>
				<td>
					
			</tr>
			<tr>
				<th>Città:</th>
				<td><%=utente.getCittaResidenza() %></td>
				<td>
					
			</tr>
			<tr>
				<th>Via:</th>
				<td><%=utente.getVia() %></td>
				<td>
					
			</tr>
			<tr>
				<th>Numero civico:</th>
				<td><%=utente.getNumeroCivico() %></td>
				<td>
					
			</tr>
			
			<tr>
				<th>Provincia:</th>
				<td><%=utente.getProvincia() %></td>
				<td>
					
			</tr>
			<tr>
				<th>CAP:</th>
				<td><%=utente.getCap() %></td>
				<td>
					
			</tr>
			
			
			<tr>
				<th>Cognome:</th>
				<td><%=utente.getCognome() %></td>
				<td>
					
			</tr>
			<tr>
				<th>Importo da pagare:</th>
				<%String prezzoTotaleS = ""+ordine.getPrezzoTotale(); %>
				<td>&euro;<%=Float.valueOf(prezzoTotaleS)%></td>
				<td>
					
			</tr>
			
			<tr>
				<td>
				
			<tr>
				<td>
				<form action = "ControlloPagamentoServlet" method="post">
				<label>Inserisci carta di credito: </label>
				<input type = "text" name = "iban" placeholder = "4023000000000000" required>
				<input type = "submit" value="Conferma l'acquisto">
				<input type = "hidden" value="<%=Float.valueOf(prezzoTotaleS)%>">
				</form>
				</td>
			</tr>
	</table>
</div>
<%} %>
</body>