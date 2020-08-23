<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona Kit-My Kit(user)</title>
</head>
<body>

	<h3>Selected Items</h3>
	<form action="user?action=placeorder" method="post">
		<c:choose>
			<c:when test="${items == null || items.isEmpty() }">
				<p>No Iems Added Yet in Corona Kit.</p>
			</c:when>
			<c:otherwise>

				<table border="1" cellspacing="5px" cellpadding="5px">
					<tr>
						<th>Product#</th>
						<th>Product Name</th>
						<th>Quantity</th>
					</tr>
					<c:forEach items="${items }" var="item">
						<tr>
							<td>${item.id }</td>
							<td>${item.productName }</td>
							<td><input type="integer" name="quantity"></td>
							<td><input type="hidden" name="itemid" value="${item.id }"></td>
						</tr>
					</c:forEach>
				</table>
				<button>Place Order</button>

			</c:otherwise>
		</c:choose>
	</form>
</body>
</html>