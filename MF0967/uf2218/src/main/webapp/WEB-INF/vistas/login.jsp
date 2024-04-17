<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
	<form action="login" method="post">
		<input name="email" placeholder="email">
		<input name="password" type="password" placeholder="password">
		
		<button>Login</button>
		<div>${error}</div>
	</form>
</body>
</html>