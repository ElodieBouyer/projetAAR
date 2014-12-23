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

<title>Gestion Production - Service assemblage</title>
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
		<h1 class="banner-head">Service assemblage</h1>
	</div>

	<c:if test="${!empty message }">
		<c:out value="${message }"></c:out>
	</c:if>
	<center>
	<table class="pure-table pure-table-horizontal pure-table-striped">
		<thead>
			<tr>
				<th>Produit manquant</th>
				<th>Quantité nécessaire</th>
				<th>Frabriquer</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${missing}" var="missingProduct">
				<tr>
					<td><c:out value="${missingProduct.getProduct().getName()}"></c:out></td>
					<td><c:out value="${missingProduct.getQuantity()}"></c:out></td>
					<td>
						<form action="?action=makeProduct" method="post">
							<input name="productId" type="hidden" value="<c:out value="${missingProduct.getProduct().getIdProduct()}"></c:out>"/>
							<input name="quantity" type="number" style="width:150px;" min="0" max="<c:out value="${missingProduct.getQuantity()}"></c:out>" placeholder="Quantité (ex: 5)"/>
							<input type="submit" class="pure-button" value="Fabriquer"/>
						</form>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	</center>
</body>
</html>