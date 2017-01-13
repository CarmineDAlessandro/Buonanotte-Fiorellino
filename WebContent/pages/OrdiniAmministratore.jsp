<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" errorPage="RequestError.jsp"%>
<%if(request.getSession().getAttribute("amministratore") != null) {%>
<%
	ArrayList<Ordine> ordini = (ArrayList<Ordine>) request.getAttribute("ordini");
	request.removeAttribute("ordini");
%>
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
<link rel="stylesheet" href="css/Ordini.css" type="text/css">
<title>Fiorazon</title>
</head>
<body>
aaa
	<div id="content">
		<%@include file="../html/header.html"%>
		<nav>
			<ul>
				<li><a href="index.jsp?IdPage=0">Home</a></li>
				
				<li><a href="index.jsp?IdPage=1"><%
 	if (session.getAttribute("utente")== null && session.getAttribute("amministratore") == null) {
 %> Login <%
 	} else {
 %> Utente <%
 	} %></a></li>
				<li><a href="index.jsp?IdPage=8">Catalogo</a></li>
				<li><a href="index.jsp?IdPage=3">Descrizione</a></li>
			</ul>
		</nav>
		<%@page import="java.util.ArrayList" %>
		<%@page import="ordinepackage.Ordine"%>
		<%@page import="prodottipackage.Prodotto"%>
		<div id="ordini">
			<%if(ordini != null && ordini.size()>0) {%>	
				<table width="" bgcolor="#ffb833" cellpadding="10" cellspacing="1">
					<tr bgcolor="FFFFFF">
						<th>ID ordine</th>
						<th>Prodotti</th>
						<th>Prezzo totale</th>
						<th>Stato</th>
						<th>Opzione</th>
					</tr>
					
					<% for(Ordine o:ordini) {%>
					<% ArrayList<Prodotto> prodotti = o.getProdotto(); %>
					<% if(prodotti.size() != 0) {%>
					<tr bgcolor="FFFFFF">
						<td valign="baseline" align="right" id="id_ord"> <%= o.getId() %> </td>
						
					
					
						
						 <td>
						 <table >
						 <% for (Prodotto p:prodotti) {%>
						 
						 	<tr bgcolor="FFFFFF">
						 	<td><%=p.getNome() %></td>
						 	<td>n&deg;<%=p.getQuantita() %></td>
						 	<%String prezzoS = ""+p.getPrezzo(); %>
						 	
						 	<td>&euro;<%=Float.valueOf(prezzoS) %></td>
						 	</tr>
						 
						 <%} %>
						 </table>
						 </td>
					
						<td valign="baseline" align="right">&euro;<%= o.getPrezzoTotale() %>
					
						<td valign="baseline" align="right"> <%= o.getStato() %>
						<%if(o.getStato().equalsIgnoreCase("Da Spedire")){ %>
						<td  valign="baseline">
							<form action="ControlloAvanzamentoOrdine" method="post">
							<input type="submit" value="Spedito">
							<input type="hidden" value="<%=o.getId()%>" name="idOrdine">
							</form>
						</td>
						<%} else {%>
						<td>
						</td>
						<%} %>
					</tr>
					<%} %>
					<%} %>
				</table>
			<%
			} else {
		%>
		<div id="nessuno">
		Non hai fatto nessun ordine!
		</div>
		<%}	%>
		</div>
		<%} %>
		<%@include file="../html/footer.html"%>
	</div>
</body>