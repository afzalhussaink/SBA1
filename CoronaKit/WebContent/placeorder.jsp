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
	<jsp:include page="header.jsp" />
	<hr />
	<form action="user?action=saveorder" method="post">
		<table border="1" cellspacing="5px" cellpadding="5px">
			<tr>
				<th>Product Id</th>
				<th>Product Name</th>
				<th>Quantity</th>
				<th>Amount</th>
			</tr>
			<c:forEach items="${kitItems }" var="kitItem">
				<tr>
					<td>${kitItem.productId }</td>
					<td>${kitItem.productName }</td>
					<td>${kitItem.quantity }</td>
					<td>${kitItem.amount }</td>
				</tr>
			</c:forEach>
		</table>
		<br />
		<div>
			<p>
				<strong>Total Amount:${total}</strong>
			</p>
		</div>
		<div>
			<label>Enter Billing Address:</label> <input type="text"
				name="address" required />
		</div>
		<button>SUBMIT</button>
	</form>
	<hr />
	<jsp:include page="footer.jsp" />
</body>
</html>