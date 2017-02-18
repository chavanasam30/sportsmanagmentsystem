<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css"/>



<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.8.1.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui.js"></script> 
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.currency.js"></script>

<script type="text/javascript">

</script>
</head>
<body>
	<form action="/Finance/uploadFile" method="post" enctype="multipart/form-data" >
		<div class="container">
		<div style="margin-top: 15px">
		<div>
			<div class="labels">Select A file to upload :</div>
			<div class=""><input type="file" name="file"/></div>
		</div>
		<div>
		<div class="chkLabel radioLabel">
				<input type="radio" name="meterInput" rel="1" id="register" value="Register"/>Register The Meter Input</div>
			<div class="chkLabel radioLabel">
				<input type="radio" name="meterInput" rel="2" id="notRegister" value="Cancel"/>Do not Register The Meter Input</div>
		</div>		
		<div class="buttondiv"><input type="submit" value="Submit" id="submitBtn" style="float: inherit;"/></div>
		</div>	

	<c:choose>
		<c:when test="${requestScope.flag eq 'true' }">
			<c:if test="${requestScope.fieldValue == 'Cancel'}">
				<font color='green' style='bold'><c:out value="File Processing completed. But Not register."/></font>
			</c:if>
			<c:if test="${requestScope.fieldValue == 'Register'}">
				<font color='green' style='bold'><c:out value="File Processing completed and register."/></font>
			</c:if>
			<a href="${requestScope.url}">Download file</a>
		</c:when>
		<c:otherwise>
			 <c:if test = "${requestScope.flag eq 'false'}">
			   <font color='red' style='bold'><c:out value="${requestScope.Error}"></c:out></font>
			</c:if>
		</c:otherwise>
	</c:choose>
	</div>
</form>
</body>
</html>