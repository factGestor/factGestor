<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html>

<head>
<title>Sistema de Gestión de Facturas</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
    <link rel="stylesheet" type="text/css" href="css/logueado.css" />
<meta charset="utf-8">
<link href="<c:url value='favicon.ico'/>" rel="shortcut icon" type="image/x-icon" />
</head>

<body>
    <header><img src="images/taxy.png" alt="Taxy"></header>
	<div id="mensajeInfo">
		<h2><c:out value="${info}" /></h2>
	</div>
	<div class="login">
        <div class="datos">
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
				</ul>
				</div>
        <aside class="sidebar">
				<h3 class="select">Seleccione una opción</h3>
				<nav role="navigation">
					<ul>
					<li>
						<a href="cambiarPassword.html" class="design-name">Cambiar password</a> 
					</li>
                        <li>
						<a href="cambiarEmail.html" class="design-name">Cambiar email</a>		</li>
                        <li>
						<a href="cambiarCuentaBancaria.html" class="design-name">Cambiar cuenta bancaria</a>
					</li>
                        <li>
						<a href="cambiarCondicionesContratacion.html" class="design-name">Cambiar condiciones de contratación
					</li>					<li>
						<a href="/listarDominios" class="design-name">Añadir/ Eliminar dominios a su cuenta</a>
					</li>					</ul>
				</nav>
		<br/>
        </aside>
        <div id="hidden"></div>
			</c:when>
		<c:otherwise>
Verifique su cuenta mediante el correo de validacion enviado al correo que puso durante el registro.
</c:otherwise>
		</c:choose>
		<hr />
	</div>
</body>
</html>