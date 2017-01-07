<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>
    
<%
	String usern = (String) request.getSession().getAttribute("username");
	String ruolo = (String) request.getSession().getAttribute("ruolo");
	
	if(usern != null && ruolo.equals("utente")) {
		response.sendRedirect("HomeUtenteLog.jsp");
		return;
	} else if(usern != null && ruolo.equals("amministratore")) {
		response.sendRedirect("HomeAmministratore.jsp");
		return;
	}
%>

<!DOCTYPE html>
<html>
<head>
	
	<title>fiorazon</title>
	<link rel="icon" href="./Immagini/">
</head>
<body>
	<h1>login utente-amministratore</h1>
	<section>
		<div class="form1">
			<form action="Login" name="log" method="post" >
				<fieldset>
					<legend>Login utente</legend>
					Username:<input type="text" name="username" class="input3" value="" placeholder="username..."/><br>
					Password:&nbsp;<input type="password" name="password" class="input4" value="" placeholder="password..."/><br>
					<input type="submit" value="Login" class="button4">
				</fieldset>
			</form>
		</div>
	</section>
	
</body>
</html>