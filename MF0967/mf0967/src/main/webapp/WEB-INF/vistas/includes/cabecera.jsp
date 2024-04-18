<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt"%>
<fmt:setLocale value="es-ES" />
<!DOCTYPE html>
<html lang="es">
<head>

<base href="${pageContext.request.contextPath}/">

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>MF0967</title>

<link rel="icon" type="image/svg+xml" href="imgs/favicon.svg">

<link rel="stylesheet" href="css/bootstrap-icons.min.css">

<link rel="stylesheet" href="css/bootstrap.min.css">
<script defer src="js/bootstrap.bundle.min.js"></script>

<script defer src="js/jquery-3.7.1.min.js"></script>

<link rel="stylesheet" href="css/dataTables.bootstrap5.css">
<script defer src="js/dataTables.js"></script>
<script defer src="js/dataTables.bootstrap5.js"></script>

<script defer src="js/mf0967.js"></script>

</head>
<body>
	<nav
		class="px-2 navbar navbar-expand-sm bg-dark border-bottom border-body ${error == null ? 'mb-3' : ''}"
		data-bs-theme="dark">
		<div class="container-fluid">
			<a class="navbar-brand" href="#">MF0967</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-sm-0">
					<li class="nav-item"><a class="nav-link" href="index">Principal</a></li>
				</ul>
				<ul class="navbar-nav mb-2 mb-sm-0">
					<c:choose>
						<c:when test="${usuario == null}">
							<li class="nav-item"><a class="nav-link" href="login">Login</a></li>
						</c:when>
						<c:otherwise>
							<li class="nav-item"><a class="nav-link" href="logout">Logout</a></li>
							<li class="nav-item"><a class="nav-link"
								href="admin/productos">Administración</a></li>
							<li class="navbar-text">${usuario}</li>
						</c:otherwise>
					</c:choose>

				</ul>
			</div>
		</div>
	</nav>
	<c:if test="${error != null}">
		<div class="alert alert-danger alert-dismissible fade show mb-3"
			role="alert">
			${error}
			<button type="button" class="btn-close" data-bs-dismiss="alert"
				aria-label="Close"></button>
		</div>
	</c:if>