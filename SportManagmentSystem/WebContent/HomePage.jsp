<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css"/>
<title>Dervan Sport Event</title>
</head>
<body>
<form>
	<div class="heading">
		<label>Dervan Sport Event</label>
	</div>
	 <div class="table">
	 <table>
	 
	 	<tr>
	 		<td>
	 			<button type="button" onclick="javascript:window.location='NewParticipantEntry.jsp'">
					<img alt="" src="<%=request.getContextPath()%>/images/add-user.png">
				</button>
			</td>
			<td>	
				<button type="button" onclick="javascript:window.location='UpdateParticipant.jsp'">
					<img alt="" src="<%=request.getContextPath()%>/images/add-group-icon.png">
				</button>
	 		</td>
	 	</tr>
	 	<tr></tr>
	 </table> 	
		<%-- <input type="image" id="newParticipant" onclick="javascript:window.location='NewParticipantEntry.jsp'" src="<%=request.getContextPath()%>/images/add_user.png" > --%>
	</div> 
</form>
</body>
</html>