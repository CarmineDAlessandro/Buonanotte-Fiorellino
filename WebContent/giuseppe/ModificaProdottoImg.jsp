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
	
		<form method="post" action=" " name="echo" enctype="multipart/form-data">
			<fieldset>
			<legend>campo da compilare</legend>
			<br><%= request.getParameter("urlP") %>
			<br><%= request.getParameter("idP") %><br>
			<label>immagine : <input type="file" name="file" size="50" multiple required /></label><br><br>
					
      				</fieldset>
      				<br><br><input type="submit" value="invia" /><br><br>
       </form>					
		
	
</body>
</html>