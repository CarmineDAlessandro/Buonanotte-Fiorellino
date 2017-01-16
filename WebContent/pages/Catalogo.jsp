<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>



<%
	/*l'amministratore*/
	Amministratore admin = (Amministratore) request.getSession().getAttribute("amministratore");
	/*la lista dei prodotti che andrà visualizzata*/
	ArrayList<Prodotto> products = (ArrayList<Prodotto>) request.getAttribute("lista");
	/*il carrello*/
	//	Cart cart = (Cart) request.getSession().getAttribute("cart");
	/*l'utente*/
	Utente user = (Utente) request.getSession().getAttribute("utente");
%>
<!DOCTYPE html>
<html>
<head>

<style type="text/css">
	.giallo {background-color: #FFFF00;}
</style>
<%@ page import="java.util.*,prodottipackage.*,utentipackage.*"%>
<title>Fiorazon</title>
</head>
<body>


	<!-- ricerca dei prodotti -->
	<h4 class="giallo">Effettua una ricerca per prezzo oppure per nome</h4>
	<div class="giallo">
		<table>
		
		<form action="ControlloRicercaProdottiServlet" method="post">
		<input type="hidden" name="action" value="nome">	
				<tr>
					<td>
						<label for="nome">Nome:</label>
					</td>
					<td>
						<input name="nome" type="text" required>
					</td>
					<td>
						<input type="submit" value="Ricerca">
					</td>
				</tr>
			
		</form>
	
		<form action="ControlloRicercaProdottiServlet" method="post">
		<input type="hidden" name="action" value="prezzo">	
				<tr>
					<td>
						<label for="prezzoMin">Prezzo minimo:</label>
					</td>
					<td>
						<input name="prezzoMin" type="number"	required>
					</td>
					<td>
						<input type="submit" value="Ricerca">
					</td>
				</tr>
			
		</form>
	
		<form action="ControlloRicercaProdottiServlet" method="post">
		<input type="hidden" name="action" value="prezzo">		
				<tr>
					<td>
						<label for="prezzoMax">Prezzo massimo:</label>
					</td>
					<td>
						<input name="prezzoMax" type="number"	required>
					</td>
					<td>
						<input type="submit" value="Ricerca">
					</td>
				</tr>
			
		</form>
		</table>
	</div>

	<!-- la lista dei prodotti -->
	
	<div id="lista" class="giallo">
		<table>

			<%
				if (products != null && products.size() > 0) {
			%>
			<tr>
				<th>Immagine</th>
				<th>Nome</th>
				<th>Descrizione</th>
				<th>Quantità</th>
				<th>Prezzo</th>
				<th></th>
			</tr>

			<%
				for (Prodotto p : products) {
			%>
			<tr>
				<td><img src="<%=p.getUrlImmagine()%>" alt="Immagine non disponibile" height="82" width="82"></td>
				<td><%=p.getNome()%></td>
				<td><%=p.getDescrizione()%></td>
				<td><%=p.getQuantita()%>
				<td>&euro;<%=p.getPrezzo()%></td>
				<td>
					<%
						if (admin != null) {
					%> <form action="ControlloEliminaProdottoServlet" method="post">
						<input type="submit" value="Elimina prodotto">
						<input type="hidden" name="id" value=<%=p.getIdProdotto()%>>
						</form>
						<form action="index.jsp?IdPage=9" method="post">
						<input type="submit" value="Modifica prodotto">
						<input type="hidden" name="id" value=<%=p.getIdProdotto()%>>
						</form>		
					
					<% } else if(user != null){%>
 					<!-- Per aggiungere il prodotto al carrello --> 
 					<form action="ControlloAggiuntaProdottoServlet" method="post">
 					<input type="number" name="quantità" min="1" max="<%=p.getQuantita()%>" required>
 					<input type ="submit" value="Aggiungi al carrello">
 					<input type="hidden" name="id" value="<%=p.getIdProdotto()%>">
 					<input type="hidden" name="username" value="<%=user.getUsername()%>">
 					</form>
 					
						<%} else {%>
							<a href="index.jsp?IdPage=1">Effettua il login 
							per aggiungere al carrello</a>
						<% } %>
				</td>

			</tr>
			<%
				}
				} else {
			%>
			<tr>
				<td colspan="6px">Non ci sono prodotti da mostrare!</td>
			</tr>
			
			<%
				}
			%>
			<tr>
				<td colspan="6px"><a href="ElencoProdottiServlet">Clicca
						qui per la lista completa.</a></td>


			</tr>
		</table>


		<!-- form per l'inserimento di un nuovo prodotto -->
	
		<%
			if (admin != null) {
		%>
		<div id="insert">
			<h4>Inserisci un nuovo prodotto</h4>
			<form action="ControlloInserisciProdottoServlet" method="post" enctype="multipart/form-data">

				<table id="table-insert">
					<tr>
						<td><label for="immagine">Immagine:</label></td>
						<td><input name="img" type="file" required></td>
					</tr>
					<tr>
						<td><label for="nome">Nome:</label></td>
						<td><input name="nome" type="text"  required
							placeholder="inserisci il nome..."></td>
					</tr>
					<tr>
						<td><label for="descrizione">Descrizione:</label></td>
						<td><textarea name="descrizione" maxlength="300" rows="3"
								required placeholder="scrivi la descrizione..."></textarea></td>
					</tr>
					<tr>
						<td><label for="prezzo">Prezzo:</label></td>
						<td><input name="prezzo" type="number" min="0" value="0"
							required></td>
					</tr>
					<tr>
						<td><label for="quantita">Quantità:</label></td>
						<td><input name="quantita" type="number" min="0" value="1"
							required></td>
					</tr>
					<tr>
						<td></td>
						<td><input type="submit" value="Invia">
						<!-- <input type="reset" value="Resetta"> -->
						</td>
					</tr>
				</table>
			</form>
		</div>
		<%
			}
		%>

	</div>


	<!-- script per gli errori dovuti alle precondizioni sbagliate
	HttpServletResponse.SC_PRECONDITION_FAILED
		e per le SQLException, che generano una
	HttpServletResponse.SC_INTERNAL_SERVER_ERROR -->
	<%
		if (response.getStatus() == HttpServletResponse.SC_PRECONDITION_FAILED) {
	%>
	<script>
		alert("Parametri formalmente scorretti, oppure prodotto già esistente. Si prega di ricontrollare.");
	</script>
	<%
		} else if (response.getStatus() == HttpServletResponse.SC_INTERNAL_SERVER_ERROR) {
	%>
	<script>
		alert("Operazione non possibile");
	</script>
	
	<%
		} else if (response.getStatus() == HttpServletResponse.SC_NOT_MODIFIED) {
	%>
	<script>
		alert("Quantità insufficiente nel negozio!");
	</script>
	<%
		}
	%>
</body>
</html>