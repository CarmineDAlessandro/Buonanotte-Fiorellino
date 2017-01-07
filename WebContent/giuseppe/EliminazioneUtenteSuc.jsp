<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%
	String usern = (String) request.getSession().getAttribute("username");
%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>fiorazon</title>
</head>
<body>
<h1>eliminazione utente avvenuto con successo</h1>
 
   <%if(usern != null) { %>
			<div class="log">Benvenuto, <%= usern%>
			<form action="Logout" name="out"><input type="submit" value="Logout" class="button"></form>
			</div>
		<% } else { %>
	<a href="Login.jsp">vuoi autenticarti?</a><br>
 	<a href="Registrazione.jsp">vuoi registrarti?</a><br>
 <% }  %>
 <a href="Home.jsp">home</a><br><br>
 	<form action="Anagrafica" name="ang"><input type="submit" value="Anagrafica" class="button"></form>
</body>
</html>