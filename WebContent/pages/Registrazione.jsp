
<head>
<link href="css/Registrazione.css" type="text/css" rel="stylesheet">

</head>
<body>
<div class="registrazione">
	<h1>Sei un nuovo cliente e cerchi i migliori fiori in circolazione?</h1>
	<h3>Questo è il posto che fa per te!</h3>
	<form method="post" action="ControlloRegistrazioneServlet">
	<input type="hidden" name="action" value ="studente">
		<table>
			<tr>
				<td>Nome</td>
				<td><input type="text" name="nome" placeholder="Mario" required></td>
			</tr>
			<tr>
				<td>Cognome</td>
				<td><input type="text" name="cognome" placeholder="Rossi" required></td>
			</tr>
			<tr>
			<tr>
				<td>E-mail</td>
				<td><input type="text" name="email"
					placeholder="MarioRossi@gmail.com" required></td>
			</tr>
			<tr>
				<td>Codice fiscale</td>
				<td><input type="text" name="cf"
					placeholder="xxxxxxxxxxxxxxxx" required></td>
			</tr>
			<tr>
				<td>Data di nascita</td>
				<td><input type="date" name="data" required placeholder="gg/mm/aaaa" required></td>
			</tr>
			<tr>
				<td>Città di nascita</td>
				<td><input type="text" name="cittàN" placeholder="Roma" required></td>
			</tr>
			<tr>
				<td>Città di residenza</td>
				<td><input type="text" name="cittàR" placeholder="Roma" required></td>
			</tr>
			<tr>
				<td>Provincia:</td>
				<td><input type="text" name="provincia" placeholder="SA" required> 
			</tr>
			
			
			<tr>
				<td>Via</td>
				<td><input type="text" name="via" placeholder="delle fontante" required></td>
			</tr>
	
			<tr>
				<td>Numero civico</td>
				<td><input type="text" name="numeroCivico" placeholder="1" required></td>
			</tr>
			<tr>
				<td>Username</td>
				<td><input type="text" name="username" value="Mario12" required></td>
			</tr>
			<tr>
				<td>CAP</td>
				<td><input type="text" name="cap" value="84013" required></td>
			</tr>
			<tr>
				<td>Password</td>
				<td><input type="password" name="password" value="carminoso" required></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="Registrati!"></td>
			</tr>
		</table>
	</form>
</div>
	
</body>
