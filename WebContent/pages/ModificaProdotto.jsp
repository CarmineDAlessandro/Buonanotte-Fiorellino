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


	<div id="form-modifica" 
		style=" background-color:white;">
		<h4>Inserire il dato da modificare in questo form</h4>
		<table>
		<tr>
		<form action="ControlloModificaProdottoServlet" method="post">
		<input type="hidden" name="action" value="nome">
		<input type="hidden" name="id" value="<%=request.getParameter("id")%>">
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
		<input type="hidden" name="id" value="<%=request.getParameter("id")%>">
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
		<input type="hidden" name="id" value="<%=request.getParameter("id")%>">
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
		<input type="hidden" name="id" value="<%=request.getParameter("id")%>">
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
		<form action="ControlloModificaProdottoServlet" method="post">
		<input type="hidden" name="action" value="img">
		<input type="hidden" name="id" value="<%=request.getParameter("id")%>">
			<tr>
				<td>
					<label for="dato">Immagine:</label>
				</td>
				<td>
					<input name="dato" type="file" required>
				</td>
				<td>
					<input type="submit" value="Modifica">
				</td>
			</tr>
		</form>
		<tr>
		</table>
	</div>
	
</body>
</html>