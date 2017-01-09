<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>



<%
	/*l'amministratore*/
	Amministratore admin = (Amministratore) 
						request.getSession().getAttribute("admin");

	/*la lista dei prodotti che andrà visualizzata*/
	ArrayList<Prodotto> products = (ArrayList<Prodotto>) request.getAttribute("lista");

	/*il carrello*/
//	Cart cart = (Cart) request.getSession().getAttribute("cart");
%>
<!DOCTYPE html>
<html>
<head>
<head>
<link href="css/Negozio.css" rel="stylesheet" type="text/css">
<%@ page import="java.util.*,prodottipackage.*,utentipackage.*"%>
<title>Fiorazon</title>
</head>
<body>


	<!-- la lista dei prodotti -->
	<div id="lista">
		<table>

			<%
				if (products != null && products.size() > 0) {
			%>
			<tr>
				<th>Codice</th>
				<th>Nome</th>
				<th>Descrizione</th>
				<th>Prezzo</th>
				<th></th>
			</tr>

			<%
				for (Prodotto p : products) {
			%>
			<tr>
				<td><%=p.getIdProdotto()%></td>
				<td><%=p.getNome()%></td>
				<td><%=p.getDescrizione()%></td>
				<td><%=p.getPrezzo()%></td>
				<td>
					<%
						if (admin != null) {
					%> <a
					href="ControlloEliminaProdottoServlet&idProdotto=
								<%=p.getIdProdotto()%>">Cancella</a><br>
					<a href="">Modifica</a>
					<%
						}
					%> <!-- Per aggiungere il prodotto al carrello --> <a
					href="product?action=addC&id=<%=p.getIdProdotto()%>">Aggiungi
						al carrello</a>
				</td>
				<!-- PER IL CARRELLO -->
			</tr>
			<%
				}
				} else {
			%>
			<tr>
				<td colspan="6px">Non ci sono prodotti da mostrare</td>
			</tr>
			<tr>
				<td colspan="6px"><a href="../ElencoProdottiServlet">Clicca
						qui per la lista</a></td>


			</tr>
			<%
				}
			%>
		</table>

	</div>



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