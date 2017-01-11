<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" errorPage="RequestError.jsp"%>
<%if(request.getSession().getAttribute("utente") != null) {%>
<%
	
	Carrello carrello = (Carrello) request.getAttribute("carrello");
%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css">
	.giallo {background-color: #FFFF00;}
</style>
</head>
<body>
	<%@page import="carrelloPackage.Carrello"%>
	<%@page import="prodottipackage.Prodotto" %>
	<%@page import="java.util.ArrayList" %>
	<%ArrayList<Prodotto> lista = carrello.getLista(); %>
	
	<div class="giallo">
			<%if(lista != null && lista.size()>0) {%>	
				<table width="" bgcolor="yellow" cellpadding="10" cellspacing="1">
					<tr bgcolor="FFFFFF">
						<th>Immagine</th>
						<th>Nome</th>
						<th>Descrizione</th>
						<th>Quantità</th>
						<th>Prezzo</th>
						<th></th>
					</tr>
					
					<% for(Prodotto p:lista) {%>
					<tr bgcolor="FFFFFF">
						<td><img src="<%=p.getUrlImmagine()%>" alt="Immagine non disponibile" height="82" width="82"></td>
						<td><%=p.getNome()%></td>
						<td><%=p.getDescrizione()%></td>
						<td><%=p.getQuantita()%>
						<td><%=p.getPrezzo()%></td>
						
						<td>
							<form action="CambiaQuantitàCarrelloServlet" method="post">
							<input type="hidden" name="idProdotto" value="<%=p.getIdProdotto()%>">
							<input type="hidden" name="idCarrello" value="<%=carrello.getId()%>">
							<input type="number" name="quantità"> 
							<input type="submit" value="Cambia la quantità del prodotto">
							
							</form>
						</td>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td>
							<form action="RimuoviProdottoCarrelloServlet" method="post">

							
							<input type="hidden" name="idProdotto" value="<%=p.getIdProdotto()%>">
							<input type="hidden" name="idCarrello" value="<%=carrello.getId()%>">
							<input type="submit" value="Rimuovi il prodotto dal carrello">
							<input type="hidden" name="action" value="Rimuovi">
							</form>
						</td>
					</tr>
			<%}%> </table>
			 <%}else {
		%>
		<div id="nessuno">
		Non hai nessun prodotto nel tuo carrello!
		</div>
		<%}}	%>
		

</body>
