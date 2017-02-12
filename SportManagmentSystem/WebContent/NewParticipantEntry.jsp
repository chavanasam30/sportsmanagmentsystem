<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/jquery-ui.structure.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/jquery-ui.theme.css">

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/validationFile.js"></script>
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
	    buttonImage: "images/calender.jpg",
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
	            }
		});
		
	});
	

});
</script>

<title>New Participant Entry</title>
</head>
<body>
	<form method="post">
		<div class="container">
				<div><h3>PERSONAL INFORMATION</h3></div>
					<table>
						<tr>
							<td>
								<div class="labels">FIRST NAME<span class="astrik">*</span> :</div>
							</td>
							<td>
								<div class="labels">MIDDLE NAME<span class="astrik">*</span> :</div>
							</td>
							<td>
								<div class="labels">LAST NAME<span class="astrik">*</span> :</div>
							</td>
						</tr>	
						<tr>
							<td>
								<div><input type="text" name="txtFName" id="txtFName"/></div>
							</td>
							<td>
								<div><input type="text" name="txtMName" id="txtMName"/></div>
							</td>
							<td>
								<div><input type="text" name="txtLName" id="txtLName"/></div>
							</td>
						</tr>	
						<tr>
							<td><div class="labels">DATE OF BIRTH<span class="astrik">*</span> :</div></td>
							<td><div class="labels">AGE :</div></td>
							<td><div class="labels">GENDER<span class="astrik">*</span> :</div></td>
						</tr>
						<tr>
							<td><input type="text" class="dateTimePicker1" name="txtDateOfBirth" id="txtDateOfBirth" /></td>
							<td><input type="text" name="txtAge" id="txtAge" disabled="disabled"/></td>
							<td>
								<select class="dropdown-menu" id="selectGender" name="selectGender" >
									<option value="G">Girl</option>
									<option value="B">Boy</option>
								</select>
							</td>
						</tr> 
						<tr>
							<td><div class="labels">EMAIL ID : </div></td>
							<td><div class="labels">PHONE<span class="astrik">*</span>: </div></td>
							<td><div class="labels">EMERGENCY PHONE<span class="astrik">*</span>: </div></td>                                              
						</tr>
						<tr>
							<td><input type="text" name="txtEmailId" id="txtEmailId"/></td>
							<td><input type="text" name="txtPhone" id="txtPhone"/></td>
							<td><input type="text" name="txtEmerPhone" id="txtEmerPhone"/></td>
						</tr>
				</table>
		 </div>		
		<div class="container">
			<div><h3>ADDRESS</h3></div>
			<table>
				<tr>
					<td><div class="labels">ADDRESS LINE 1<span class="astrik">*</span>: </div></td>
					<td><div class="labels">ADDRESS LINE 2: </div></td>
				</tr>
				<tr>
					<td><input type="text" name="txtAdd1" id="txtAdd1"/></td>
					<td><input type="text" name="txtAdd2" id="txtAdd2"/></td>
				</tr>
				<tr>
					<td><div class="labels">CITY<span class="astrik">*</span>: </div></td>
					<td><div class="labels">STATE<span class="astrik">*</span>: </div></td>
				</tr>
				<tr>
					<td><input type="text" name="txtCity" id="txtCity"/></td>
					<td><input type="text" name="txtState" id="txtState"/></td>
				</tr>
				<tr>
					<td><div class="labels">PINCODE<span class="astrik">*</span>: </div></td>
				</tr>
				<tr>
					<td><input type="text" name="txtPincode" id="txtPincode"/></td>
				</tr>
			</table>
		</div>
		<div class="container">
			<div><h3>SCHOOL ADDRESS</h3></div>
			<table>
				<tr>
					<td><div class="labels">SCHOOL NAME<span class="astrik">*</span>: </div></td>
				</tr>
				<tr>
					<td><input type="text" name="txtSch" id="txtSch"/></td>
				</tr>
				<tr>
					<td><div class="labels">SCHOOL ADDRESS LINE 1<span class="astrik">*</span>: </div></td>
					<td><div class="labels">SCHOOL ADDRESS LINE 2: </div></td>
				</tr>
				<tr>
					<td><input type="text" name="txtSchAdd1" id="txtSchAdd1"/></td>
					<td><input type="text" name="txtSchAdd2" id="txtSchAdd2"/></td>
				</tr>
				<tr>
					<td><div class="labels">SCHOOL CITY<span class="astrik">*</span>: </div></td>
					<td><div class="labels">SCHOOL STATE<span class="astrik">*</span>: </div></td>
				</tr>
				<tr>
					<td><input type="text" name="txtSchCity" id="txtSchCity"/></td>
					<td><input type="text" name="txtSchState" id="txtSchState"/></td>
				</tr>
				<tr>
					<td><div class="labels">SCHOOL PINCODE<span class="astrik">*</span>: </div></td>
				</tr>
				<tr>
					<td><input type="text" name="txtSchPincode" id="txtSchPincode"/></td>
				</tr>
			</table>
		</div>
		<div class="container">
			<table>
				<tr>
					<td><div class="labels">INSERT USER NAME: </div></td>
					<td><input type="text" name="txtUserName" id="txtUserName"/></td>
					<td><div class="buttondiv"><input type="button" value="Next" id="gameBtn" style="float: right;"></div></td>
				</tr>
				
			</table>
		</div>
		<div class="hiddenDiv" id="gameDiv">
			<div class="container">
				
						<div class="labels">SELECT GAME NAMES YOU WANT TO PARTICIPATE: </div>
						<div id="gameEvent"></div>				
						<div class="buttondiv"><input type="button" value="Add" id="addBtn" style="float: right;"></div>	
	
			</div>	
		</div>
	
	</form>
</body>
</html>