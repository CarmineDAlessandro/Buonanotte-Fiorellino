<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"  import="bean.Utente"%>
    
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
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>fiorazon</title>
</head>
<body>
dati anagrafici utente
<form action="EliminazioneUtente.jsp" name="eli"><input type="submit" value="elimina utente" class="button"></form><br>
<form action="ModificaUtente1" name="eli"><input type="submit" value="modifica dati" class="button"></form>
<section>
		<div >Ecco i dati da te inseriti all'atto della registrazione:</div>
		<ul >
			
			<li>ruolo: <%= usr.getRuolo() %> 
			<li>Nome: <%= usr.getNome() %>
			<li>Cognome: <%= usr.getCognome() %>
			<li>e-Mail: <%= usr.geteMail() %>
			<li>codice fiscale: <%= usr.getCodiceFiscale() %>
			<li>data nascita: <%= usr.getDataNascita() %>
			<li>citta nascita: <%= usr.getCittaNascita() %>
			<li>citta residenza: <%= usr.getCittaResidenza() %>
			<li>via: <%= usr.getVia() %>
			<li>numero civico: <%= usr.getNumeroCivico() %>
			<li>cap: <%= usr.getCap() %>
			<li>username: <%= usr.getUsername() %>
			<li>password:  <%= "**********"%>
		</ul>
	</section>

<br><br>
 <a href="Home.jsp">home</a><br><br>
</body>
</html>