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
		<h1 class="banner-head">Service Assemblage</h1>
	</div>

	<c:if test="${!empty message }">
		<c:out value="${message }"></c:out>
	</c:if>
	
	<div class="pure-form">
		<legend>Fabrication de <c:out value="${productManufacturing.getQuantity() }"></c:out> <c:out value="${productManufacturing.getProductName() }"></c:out></legend>
	</div>

		
	<center>
		<table class="pure-table pure-table-horizontal">
			<thead>
				<tr>
					<th>Composant</th>
					<th>Quantité total</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${productManufacturing.getComponentRemaining()}" var="r">
					<tr>
						<td><c:out value="${r.getComponent().getName()}"></c:out></td>
						<td><c:out value="${r.getQuantity()}"></c:out></td>
						<td>
							<form action="?action=takeComponent" method="post">
								<input name="componentId" type="hidden" value="<c:out value="${r.getComponent().getIdComponent()}"></c:out>"/>
								<input name="quantity" type="number" style="width:150px;" min="0" max="<c:out value="${r.getQuantity()}"></c:out>" placeholder="Quantité (ex: 5)"/>
								<input type="submit" class="pure-button" value="Prendre ces composants"/>
							</form>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<a href="?action=validOneProduct" class="pure-button pure-button-primary">Valider un produit.</a>
	</center>
</body>
</html>
