<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="bean.Utente"%>

<%
	String usern = (String) request.getSession().getAttribute("username");

	if(usern == null) {
		response.sendRedirect("./Login.jsp");
		return;
	}
	
	Utente usr = (Utente) request.getSession().getAttribute("Dati");
%>

<!DOCTYPE html>
<html>
<head>
	
	<title>fiorazon</title>
	<link rel="icon" href="./Immagini/">
</head>
<body>
	<a href="Home.jsp">home</a><br><br>
	
	<section>
		<h2>modifica dati:</h2>
		<div class="regist">
			<form action="ModificaUtente2" name="reg" method="post" >
				<fieldset>
					<legend>Dati anagrafici</legend>
					Nome: <input type="text" name="nome" value= <%= usr.getNome() %> /><br><br>
					Cognome: <input type="text" name="cognome" value= <%= usr.getCognome() %> /><br><br>
					e-Mail: <input type="text" name="eMail" value= <%= usr.geteMail() %> /><br><br>
					Codice Fiscale: <input type="text" name="codiceFiscale" value= <%= usr.getCodiceFiscale() %> /><br><br>
					Data di nascita: <input type="text" name="dataNascita" value= <%= usr.getDataNascita() %>/><br><br>
					CittaNascita: <input type="text" name="cittaNascita" value= <%= usr.getCittaNascita() %> /><br><br>
					CittaResidenza: <input type="text" name="cittaResidenza" value= <%= usr.getCittaResidenza() %> /><br><br>
					Via: <input type="text" name="via" value= <%= usr.getVia() %>/><br><br>
					numero Civico: <input type="number" value= <%= usr.getNumeroCivico() %> name="numeroCivico" /><br><br>
					CAP: <input type="number" value= <%= usr.getCap() %> name="cap" /><br><br>
					
				</fieldset>
				<fieldset>
					<legend>Dati utente</legend>
					Username: <input type="text" name="username" value= <%= usr.getUsername() %> /><br><br>
					Password: <input type="password" name="password1" value= <%= usr.getPassword() %> /><br><br>
					Reinserisci password: <input type="password" name="password2" value=<%= usr.getPassword() %> /><br><br>
				</fieldset><br>
				<input type="submit" value="Invia" class="simple"/>
			</form>
		</div>
	</section>
	
	
</body>
</html>