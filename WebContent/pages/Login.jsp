<head>
<link href="css/Login.css" type="text/css" rel="stylesheet">

</head>
<body>


	<%if (session.getAttribute("utente") == null && session.getAttribute("amministratore") == null) { %>
	
	<div id="login-page">
		<div class="log-user">
			<h1>Sei già registrato?</h1>
			<h3>Se sei un utente, accedi qui</h3>
			<form action="ControlloLoginUtenteServlet" method="post">
			<input type="hidden" name ="utente" value="utente">
			
				<table>
					<tr>
						<td>User:</td>
						<td><input type="text" name="user" /></td>

					</tr>
					<tr>
						<td>Password:</td>
						<td><input type="password" name="password" /></td>
						
						
					</tr>
				
					<tr>
					
						<td><input type="submit"></td>
					</tr>
				</table>
			</form>
		</div>
		<div class="new-user">
			<h1>Sei un nuovo cliente?</h1>
			<h3>
				<a href="index.jsp?IdPage=2">Registrati qui</a>
			</h3>
		</div>
		<div class="log-user">
			<h1>Sei l'amministratore?</h1>
			<h3>Se sei un amministratore, accedi qui</h3>
			<form action="ControlloLoginAmministratoreServlet" method="post">
			<input type="hidden" name ="amministratore" value="amministratore">
			
				<table>
					<tr>
						<td>User:</td>
						<td><input type="text" name="user" /></td>

					</tr>
					<tr>
						<td>Password:</td>
						<td><input type="password" name="password" /></td>

					</tr>
					<tr>
						<td><input type="submit"></td>
					</tr>
				</table>
			</form>
		</div>
		
	</div>
	
	<%
		} else if (session.getAttribute("utente") != null) {
	%>
	<%@include file="Utente.jsp" %> 
	
	<% } else if (session.getAttribute("amministratore") != null) {
	%>
	<%@include file="Amministratore.jsp" %> 
	<% } %>
	
	<% if(response.getStatus() == HttpServletResponse.SC_UNAUTHORIZED) { %>
			<script>
				alert("Username e/o password errati.");
			</script>
			<% } %>
	<% if(response.getStatus() == HttpServletResponse.SC_ACCEPTED) { %>
			<script>
				alert("Modifica avvenuta con successo. La preghiamo di effettuare nuovamente il login");
			</script>
			<% } %>
	
	
</body>