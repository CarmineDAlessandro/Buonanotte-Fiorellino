<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="bean.Prodotto" %>
    
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
	Prodotto usr = (Prodotto) request.getSession().getAttribute("Dati");
	
%>   
 
<!DOCTYPE html>
<html>
<head>
	<title>fiorazon</title>
	<link rel="icon" href="./Immagini/">
</head>
<body>
	<a href="Home.jsp">home</a><br><br>
	<%String str = usr.getDescrizione();
	str =str.replaceAll(" ", "&nbsp;");%> 
		<form method="post" action="ModificaP" name="echo" >
			<fieldset>
				modifica prodotto 
			<legend>campo da compilare</legend>
			<br>
				<input type="hidden" name="idProdotto" value=<%= usr.getIdProdotto() %>>
					<label>Nome: <input type="text" name="nome" size="40"  value= <%=usr.getNome()  %>/></label><br><br>
					<label>quantita: <input type="text" name="quantita"  value= <%=usr.getQuantita() %> /></label><br><br>
					<label>descrizione: <input type="text" name="descrizione" size="40" value= <%=str %>    /></label><br><br>
					<label>prezzo: <input type="text" name="prezzo"  value= <%=usr.getPrezzo() %> /></label><br><br>
      				</fieldset>
      				<br><br><input type="submit" value="invia" /><br><br>
       </form>			
		
	
</body>
</html>