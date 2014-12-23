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

<title>Gestion Production - Service Livraison</title>
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
		<h1 class="banner-head">Service Livraison</h1>
	</div>

	<c:if test="${!empty message }">
		<c:out value="${message }"></c:out>
	</c:if>

	<div class="splash-container">
		<div>
			<div class="pure-form">
				<legend> Commandes en cours :</legend>

				<c:if test="${empty currentOrders}">
					<i> Il n'y a pas de commande en cours...</i>
				</c:if>

				<c:if test="${not empty currentOrders}">
					<ul>
						<c:forEach var="order" items="${currentOrders}">
							<li class="box-detail"><label class="id-detail"> ○
									Commande numéro ${order.idOrder} </label>

								<div>
									<div>
										<input class="pure-button" type="button"
											value="Afficher les détails"
											onclick="if (this.parentNode.parentNode.getElementsByTagName('div')[1].getElementsByTagName('div')[0].style.display != '') { this.parentNode.parentNode.getElementsByTagName('div')[1].getElementsByTagName('div')[0].style.display = '';        this.innerText = ''; this.value = 'Cacher'; } else { this.parentNode.parentNode.getElementsByTagName('div')[1].getElementsByTagName('div')[0].style.display = 'none'; this.innerText = ''; this.value = 'Afficher'; }" />
									</div>
									<div class="quotecontent">
										<div style="display: none;">
											<table class="pure-table pure-table-horizontal table-detail">
												<thead>
													<tr>
														<th>Propriétaire</th>
														<th>Statut</th>
													</tr>
												</thead>
												<tbody>
													<tr>
														<td>${order.owner.name}</td>
														<td>${order.state}</td>
													<tr>
												</tbody>
											</table>
										</div>
									</div>
								</div></li>
						</c:forEach>
					</ul>
				</c:if>
			</div>

			<div class="pure-form">
				<legend>Commandes prêtes à être envoyées :</legend>

				<c:if test="${empty completedOrders}">
					<i> Il n'y a pas de commande terminée...</i>
				</c:if>

				<c:if test="${not empty completedOrders}">
					<ul>
						<c:forEach var="order" items="${completedOrders}">
							<li class="box-detail"><label class="id-detail"> ○
									Commande numéro ${order.idOrder} </label>

								<div class="id-detail">
									<form action="?action=sent" method="post">
										<input type="hidden" name="idorder" value="${order.idOrder}" />
										<input type="submit" class="pure-button pure-button-primary"
											value="Envoyer la commande" />
									</form>
								</div>

								<div>
									<div>
										<input class="pure-button" type="button"
											value="Afficher les détails"
											onclick="if (this.parentNode.parentNode.getElementsByTagName('div')[1].getElementsByTagName('div')[0].style.display != '') { this.parentNode.parentNode.getElementsByTagName('div')[1].getElementsByTagName('div')[0].style.display = '';        this.innerText = ''; this.value = 'Cacher'; } else { this.parentNode.parentNode.getElementsByTagName('div')[1].getElementsByTagName('div')[0].style.display = 'none'; this.innerText = ''; this.value = 'Afficher'; }" />
									</div>

									<div class="quotecontent send-detail">
										<div style="display: none;">
											<table class="pure-table pure-table-horizontal table-detail">
												<thead>
													<tr>
														<th>Propriétaire</th>
														<th>Statut</th>
													</tr>
												</thead>
												<tbody>
													<tr>
														<td>${order.owner.name}</td>
														<td>${order.state}</td>
													<tr>
												</tbody>
											</table>
										</div>
									</div>

								</div></li>
						</c:forEach>
					</ul>
				</c:if>

			</div>

			<div class="pure-form">
				<legend>Commandes envoyées :</legend>

				<c:if test="${empty sentOrders}">
					<i> Il n'y a pas de commande envoyée...</i>
				</c:if>

				<c:if test="${not empty sentOrders}">
					<ul>
						<c:forEach var="order" items="${sentOrders}">
							<li class="box-detail"><label class="id-detail"> ○
									Commande numéro ${order.idOrder} </label>

								<div>
									<div>
										<input class="pure-button" type="button"
											value="Afficher les détails"
											onclick="if (this.parentNode.parentNode.getElementsByTagName('div')[1].getElementsByTagName('div')[0].style.display != '') { this.parentNode.parentNode.getElementsByTagName('div')[1].getElementsByTagName('div')[0].style.display = '';        this.innerText = ''; this.value = 'Cacher'; } else { this.parentNode.parentNode.getElementsByTagName('div')[1].getElementsByTagName('div')[0].style.display = 'none'; this.innerText = ''; this.value = 'Afficher'; }" />
									</div>
									<div class="quotecontent">
										<div style="display: none;">
											<table class="pure-table pure-table-horizontal table-detail">
												<thead>
													<tr>
														<th>Propriétaire</th>
														<th>Statut</th>
													</tr>
												</thead>
												<tbody>
													<tr>
														<td>${order.owner.name}</td>
														<td>${order.state}</td>
													<tr>
												</tbody>
											</table>
										</div>
									</div>
								</div></li>
						</c:forEach>
					</ul>
				</c:if>

			</div>
		</div>
	</div>

</body>
</html>