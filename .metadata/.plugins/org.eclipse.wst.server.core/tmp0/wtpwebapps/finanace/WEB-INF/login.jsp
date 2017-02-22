<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login Page</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css">

</head>
<body>
<img src="<%=request.getContextPath()%>/images/Experian_logo.png"/>
<form action="/Finance/loginModel" method="post">
	<div class="container">
	<h3 class="heading">Login Page</h3>
	<center>
		<div class="labels"><span>User Id :</span></div>
		<input type="text" name="username" id="username"><br>
		
		<div class ="labels"><span>Password :</span></div>
		<input type="password" name="password" id="password"><br>
		
		<div class="buttondiv">
		<input type="submit" name="submitBtn" id="submitBtn" value="Submit">
		</div>
	<c:if test = "${requestScope.flag eq 'false'}">
			   <h3><font color='red' style='bold'><c:out value="${requestScope.Error}"></c:out></font></h3>
			</c:if>
	</center>	
	</div>
</form>
<jsp:include page="footer.jsp"/>
</body>
</html>