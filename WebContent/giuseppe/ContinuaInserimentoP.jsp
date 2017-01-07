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
	<link href="CSS/dropzone.css" type="text/css" rel="stylesheet" />
	<script src="JavaScript/dropzone.js"></script>
</head>
<body>
	<a href="Home.jsp">home</a><br><br>
		<form action="InserisciPImg" class="dropzone" name="uploadimg" method="post" enctype="multipart/form-data" >
		<input type="submit" value="conferma" />
		</form>			
		
	
</body>
</html>