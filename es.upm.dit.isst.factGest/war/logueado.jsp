<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html>

<head>
<title>Sistema de Gestión de Facturas</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
<meta charset="utf-8">
<link href="<c:url value='favicon.ico'/>" rel="shortcut icon" type="image/x-icon" />
</head>

<body>
	<div class="login">
		<c:choose>
			<c:when test="${u.confirmado == true}">
				<h2 style="font-weight: bold;">
					¡Bienvenido,
					<c:out value="${u.name}" />
					!
				</h2>
				<p>Tus datos son los siguientes:</p>
				<ul>
					<li>ID: <c:out value="${u.id}" /></li>
					<li>Nombre: <c:out value="${u.name}" /></li>
					<li>CIF: <c:out value="${u.CIF}" /></li>
					<li>Email: <c:out value="${u.email}" /></li>
					<li>Confirmado: <c:out value="${u.confirmado}" /></li>
				</ul>
			</c:when>
			<c:otherwise>
Verifique su cuenta mediante el correo de validacion enviado al correo que puso durante el registro.
</c:otherwise>
		</c:choose>
		<hr />
	</div>
</body>
</html>