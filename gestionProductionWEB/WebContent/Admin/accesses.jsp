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

<title>Gestion Production - Service Administration</title>
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
		<h1 class="banner-head">Accès</h1>
	</div>

	<c:if test="${!empty message }">
		<c:out value="${message }"></c:out>
	</c:if>
<div class="pure-g">
<c:forEach items="${roles}" var="elem">
	<div class="pure-u-1-3" style="margin-bottom:10px">
		<center>
			<table class="pure-table pure-table-horizontal pure-table-stripped" style="min-width:200px">
			<thead>
				<tr>
					<th colspan="2"><c:out value="${elem.getName() }"></c:out></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${elem.getAccessList() }" var="subelem">
					<tr>
						<td><c:out value="${subelem.getPageName() }"></c:out></td>
						<td><c:out value="${subelem.getRights() }"></c:out></td>
					</tr>
				</c:forEach>
			</tbody>
			</table>
		</center>
	</div>
</c:forEach>
</div>
</body>
</html>