<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona Kit-All Products(Admin)</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<hr/>

	<c:choose>
        <c:when test="${products == null || products.isEmpty() }">
            <p>No Products Available</p>
        </c:when>
        <c:otherwise>
        	<h3>List of Products</h3>
			<c:if test="${msg != null }">
				<p>
					<strong>${msg }</strong>
				</p>
			</c:if>
			<table border="1" cellspacing="5px" cellpadding="5px">
                <tr>
                    <th>Product#</th>
                    <th>Product Name</th>
                    <th>Description</th>
                    <th>Cost</th>
                </tr>
                <c:forEach items="${products }" var="product">
                    <tr>
                        <td>${product.id }</td>
                        <td>${product.productName }</td>
                        <td>${product.productDescription }</td>
                        <td>${product.cost }</td>
                        <td>
                            <a href="admin?id=${product.id }&action=deleteproduct">DELETE</a>
                            <span>|</span>
                            <a href="admin?id=${product.id }&action=editproduct">EDIT</a>
                        </td>
                    </tr> 
                </c:forEach>
            </table>
        </c:otherwise>
    </c:choose>
    <br/>
    <br/>
	<form action="admin?action=newproduct" method="post">
    	<button>Add New Product</button>
    </form>
<hr/>	
	<jsp:include page="footer.jsp"/>
</body>
</html>