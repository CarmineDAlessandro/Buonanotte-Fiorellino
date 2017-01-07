<!DOCTYPE html>
<head>
<link rel="stylesheet" href="css/HomePage.css" type="text/css">
</head>
<body>
	<div id="content-home-page"></div>

	<script>
		var n = 0;
		var div = document.getElementById("content-home-page");

		setInterval('cambia()', 5000);
		cambia();
		function cambia() {

			n++;
			switch (n) {
			case 1:
				div.style.background = "url('images/simbolo.jpg') no-repeat";
				break;
			case 2:
				div.style.background = "url('images/bouquet.png') no-repeat";
				break;
			case 3:
				div.style.background = "url('images/rosa.jpg') no-repeat";
				break;
			case 4:
				div.style.background = "url('images/ciclamino.jpg') no-repeat";
				break;
			case 5:
				div.style.background = "url('images/boccaLeone.jpg') no-repeat";
				break;
			case 6:
				div.style.background = "url('images/gelsomino.jpg') no-repeat";
				n = 0
				break;
			default:
				n = 0;
			}

			div.style.backgroundSize = "100% 100%";
		}
	</script>

	
	
	<audio autoplay loop>
		<source src="sound/degregori.mp3" />
		Il tuo browser non supporta l'audio
	</audio>
</body>