<%@ page language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page isELIgnored="false"%>

<html>

<head>
<meta charset="utf-8">
<link href="<c:url value='favicon.ico'/>" rel="shortcut icon"
	type="image/x-icon" />
</head>

<body>

	<div id="mensajeError">
		<h2><c:out value="${error}" /></h2>
	</div>

	<%@ include file='PIndex.html'%>

</body>
</html>