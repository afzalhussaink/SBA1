<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<body>
	<h1>Corona Kit</h1>
	<%
		String servletPath = request.getServletPath();
	%>
	<%
		if ("/listproducts.jsp".equals(servletPath) || "/newproduct.jsp".equals(servletPath)
			|| "/editproduct.jsp".equals(servletPath)) {
	%>
	<a href="admin?action=logout">Logout</a>
	<%
		}
	%>
</body>
</html>