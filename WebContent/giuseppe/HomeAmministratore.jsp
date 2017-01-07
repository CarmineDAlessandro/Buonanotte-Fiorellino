<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%
	String usern = (String) request.getSession().getAttribute("username");
	String ruolo = (String) request.getSession().getAttribute("ruolo");
	
	if(usern == null) {
		response.sendRedirect("./Login.jsp");
		return;
	} else if(!"amministratore".equals(ruolo)) {
		response.sendRedirect("./LoginErrore.jsp");
		return;
	}
%>
   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>fiorazon</title>
</head>
<body>
<h1>home amministratore<br><br></h1>
<div class="log">Benvenuto, <%= usern%>
		<form action="Logout" name="out"><input type="submit" value="Logout" class="button"></form>
		<form action="Anagrafica" name="ang"><input type="submit" value="Anagrafica" class="button"></form>
		<form action="ElencoProdotti" name="elencoPrd"><input type="submit" value="catalogo" class="button"></form>
		<form action="ElencoOrdini" name="elencoord"><input type="submit" value="ordini" class="button"></form>
		</div>
		
<br><br>
 <a href="Home.jsp">home</a><br><br>
  
</body>
</html>