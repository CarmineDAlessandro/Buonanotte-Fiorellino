<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*, bean.Prodotto,bean.Ordine"%>
    
 <%
int i=0;

	ArrayList<?> lista = (ArrayList<?>) request.getSession().getAttribute("Elenco");

	String usern = (String) request.getSession().getAttribute("username");
	String ruolo = (String) request.getSession().getAttribute("ruolo");
	String link = null;
%> 
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>fiorazon</title>
</head>
<body>
<a href="HomeAmministratore.jsp">home</a><br><br>
ordini amministratore<br><br><br>

<table class="tr3">
			<tr>
			
				<th class="mex4">id</th>
				<th class="mex4">utenteOrdine</th>
				<th class="mex4">PrezzoTotale</th>
				<th class="mex4">stato</th>
					<th class="mex4">nome</th>
					<th class="mex4">descrizione</th>
					<th class="mex4">avanzaStato</th>
			</tr>
			<%
				Iterator<?> it = lista.iterator();
				while(it.hasNext()) {
					Ordine ord = (Ordine) it.next();
			%>
			<tr>
			<td class="mex4"><%= ord.getId() %></td>
			<td class="mex4"><%= ord.getUtenteOrdine() %></td>
			<td class="mex4"><%= ord.getPrezzoTotale() %></td>
			<td class="mex4"><%= ord.getStato() %></td>
			
				<% ArrayList<Prodotto> listaProdotti = new ArrayList<Prodotto>();
					listaProdotti =ord.getProdotto();%>
				
				
					
				<td class="mex4">
				<%for(Prodotto pr : listaProdotti){%>
					 <%= pr.getNome() %><br>
				<% } %>
				</td>
				<td class="mex4">
				<%for(Prodotto pr : listaProdotti){%>
					 <%= pr.getDescrizione() %><br>
				<% } %>
				</td>
				<td class="mex4">
				<form action="AvanzaStato" name="modifica" method="post" >

  				<select name="stato" >
   					<option value="Da spedire" selected="selected">Da spedire  </option>
  					 <option value="Spedito"> Spedito  </option>
   					<option value="Arrivato"> Arrivato  </option>
  				</select>
					<input type="hidden" name="idOrdine" value=<%= ord.getId()%>>
					<input type="submit" value="avanza" >
					</form>
				</td>
					<th class="mex4">
					<form action="EliminaOrdine" name="elimina" method="post" >
					<input type="hidden" name="idOrd" value=<%= ord.getId() %>>
					<input type="submit" value="elimina" >
					</form>
				</th>
			</tr>
			<tr><td>|____|</td> <td>|___________________|</td> <td>|________|</td> <td>|__________|</td> <td>|__________________|</td> <td>|___________________|</td><td>|___________________|</td><td>|__________|</td></tr>
			
			
			<% } %>
		</table>
</body>
</html>