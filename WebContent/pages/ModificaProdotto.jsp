<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="../css/Negozio.css" rel="stylesheet" type="text/css">
<link href="../css/common-layout.css" type="text/css" rel="stylesheet">
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

	<div id="form-modifica" 
		style=" background-color:white;">
		<h4>Inserire il dato da modificare in questo form</h4>
		<table>
		<tr>
		<form action="ControlloModificaProdottoServlet" method="post">
		<input type="hidden" name="action" value="nome">
		<input type="hidden" name="idProdotto" value="<%=request.getParameter("idProdotto")%>">
			<tr>
				<td>
					<label for="dato">Nome:</label>
				</td>
				<td>
					<input name="dato" type="text" required>
				</td>
				<td>
					<input type="submit" value="Modifica">
				</td>
			</tr>
		
		</form>
		</tr>
			
		<tr>
		<form action="ControlloModificaProdottoServlet" method="post">
		<input type="hidden" name="action" value="descrizione">
		<input type="hidden" name="idProdotto" value="<%=request.getParameter("idProdotto")%>">
			<tr>
				<td>
					<label for="dato">Descrizione:</label>
				</td>
				<td>
					<input name="dato" type="text" required>
				</td>
				<td>
					<input type="submit" value="Modifica">
				</td>
			</tr>
		</form>
		<tr>
		<tr>
		<form action="ControlloModificaProdottoServlet" method="post">
		<input type="hidden" name="action" value="prezzo">
		<input type="hidden" name="idProdotto" value="<%=request.getParameter("idProdotto")%>">
			<tr>
				<td>
					<label for="dato">Prezzo:</label>
				</td>
				<td>
					<input name="dato" type="number" required>
				</td>
				<td>
					<input type="submit" value="Modifica">
				</td>
			</tr>
		</form>
		<tr>
		<tr>
		<form action="ControlloModificaProdottoServlet" method="post">
		<input type="hidden" name="action" value="quantità">
		<input type="hidden" name="idProdotto" value="<%=request.getParameter("idProdotto")%>">
			<tr>
				<td>
					<label for="dato">Quantità:</label>
				</td>
				<td>
					<input name="dato" type="number" required>
				</td>
				<td>
					<input type="submit" value="Modifica">
				</td>
			</tr>
		</form>
		<tr>
		</table>
	</div>
	<%@include file="../html/footer.html"%>
</body>
</html>