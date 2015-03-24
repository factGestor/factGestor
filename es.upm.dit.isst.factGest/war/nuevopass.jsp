<%@ page language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html lang="es">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>Introduzca su nueva contraseña</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
    <header><img src="images/taxy.png" alt="Taxy"></header>
	<section class="container">
		<div class="login">
			<form id="formulario" method="post" action="/nuevopassword" align="center">
				<p>
					<p>Introduzca su nuevo password: </p>
                <input name="password"
					type="password" id="password" class="password"
					placeholder="Ej: unicornio33" style="text-align: center"required/ >
                <input type="hidden" name="userId" value=<c:out value="${id}" />>
				</p>
				<p class="submit">
					<input type="submit" id='commit' name="commit" value="Validar pass">
				</p>
			</form>
		</div>
	</section>
</body>

</html>