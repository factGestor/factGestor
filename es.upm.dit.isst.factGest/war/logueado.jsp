<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<title>Todos</title>
<link rel="stylesheet" type="text/css" href="css/main.css" />
<meta charset="utf-8">
</head>

<body>
	<table>
<tr>
<th>ID</th>
<th>Nombre</th>
<th>CIF</th>
<th>Email</th>
</tr>
<tr>
<td><c:out value="${u.id}" /></td>
<td><c:out value="${u.name}" /></td>
<td><c:out value="${u.CIF}" /></td>
<td><c:out value="${u.email}" /></td>
</tr>

</table>

<hr />


</body>
</html>