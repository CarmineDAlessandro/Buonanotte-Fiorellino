<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>
    
<%
	String usern = (String) request.getSession().getAttribute("username");
	String ruolo = (String) request.getSession().getAttribute("ruolo");
	
	if(usern == null) {
		response.sendRedirect("./Login.jsp");
		return;
	} else if(!"amministratore".equals(ruolo)) {
		response.sendRedirect("./Home.jsp");
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
	<a href="Home.jsp">home</a><br><br>
	
		<form method="post" action="InserisciP" name="echo" enctype="multipart/form-data">
			<fieldset>
			<legend>campo da compilare</legend>
			<br>
			<label>immagine : <input type="file" name="file" size="50" multiple required /></label><br><br>
					<label>Nome: <input type="text" name="nome" placeholder="nome..." /></label><br><br>
					<label>quantita: <input type="text" name="quantita" placeholder="quantita..." /></label><br><br>
					<label>descrizione: <input type="text" name="descrizione" placeholder="descrizione..." /></label><br><br>
					<label>prezzo: <input type="text" name="prezzo" placeholder="prezzo..." /></label><br><br>
      				</fieldset>
      				<br><br><input type="submit" value="invia" /><br><br>
       </form>			
		
	
</body>
</html>