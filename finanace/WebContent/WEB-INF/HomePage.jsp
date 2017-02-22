<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" session="false"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home Page</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css"/>

</head>
<body>
	<img src="/Finance/images/Experian_logo.png"/>
	<form>
	<div class="container">
		<div>
			<h3 class="heading">Please Select Option</h3>
		</div>
		<div><div class="buttondiv">
			<input type="button" onclick="javascript:window.location='DisplayContract.jsp'" 
			id="searchContract" name="Search_Contract" value="Search Contract">
		</div>
		</div><br/>
		<div><div class="buttondiv">
			<input type="button" onclick="javascript:window.location='DisplaySlab.jsp'" id="searchSlab" name="Search_Slab" value="Search Slab">
		</div>
		</div><br/>
		<div><div class="buttondiv">
			<input type="button" onclick="javascript:window.location='MeterInput.jsp'" id="inputMeter" name="inputMeter" value="Meter Reading Processing">
		</div>
		</div><br/>
		<div>
		<div class="buttondiv">
			<input type="button" value="Back" onclick="history.go(-1);"/></div>
		</div>
	</div>
		
	</form>
	<jsp:include page="footer.jsp"/>
</body>
</html>