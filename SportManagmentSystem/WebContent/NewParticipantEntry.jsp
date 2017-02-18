<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<%-- <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/jquery-ui.structure.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/jquery-ui.theme.css">

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/validationFile.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui.js"></script> 
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.currency.js"></script>  --%>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/material.min.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/font/material-icons.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/material.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/validationFile.js"></script> 
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui.js"></script> 
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.currency.js"></script> 



<script type="text/javascript">
$(document).ready(function(){
	$("#gameDiv").hide();
	$("#txtDateOfBirth").change(function(){
		if($(this).val()!=undefined ||$(this).val()!=null){
		var start = $('#txtDateOfBirth').val();
     	
	 	var date = new Date(start);
     	var d = date.getDate();
	 	var m = date.getMonth();
     	var y = date.getFullYear();
	 	
     	var now = new Date();
	 	
		var age = now.getFullYear() - y;
		var mdif = now.getMonth() - m + 1; //0=jan	
		
		if(mdif < 0)
		{
			--age;
		}
		else if(mdif == 0)
		{
			var ddif = now.getDate() - d;
			
			if(ddif < 0)
			{
				--age;
			}
		}
	 	
	 	$('#txtAge').val(age);
     }
	 
	});
	
	$(".dateTimePicker1").datepicker({
	    showOn: "button",
	    buttonImage: "css/images/calendar.png",
	    buttonImageOnly: true,
	    buttonText: "",
	    dateFormat:"dd-M-yy", 
	    changeMonth : true,
           changeYear : true,
           yearRange: '-100y:c+nn',
           maxDate: '-1d'
	  });
	
	 $("#gameBtn").click(function(){ 
		$("#gameDiv").show();
		if(validationParticipant()){
		var jsonData={
				"txtFName":$("#txtFName").val(),
				"txtMName":$("#txtMName").val(),
				"txtLName":$("#txtLName").val(),
				"txtDateOfBirth":$("#txtDateOfBirth").val(),
				"txtEmailId":$("#txtEmailId").val(),
				"txtPhone":$("#txtPhone").val(),
				"txtEmerPhone":$("#txtEmerPhone").val(),
				"txtAdd1":$("#txtAdd1").val(),
				"txtAdd2":$("#txtAdd2").val(),
				"txtCity":$("#txtCity").val(),
				"txtState":$("#txtState").val(),
				"txtPincode":$("#txtPincode").val(),
				"txtSch":$("#txtSch").val(),
				"txtSchAdd1":$("#txtSchAdd1").val(),
				"txtSchAdd2":$("#txtSchAdd2").val(),
				"txtSchCity":$("#txtSchCity").val(),
				"txtSchState":$("#txtSchState").val(),
				"txtSchPincode":$("#txtSchPincode").val(),
				"txtUserName":$("#txtUserName").val(),
				"txtAge":$('#txtAge').val(),
				"selectGender":$('#selectGender').val()
			};
		$.ajax({
			type: "post",
		       url:"/SportManagmentSystem/newParticipant",
		       data:{dataGame:JSON.stringify(jsonData)},   
		       datatype:"json",
		       success:function(data, textStatus, jqXHR)
	            {
		    	   $("#gameEvent").empty();
		    	   var e=jQuery.parseJSON(data);
		    	   console.log(e);
		    	   if(e!=undefined && e!=null && e!="" ){
		    		   $.each(e, function(key, value){
		    			   console.log(e[key]);
		    			   if(e[key]!=undefined && e[key]!=null && e[key]!="" ){
		    				   $.each(e[key], function(key, value){
		    					   console.log(key+" : "+value);
		    					   $("#gameEvent").append("<h5>"+key+"</h5>");
		    					   $.each(value, function(key1, value1){
		    						   console.log(key1+" :: "+value1);
		    						   $("#gameEvent").append("<input type='checkbox' name='sport' id='"+key1+"' value='"+value1+"'/>"+value1+"<br/>");
		    					   });
		    				   });
		    				   
		    			   }
		    		   });
		    	   }
	            },
				error:function(xhr,status)
				{
	        		console.log(status);
	 			}
	 		});
		 }else{
			 $("#gameDiv").hide();
			 alert("Errors : Please Fill the Required Fields");
		 }
		});
	
	$("#addBtn").click(function(){
		var dataToSend =[];
		var data = {};
		$.each($("input[type='checkbox']:checked"),function(){
			
	        data[$(this).attr('id')] =$(this).val();
	        dataToSend.push(data);
	        console.log(dataToSend);
		});
		
		$.ajax({
		       type: "post",
		       url:"/SportManagmentSystem/newParticipant",
		       data:{dataSelected:JSON.stringify(dataToSend)},       			       
		       datatype:"json",
		       success:function(data, textStatus, jqXHR)
	            {
		    	   var e=jQuery.parseJSON(data);
		    	   console.log(e);
		    	   alert("Your Participant ID is : "+e.partID);  
		    	   document.getElementById("cform").reset();
	            }
		});
		
	});
	

});
</script>

