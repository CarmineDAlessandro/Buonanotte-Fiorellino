<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>



<%
	/*l'amministratore*/
	Amministratore admin = (Amministratore) request.getSession().getAttribute("admin");

	/*la lista dei prodotti che andrà visualizzata*/
	ArrayList<Prodotto> products = (ArrayList<Prodotto>) request.getAttribute("lista");

	/*il carrello*/
	//	Cart cart = (Cart) request.getSession().getAttribute("cart");
%>
<!DOCTYPE html>
<html>
<head>
<head>
<link href="../css/Negozio.css" rel="stylesheet" type="text/css">
<link href="../css/common-layout.css" type="text/css" rel="stylesheet">
<%@ page import="java.util.*,prodottipackage.*,utentipackage.*"%>
<title>Fiorazon</title>
</head>
<body>
	<!-- intestazione e menu -->
	<%@include file="../html/header.html"%>
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
				<li><a href="http://localhost:8080/PROGETTO_PW_INTERO/product">Negozio</a></li>
				<li><a href="index.jsp?IdPage=3">Fiorazon</a></li>
			</ul>
		</nav>


	<!-- ricerca dei prodotti -->
	<h4>Effettua una ricerca per prezzo oppure per nome</h4>
	<div>
		<table>
		
		<form action="ControlloRicercaProdottiNomeServlet" method="post">
			
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
	
		<form action="ControlloRicercaProdottiPrezzoServlet" method="post">
			
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
	
		<form action="ControlloRicercaProdottiPrezzoServlet" method="post">
			
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
	
	<div id="lista">
		<table>

			<%
				if (products != null && products.size() > 0) {
			%>
			<tr>

				<th>Nome</th>
				<th>Descrizione</th>
				<th>Prezzo</th>
				<th></th>
			</tr>

			<%
				for (Prodotto p : products) {
			%>
			<tr>

				<td><%=p.getNome()%></td>
				<td><%=p.getDescrizione()%></td>
				<td><%=p.getPrezzo()%></td>
				<td>
					<%
						if (admin != null) {
					%> <a
					href="ControlloEliminaProdottoServlet&idProdotto=
								<%=p.getIdProdotto()%>">Cancella</a><br>
					<a href="ModificaProdotto.jsp?idProdotto=<%=
						p.getIdProdotto()%>">Modifica</a><br>
					<% } %>
 					<!-- Per aggiungere il prodotto al carrello --> <a
					href="product?action=addC&id=<%=p.getIdProdotto()%>">Aggiungi
						al carrello</a>
				</td>

			</tr>
			<%
				}
				} else {
			%>
			<tr>
				<td colspan="6px">Non ci sono prodotti da mostrare</td>
			</tr>
			<tr>
				<td colspan="6px"><a href="ElencoProdottiServlet">Clicca
						qui per la lista</a></td>


			</tr>
			<%
				}
			%>
		</table>


		<!-- form per l'inserimento di un nuovo prodotto -->
	
		<%
			if (admin != null) {
		%>
		<div id="insert">
			<h4>Inserisci un nuovo prodotto</h4>
			<form action="ControlloInerisciProdottoServlet" method="post">

				<table id="table-insert">
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



	<%@include file="../html/footer.html"%>

	<!-- script per gli errori dovuti alle precondizioni sbagliate
	HttpServletResponse.SC_PRECONDITION_FAILED
		e per le SQLException, che generano una
	HttpServletResponse.SC_INTERNAL_SERVER_ERROR -->
	<%
		if (response.getStatus() == HttpServletResponse.SC_PRECONDITION_FAILED) {
	%>
	<script>
		alert("Parametri formalmente scorretti");
	</script>
	<%
		} else if (response.getStatus() == HttpServletResponse.SC_INTERNAL_SERVER_ERROR) {
	%>
	<script>
		alert("Operazione non possibile");
	</script>
	<%
		}
	%>
</body>
</html>