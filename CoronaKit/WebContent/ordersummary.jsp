<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona Kit-Order Summary(user)</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	<hr />
	<h3>Order Summary</h3>
	<div>
		<p>
			<strong>Name:${coronakit.personName}</strong>
		</p>
	</div>
	<div>
		<p>
			<strong>Email:${coronakit.email}</strong>
		</p>
	</div>
	<div>
		<p>
			<strong>Contact:${coronakit.contactNumber}</strong>
		</p>
	</div>
	<div>
		<p>
			<strong>Delivery Address:${coronakit.deliveryAddress}</strong>
		</p>
	</div>
	<div>
		<p>
			<strong>Order Date:${coronakit.orderDate}</strong>
		</p>
	</div>
	<div>
		<p>
			<strong>Total Amount:${coronakit.totalAmount}</strong>
		</p>
	</div>
	<table border="1" cellspacing="5px" cellpadding="5px">
		<tr>
			<th>Product#</th>
			<th>Product Name</th>
			<th>Quantity</th>
			<th>Amount</th>
		</tr>
		<c:forEach items="${kitItems }" var="item">
			<tr>
				<td>${item.id }</td>
				<td>${item.productName }</td>
				<td>${item.quantity }</td>
				<td>${item.amount }</td>
			</tr>
		</c:forEach>
	</table>
	<hr />
	<jsp:include page="footer.jsp" />
</body>
</html>