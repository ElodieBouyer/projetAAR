<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" href="css/pure-min.css">
<link rel="stylesheet" href="css/splash.css">

<title>Connection</title>

</head>

<body>

	<div class="header">
		<div
			class="home-menu pure-menu pure-menu-open pure-menu-horizontal pure-menu-fixed">
			<a class="pure-menu-heading" href="?action=home">Gestion
				Production</a>
			<ul>
				<li><a href="?action=signin">S'inscrire</a></li>
			</ul>
		</div>
	</div>


	<div class="splash-container">
		<div class="splash">
			<form action="?action=home" method="post" class="pure-form">
				<fieldset>
					<legend> Connectez-vous </legend>
					<input type="hidden" name="type" value="login" /> <input
						type="text" name="login" placeholder="Login" /> <input
						type="password" name="password" placeholder="Password" /> <input
						type="submit" value="Me connecter"
						class="pure-button " />
				</fieldset>
			</form>
		</div>
	</div>


</body>
</html>