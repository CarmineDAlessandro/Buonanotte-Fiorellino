<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>
    
<!DOCTYPE html>
<html>
<head>
	
	<title>fiorazon</title>
	<link rel="icon" href="./Immagini/">
	<script src="./JavaScript/Registration.js"></script>
	<script src="./JavaScript/Login.js"></script>
</head>
<body>
	<a href="Home.jsp">home</a><br><br>
	
	<section>
		<h2>username già usato</h2>
		<div class="regist">
			<form action="Registration" name="reg" method="post" >
				<fieldset>
					<legend>Dati anagrafici</legend>
					Nome: <input type="text" name="nome" placeholder="nome..." /><br><br>
					Cognome: <input type="text" name="cognome" placeholder="cognome..." /><br><br>
					e-Mail: <input type="text" name="eMail" placeholder="e-mail..." /><br><br>
					Codice Fiscale: <input type="text" name="codiceFiscale" placeholder="codice fiscale..." /><br><br>
					Data di nascita: <input type="text" name="dataNascita" placeholder="gg/mm/aaaa"/><br><br>
					CittaNascita: <input type="text" name="cittaNascita" placeholder="citta di nascita..." /><br><br>
					CittaResidenza: <input type="text" name="cittaResidenza" placeholder="citta di residenza..." /><br><br>
					Via: <input type="text" name="via" placeholder="via..." /><br><br>
					numero Civico: <input type="number" placeholder="civico..." name="numeroCivico" /><br><br>
					CAP: <input type="number" placeholder="CAP..." name="cap" /><br><br>
					
				</fieldset>
				<fieldset>
					<legend>Dati utente</legend>
					Username: <input type="text" name="username" placeholder="username..." /><br><br>
					Password: <input type="password" name="password1" placeholder="password..." /><br><br>
					Reinserisci password: <input type="password" name="password2" placeholder="password..." /><br><br>
				</fieldset><br>
				<input type="submit" value="Invia" class="simple"/>
			</form>
		</div>
	</section>
	
	
</body>
</html>