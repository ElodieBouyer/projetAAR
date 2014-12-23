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

<title>Gestion Production - Service Commercial</title>
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
		<h1 class="banner-head">Service Commercial</h1>
	</div>

	<c:if test="${!empty message }">
		<c:out value="${message }"></c:out>
	</c:if>

	<div class="splash-container">
		<div>
			<form class="pure-form pure-form-aligned" action="?action=saveOrder"
				method="post">
				<fieldset>
					<input type="hidden" name="orderId" value="${orderId}">

					<legend>Nouvelle commande</legend>

					<div class="pure-control-group">
						<label for="order-state"> Etat : </label> <label id="order-state">
							${orderState} </label>
					</div>

					<div class="pure-control-group">
						<label for="order-owner">Propriétaire :</label> <label
							id="order-owner"> ${orderOwner}</label>
					</div>

					<div class="pure-control-group">
						<label for="product-list"> Produits à commander :</label>
						<c:if test="${empty productToOrder}">
							<label id="product-list"> Aucun produit sélectionné...</label>
						</c:if>
						<c:if test="${not empty productToOrder}">
							<ul>
								<c:forEach var="detailOrder" items="${productToOrder}">
									<label for="product-quantity">
										${detailOrder.product.name} </label>
									<label id="product-quantity"> Quantité :
										${detailOrder.quantity} </label>
									<br />
								</c:forEach>
							</ul>
						</c:if>
					</div>

					<button type="submit" class="pure-button">Enregistrer</button>
				</fieldset>
			</form>
		</div>
		<div class="splash-container">
			<form action="?action=addProduct" method="post"
				class="pure-form pure-form-aligned">
				<fieldset>

					<legend> Ajouter un produit à la commande </legend>

					<input type="hidden" name="orderId" value="${orderId}">

					<div class="pure-control-group">
						<label for="order-product">Nouveau produit :</label> <select
							id="order-product" name="name" required>
							<c:forEach var="name" items="${allProduct}">
								<option>${name}</option>
							</c:forEach>
						</select> <label for="product-quantity">Quantité :</label> <input
							id="product-quantity" name="quantity" type=number min=0 required>
						<button type="submit" class="pure-button">Ajouter</button>

					</div>
				</fieldset>
			</form>
		</div>
	</div>

</body>
</html>