<title>New Participant Entry</title>
</head>
<body>
	<form method="post" id="cform">
		<div class="mdl-dialog__content">
				<h5>PERSONAL INFORMATION</h5>
							<div class="mdl-grid">
							<div class="mdl-cell mdl-cell--4-col">
								<div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
									<input type="text" name="txtFName" id="txtFName" class="mdl-textfield__input"/>
									<label class="mdl-textfield__label" for="txtFName">First name</label>
								</div>
							</div>	
							<div class="mdl-cell mdl-cell--4-col">
								<div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
									<input type="text" name="txtMName" id="txtMName" class="mdl-textfield__input"/>
									<label class="mdl-textfield__label" for="txtMName">Middle name</label>
								</div>
							</div>
							<div class="mdl-cell mdl-cell--4-col">
								<div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
									<input type="text" name="txtLName" id="txtLName" class="mdl-textfield__input"/>
									<label class="mdl-textfield__label" for="txtLName">Surname</label>
								</div>
							</div>	
							<div class="mdl-cell mdl-cell--4-col">
								<div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
									<span class="mdl-textfield__label">Date</span>
									<input type="text" class="dateTimePicker1 mdl-textfield__input" name="txtDateOfBirth" id="txtDateOfBirth" />
								</div>
							</div>
							<div class="mdl-cell mdl-cell--4-col">
								<div class="mdl-textfield mdl-js-textfield">
									<label class="mdl-textfield__label" for="mdl-textfield__input"></label>
									<input type="text" name="txtAge" id="txtAge" disabled="disabled" class="mdl-textfield__input"/>
									
								</div>
							</div>
							<div class="mdl-cell mdl-cell--4-col">
								<div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
								<select id="selectGender" name="selectGender" class="mdl-textfield__input" >
									<option value=""></option>
									<option value="G">Girl</option>
									<option value="B">Boy</option>
								</select>
								 <label class="mdl-textfield__label" for="selectGender">Gender</label>
							</div>
							</div>	
							<div class="mdl-cell mdl-cell--4-col">
								<div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
									<input type="text" name="txtEmailId" id="txtEmailId" class="mdl-textfield__input"/>
									<label class="mdl-textfield__label" for="txtEmailId">Email-Id</label>
								</div>
							</div>		
							<div class="mdl-cell mdl-cell--4-col">
								<div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
									<input type="text" name="txtPhone" id="txtPhone" class="mdl-textfield__input"/>
									<label class="mdl-textfield__label" for="txtPhone">Phone</label>
								</div>
							</div>	
							<div class="mdl-cell mdl-cell--4-col">
								<div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
									<input type="text" name="txtEmerPhone" id="txtEmerPhone" class="mdl-textfield__input"/>
									<label class="mdl-textfield__label" for="txtEmerPhone">Emergency Phone</label>
								</div>
							</div>		
		 </div>
		 <h5>ADDRESS</h5>		
		 <div class="mdl-grid">
					<div class="mdl-cell mdl-cell--4-col">
						<div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
							<input type="text" name="txtAdd1" id="txtAdd1" class="mdl-textfield__input"/>
							<label class="mdl-textfield__label" for="txtAdd1">Address Line 1</label>
						</div>
					</div>		
					<div class="mdl-cell mdl-cell--4-col">
						<div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
							<input type="text" name="txtAdd2" id="txtAdd2" class="mdl-textfield__input"/>
							<label class="mdl-textfield__label" for="txtAdd2">Address Line 2</label>
						</div>
					</div>	
					<div class="mdl-cell mdl-cell--4-col">
						<div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
							<input type="text" name="txtCity" id="txtCity" class="mdl-textfield__input"/>
							<label class="mdl-textfield__label" for="txtCity">City</label>
						</div>
					</div>		
					<div class="mdl-cell mdl-cell--4-col">
						<div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
							<input type="text" name="txtState" id="txtState" class="mdl-textfield__input"/>
							<label class="mdl-textfield__label" for="txtState">State</label>
						</div>
					</div>		
					<div class="mdl-cell mdl-cell--4-col">
						<div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
							<input type="text" name="txtPincode" id="txtPincode" class="mdl-textfield__input"/>
							<label class="mdl-textfield__label" for="txtPincode">Pincode</label>
						</div>
					</div>	
		</div>
		<h5>SCHOOL ADDRESS</h5>
		<div class="mdl-grid">
					<div class="mdl-cell mdl-cell--4-col">
						<div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
							<input type="text" name="txtSch" id="txtSch" class="mdl-textfield__input"/>
							<label class="mdl-textfield__label" for="txtSch">School Name</label>
						</div>
					</div>		
					<div class="mdl-cell mdl-cell--4-col">
						<div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
							<input type="text" name="txtSchAdd1" id="txtSchAdd1" class="mdl-textfield__input"/>
							<label class="mdl-textfield__label" for="txtSchAdd1">School Address Line 1</label>
						</div>
					</div>	
					<div class="mdl-cell mdl-cell--4-col">
						<div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
							<input type="text" name="txtSchAdd2" id="txtSchAdd2" class="mdl-textfield__input"/>
							<label class="mdl-textfield__label" for="txtSchAdd2">School Address Line 2</label>
						</div>
					</div>	
					<div class="mdl-cell mdl-cell--4-col">
						<div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
							<input type="text" name="txtSchCity" id="txtSchCity" class="mdl-textfield__input"/>
							<label class="mdl-textfield__label" for="txtSchCity">School City</label>
						</div>
					</div>
					<!-- </td>
					<td> -->
					<div class="mdl-cell mdl-cell--4-col">
						<div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
							<input type="text" name="txtSchState" id="txtSchState" class="mdl-textfield__input"/>
							<label class="mdl-textfield__label" for="txtSchState">School State</label>
						</div>
					</div>		
					<div class="mdl-cell mdl-cell--4-col">
						<div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
							<input type="text" name="txtSchPincode" id="txtSchPincode" class="mdl-textfield__input"/>
							<label class="mdl-textfield__label" for="txtSchPincode">School Pincode</label>
						</div>
					</div>
		</div>
		<div class="mdl-grid">
					<div class="mdl-cell mdl-cell--4-col">
						<div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
							<input type="text" name="txtUserName" id="txtUserName" class="mdl-textfield__input"/>
							<label class="mdl-textfield__label" for="txtUserName">User name</label>
						</div>
					</div>
					<div class="mdl-dialog__actions">
						<button type="button" class="mdl-button mdl-js-button mdl-button--fab mdl-button--mini-fab mdl-js-ripple-effect  mdl-button--accent searching" 
					id="gameBtn" >Next</button>
					
					</div>
		</div>
		<div  id="gameDiv">
			<div class="container">
						<label class="mdl-textfield__label">SELECT GAME NAMES YOU WANT TO PARTICIPATE:</label>
						<div></div><div></div>
						<div id="gameEvent"></div>				
						<div class="mdl-dialog__actions">
						<button type="button" class="mdl-button mdl-js-button mdl-button--fab mdl-button--mini-fab mdl-js-ripple-effect  mdl-button--accent insert" 
					id="addBtn" >
						Add
					</button>
					</div>	
	
			</div>	
		</div>
	
	</form>
</body>
</html>