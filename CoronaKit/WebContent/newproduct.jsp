<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona Kit-Add New Product(Admin)</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<hr/>

	<h3>New Product</h3>
    
    <form action="admin?action=insertproduct" method="post">
        <div>
            <label>ProductId</label>
            <input type="number" name="pid" value="${product.id }" required }/>
        </div>   
        <div>
            <label>Product Name</label>
            <input type="text" name="pname" value="${product.productName }" required />
        </div>    
        <div>
            <label>Product Description</label>
            <input type="text" name="pdesc" value="${product.productDescription }" required />
        </div>    
        <div>
            <label>Cost</label>
            <input type="decimal" name="pcost" value="${product.cost }" required />
        </div>      
        <button>ADD</button>        
    </form>

<hr/>	
	<jsp:include page="footer.jsp"/>
</body>
</html>