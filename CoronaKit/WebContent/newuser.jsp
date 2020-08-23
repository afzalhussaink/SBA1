<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona Kit-New User(user)</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<hr/>

	<h3>New User</h3>
    
    <form action="user?action=insertuser" method="post">
    	<div>
            <label>Name</label>
            <input type="text" name="pname" required />
        </div>
        <div>
            <label>Email</label>
            <input type="email" name="pemail" required />
        </div>
        <div>
            <label>Contact</label>
            <input type="tel" name="pcontact" required />
        </div>     
        <button>Proceed to Create Kit</button>        
    </form>

<hr/>	
	<jsp:include page="footer.jsp"/>
</body>
</html>