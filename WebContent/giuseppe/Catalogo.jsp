<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*, bean.Prodotto"%>
    
<%
int i=0;

	ArrayList<?> lista = (ArrayList<?>) request.getSession().getAttribute("Elenco");
%>
<%
	String usern = (String) request.getSession().getAttribute("username");
	String ruolo = (String) request.getSession().getAttribute("ruolo");
	String link = null;
%> 

<!DOCTYPE html>
<html>
<head>
	
	<title>fiorazon</title>
	<link rel="icon" href=" ">
	<link type="text/css" rel="stylesheet" href="">
</head>
<body>
	
	<section>
		<div >catalogo</div><br>
	<%if("amministratore".equals(ruolo)) {%>
		<a href="InserisciProdotto.jsp">+ aggiungi prodotto</a><br><br>
	<%	}%> 
		<a href="Home.jsp">home</a><br><br>
		
		<table class="tr3">
			<tr>
			<%i=0; %>
			<th class="mex4">immagine</th>
				<th class="mex4">nome</th>
				<th class="mex4">quantita</th>
				<th class="mex4">descrizione</th>
				<th class="mex4">prezzo</th>
				<%if("amministratore".equals(ruolo)) {%>
				<th class="mex4">elimina</th>
				<th class="mex4">modifica</th>
				<th class="mex4">cambiaImg</th><%} %>
			</tr>
			<%
				Iterator<?> it = lista.iterator();
				while(it.hasNext()) {
					Prodotto pr = (Prodotto) it.next();
			%>
			<tr>
			
				<td class="mex4"><img src=<%= pr.getUrlImmagine() %> alt= <%= pr.getNome() %>></td>
				<td class="mex4"><%= pr.getNome() %></td>
				<td class="mex4"><%= pr.getQuantita() %></td>
				<td class="mex4"><%= pr.getDescrizione() %></td>
				<td class="mex4"><%= pr.getPrezzo() %></td>
				<%if("amministratore".equals(ruolo)) {%>
				<th class="mex4">
					<form action="EliminaProdotto" name="elimina" method="post" >
					<input type="hidden" name="urlP" value=<%= pr.getUrlImmagine() %>>
					<input type="hidden" name="idP" value=<%= pr.getIdProdotto() %>>
					<input type="submit" value="X" >
					</form>
				</th>
				<th class="mex4">
					<form action="modificapinit" name="modifica" method="post" >
					<input type="hidden" name="idP" value=<%= pr.getIdProdotto() %>>
					<input type="submit" value="M" >
					</form>
				</th>
				<th class="mex4">
				<form action="ModificaProdottoImg.jsp" name="modificaImg" method="link" >
					<input type="hidden" name="urlP" value=<%= pr.getUrlImmagine() %>>
					<input type="hidden" name="idP" value=<%= pr.getIdProdotto() %>>
					<input type="submit" value="ModImg" >
					</form>
				</th>
				<%}%>
			
			</tr>
			<% } %>
		</table>
	</section>
	
</body>
</html>
