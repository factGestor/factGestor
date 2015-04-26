<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page isELIgnored="false"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<link href="<c:url value='favicon.ico'/>" rel="shortcut icon"
	type="image/x-icon" />
<link rel="stylesheet" type="text/css" href="css/style.css" />
<title>Operación realizada con éxito</title>
</head>

<body>
    <header><img src="images/taxy.png" alt="Taxy"></header>
	<div class="login">
		<h2 style="font-weight: bold;">¡Operacion realizada con exito!</h2>
		<p>
			Se ha completado su pago. Estos son sus datos:
		<table>
			<tr>
				<th>Concepto</th>
				<th>Valor</th>
			</tr>
				<tr>
					<td>Id transacción:</td>
					<td><c:out value="${id}" /></td>
				</tr>
				<tr>
					<td>Estado:</td>
					<td><c:out value="${estado}" /></td>
				</tr>
				<tr>
					<td>Importe:</td>
					<td><c:out value="${importe}" /></td>
				</tr>
				<tr>
					<td>Tarifa:</td>
					<td><c:out value="${contratado}" /></td>
				</tr>

		</table>
		<a href="https://www.taxy-gest.appspot.com/">
				<button class="boton">Volver</button>
			</a>
	</div>
</body>
</html>