<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona Kit-All Products(user)</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<hr/>
	<form action="user?action=showkit" method="post">
	<c:choose>
        <c:when test="${products == null || products.isEmpty() }">
            <p>No Products Available</p>
        </c:when>
        <c:otherwise>
        	<h3>List of Products</h3>
			
			<table border="1" cellspacing="5px" cellpadding="5px">
                <tr>
                	<th>Select</th>
                    <th>Product#</th>
                    <th>Product Name</th>
                    <th>Description</th>
                    <th>Cost</th>
                </tr>
                <c:forEach items="${products }" var="product">
                    <tr>
                    	<td><input type="checkbox" name="productlist" value="${product.id }"></td>
                        <td>${product.id }</td>
                        <td>${product.productName }</td>
                        <td>${product.productDescription }</td>
                        <td>${product.cost }</td>
                    </tr> 
                </c:forEach>
            </table>
        </c:otherwise>
    </c:choose>
    <br/>
    <br/>
	
    	<button>Add Products to Kit</button>
    	<%-- <jsp:include page="showkit.jsp"/> --%>
    </form>
<hr/>	
	<jsp:include page="footer.jsp"/>
</body>
</html>