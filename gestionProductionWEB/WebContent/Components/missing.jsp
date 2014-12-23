<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" href="css/pure-min.css">
<link rel="stylesheet" href="css/splash.css">
<link rel="stylesheet" href="css/banner.css">

<title>Gestion Production - Service Gestion des Stocks</title>
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
		<h1 class="banner-head">Service Gestion des Stocks</h1>
	</div>

	<c:if test="${!empty message }">
		<c:out value="${message }"></c:out>
	</c:if>
	<center>
		<table class="pure-table pure-table-horizontal">
		<thead>
			<tr><th>Composant</th><th>Seuil d'alerte</th><th>Quantité</th><th>Ajouter</th></tr>
			
		<c:forEach items="${components}" var="component">
			<c:if test="${component.getMinimalQuantity() > component.getQuantity()}">
				<tr style="background-color:#FA5858;">
			</c:if>
			<c:if test="${component.getMinimalQuantity() == component.getQuantity()}">
				<tr style="background-color:#F6CECE;">
			</c:if>
			<c:if test="${component.getMinimalQuantity() < component.getQuantity()}">
				<tr>
			</c:if>
				<td><c:out value="${component.getName()}"></c:out></td>
				<td><c:out value="${component.getMinimalQuantity()}"></c:out></td>
				<td><c:out value="${component.getQuantity()}"></c:out></td>
				<td>
					<form action="?action=newComponent" method="post">
						<input type="hidden" name="id" value="<c:out value="${component.getIdComponent()}"></c:out>"/>
						<input type="number" min=0 name="quantity"/>
						<input type="submit" class="pure-button" value="Valider"/>
					</form>
				</td>
			</tr>
		</c:forEach>
		</table>
	</center>
</body>
</html>