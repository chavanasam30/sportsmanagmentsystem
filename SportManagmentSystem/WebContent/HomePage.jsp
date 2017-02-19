<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Dervar Sport Event</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/material.min.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/font/material-icons.css"/>
<script type="text/javascript" src="js/material.min.js"></script> 
</head>
<body>
<form>
	<div class="heading">
		<label>Dervan Sport Event</label>
	</div>
<div class="demo-card-wide mdl-card mdl-shadow--2dp">
  
  <div class="mdl-card__actions mdl-card--border">
 <span class="mdl-chip mdl-chip--contact" onclick="javascript:window.location='NewParticipantEntry.jsp'">
	<img class="mdl-chip__contact" src="<%=request.getContextPath()%>/images/add-user.png"></img>    
    <span class="mdl-chip__text">New Participant</span>
</span>
   <span class="mdl-chip mdl-chip--contact" onclick="javascript:window.location='TeamEntry.jsp'">
    <img class="mdl-chip__contact" src="<%=request.getContextPath()%>/images/add-group-icon.png"></img>
    <span class="mdl-chip__text">Create Team</span>
</span>
</div>
<div class="mdl-card__actions mdl-card--border">
<span class="mdl-chip mdl-chip--contact" onclick="javascript:window.location='UpdateParticipant.jsp'">
	<img class="mdl-chip__contact" src="<%=request.getContextPath()%>/images/update_user.png"></img>    
    <span class="mdl-chip__text">Update Participant</span>
</span>
</div>  
  
</div>


	
</form>
</body>
</html>