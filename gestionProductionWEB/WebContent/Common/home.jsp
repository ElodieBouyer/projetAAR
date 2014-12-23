<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link rel="stylesheet" href="css/pure-min.css">
<link rel="stylesheet" href="css/banner.css">

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Gestion Production</title>
</head>


<body>


	<div class="header">
		<div
			class="home-menu pure-menu pure-menu-open pure-menu-horizontal pure-menu-fixed">
			<a class="pure-menu-heading" href="?action=home"> Gestion
				Production </a>

			<ul>
				<li><a href="?action=disconnect">Déconnexion</a></li>
			</ul>
		</div>
	</div>

	<div class="banner">
		<h1 class="banner-head">
			Bonjour
			<c:out value="${name}"></c:out>
		</h1>
	</div>

	<div id="main-nav" class="pure-g">
		<div class="pure-u-1-5 pure-menu">
			<c:if test="${role == 'Administration' or role == 'Commercial'}">
				<a href="?action=addOrder">Nouvelle commande</a>
			</c:if>
		</div>

		<div class="pure-u-1-5 pure-menu">
			<c:if
				test="${role == 'Administration' or role == 'Service livraison'}">
				<a href="?action=waitingOrders" class="fill-div">Envoyer des
					commandes</a>
			</c:if>
		</div>
		<div class="pure-u-1-5 pure-menu">
			<c:if test="${role == 'Administration' or role == 'Assembleur'}">
				<a href="?action=waitingProducts" class="fill-div">Liste
					produits à assembler</a>
			</c:if>
		</div>
		<div class="pure-u-1-5 pure-menu">
			<c:if
				test="${role == 'Administration' or role == 'Service gestion des stocks'}">
				<a href="?action=missingComponents" class="fill-div">Gérer les stocks</a>
			</c:if>
		</div>
		<div class="pure-u-1-5 pure-menu">
			<c:if test="${role == 'Administration'}">
				<a href="?action=accesses" class="fill-div">Tableau des accès</a>
			</c:if>
		</div>
	</div>
	
<script type="text/javascript">
	var main = document.getElementById('main-nav');
	 
	if(main.getElementsByTagName('a').length == 1){
		document.location.href = main.getElementsByTagName('a')[0].getAttribute('href');
	}
</script>
</body>
</html>