<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<fmt:setLocale value="es-ES"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Principal</title>
</head>
<body>

<ul>
	<c:forEach items="${productos}" var="p">
		<li>${p.nombre}: <fmt:formatNumber type="currency" value="${p.precio}"/>
	</c:forEach>
</ul>

</body>
</html>