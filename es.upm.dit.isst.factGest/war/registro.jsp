<%@ page language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>

<head>
<meta charset="utf-8">
<link href="<c:url value='favicon.ico'/>" rel="shortcut icon"
	type="image/x-icon" />
</head>
<body>

	<div id="mensajeError">
		<h3><c:out value="${error}" /></h3>
	</div>
	<%@ include file='registro.html'%>

</body>

</html>