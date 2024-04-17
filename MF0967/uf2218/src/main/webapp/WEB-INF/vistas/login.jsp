<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>

<main>
	<form action="login" method="post">
		<input name="email" placeholder="email"> <input
			name="password" type="password" placeholder="password">

		<button>Login</button>
		<div>${error}</div>
	</form>
</main>

<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>