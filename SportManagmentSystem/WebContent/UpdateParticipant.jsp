<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
	$("#tablediv").hide(); 

	$("#searchBtn").click(function(){
		var jsonData={
				"searchID":$('#searchID').val(),
				"searchFName":$('#searchFName').val(),
				"searchMName":$('#searchMName').val(),
				"searchLName":$('#searchLName').val(),
				"searchPhone":$('#searchPhone').val()
		};
		$.ajax({
		       type: "post",
		       url:"updateParticipant",
		       data:{dataSearch:JSON.stringify(jsonData)},   
		       datatype:"json",
		       success:function(data, textStatus, jqXHR)
	            {
		    	   var optionCat ='';
		    	   $("#participanttable").find("tr:gt(0)").remove();
		    	   var trHTML = '';	
		    	   $('#tablediv').show();
		    	   console.log("submit:"+data);
		    	   console.log("length :"+data.length);
 
		           $.each(data, function(index, participant) {  
		            	trHTML += '<tr><td class=\"td\"id=\"PART_ID\" >' + participant.PART_ID 
		            	+ '</td><td class=\"td\" id=\"FNAME\">' + participant.FNAME 
		            	+ '</td><td class=\"td\" id=\"MNAME\" >' + participant.MNAME 
		            	+ '</td><td class=\"td\" id=\"LNAME\">' + participant.LNAME
		            	+ '</td><td class=\"td\" id=\"DOB\">' + participant.DOB
		            	+ '</td><td class=\"td\" id=\"AGE\" >' + participant.AGE
		            	+ '</td><td class=\"td\" id=\"SCHOOL\" >' + participant.SCHOOL 
		            	+ '</td><td class=\"td\" id=\"ADDRESS_LINE1\">'+ participant.ADDRESS_LINE1
		            	+ '</td><td class=\"td\" id=\"ADDRESS_LINE2\">'+ participant.ADDRESS_LINE2
		            	+ '</td><td class=\"td\" id=\"STATE\">'+ participant.STATE
		            	+ '</td><td class=\"td\" id=\"CITY\">'+ participant.CITY
		            	+ '</td><td class=\"td\"  id=\"PINCODE\">'+ participant.PINCODE
		            	+ '</td><td class=\"td\" id=\"SCHOOL_ADDRESS_LINE1\">'+ participant.SCHOOL_ADDRESS_LINE1 
		            	+ '</td><td class=\"td\" id=\"SCHOOL_ADDRESS_LINE2\">'+ participant.SCHOOL_ADDRESS_LINE2
		            	+ '</td><td class=\"td\" id=\"SCHOOL_STATE\">'+ participant.SCHOOL_STATE
		            	+ '</td><td class=\"td\" id=\"SCHOOL_CITY\">'+ participant.SCHOOL_CITY
		            	+ '</td><td class=\"td\" id=\"SCHOOL_PINCODE\">'+ participant.SCHOOL_PINCODE
		            	+ '</td><td class=\"td\" id=\"GENDER\">'+ participant.GENDER
		            	+ '</td><td class=\"td\" id=\"PHONE\">'+ participant.PHONE
		            	+ '</td><td class=\"td\" id=\"EMER_PHONE\">'+ participant.EMER_PHONE
		            	+ '</td><td class=\"td\" id=\"EMAIL_ID\">'+ participant.EMAIL_ID
		            	+ '</td><td class=\"td\" id=\"INSERT_USER_NAME\">'+ participant.INSERT_USER_NAME
		            	+ '</td><td class=\"td\" id=\"UPDATE_USER_NAME\">'+ participant.UPDATE_USER_NAME
		            	+ '</td><td><img src=\'images/edit.png\' class=\'btnEdit\'/></td></tr>';
		            	console.log(participant);
		            	console.log("index "+index);
		            	$(".btnEdit").live("click",Edit); 
		           
		            	function Edit(){ 
		            		var par = $(this).parent().parent(); //tr 
		            		var tdPartID = par.children("td:nth-child(1)"); 
		            		var tdFname = par.children("td:nth-child(2)"); 
		            		var tdMname = par.children("td:nth-child(3)"); 
		            		var tdLname = par.children("td:nth-child(4)"); 
		            		var tdDOB = par.children("td:nth-child(5)");
		            		var tdAge = par.children("td:nth-child(6)");
		            		var tdSchool = par.children("td:nth-child(7)");
		            		var tdAdd1 = par.children("td:nth-child(8)");
		            		var tdAdd2 = par.children("td:nth-child(9)");
		            		var tdState = par.children("td:nth-child(10)");
		            		var tdCity = par.children("td:nth-child(11)");
		            		var tdPincode = par.children("td:nth-child(12)");
		            		var tdSchAdd1 = par.children("td:nth-child(13)");
		            		var tdSchAdd2 = par.children("td:nth-child(14)");
		            		var tdSchState = par.children("td:nth-child(15)");
		            		var tdSchCity = par.children("td:nth-child(16)");
		            		var tdSchPincode = par.children("td:nth-child(17)");
		            		var tdGender = par.children("td:nth-child(18)");
		            		var tdPhone = par.children("td:nth-child(19)");
		            		var tdEPhone = par.children("td:nth-child(20)");
		            		var tdEmail = par.children("td:nth-child(21)");
		            		var tdInsertUsrName = par.children("td:nth-child(22)");
		            		var tdUpdateUsrName = par.children("td:nth-child(23)");
		            		var tdButtons = par.children("td:nth-child(24)"); 
		            		
		            		
		            		var optionGender='<option value=\"G\">Girl</option>';
		            		optionGender += '<option value=\"B\">Boy</option>';
		            		
		            		
		            		tdFname.html("<input type='text' id='FNAME' value='"+tdFname.html()+"'/>");
		            		tdMname.html("<input type='text' id='MNAME' value='"+tdMname.html()+"'/>");
		            		tdLname.html("<input type='text' id='LNAME' value='"+tdLname.html()+"'/>");
		            		tdSchool.html("<input type='text' id='SCHOOL' value='"+tdSchool.html()+"'/>");
		            		tdAdd1.html("<input type='text' id='ADDRESS_LINE1' value='"+tdAdd1.html()+"'/>");
		            		tdAdd2.html("<input type='text' id='ADDRESS_LINE2' value='"+tdAdd2.html()+"'/>");
		            		tdState.html("<input type='text' id='STATE' value='"+tdState.html()+"'/>");
		            		tdCity.html("<input type='text' id='CITY' value='"+tdCity.html()+"'/>");
		            		tdPincode.html("<input type='text' id='PINCODE' value='"+tdPincode.html()+"'/>");
		            		tdSchAdd1.html("<input type='text' id='SCHOOL_ADDRESS_LINE1' value='"+tdSchAdd1.html()+"'/>");
		            		tdSchAdd2.html("<input type='text' id='SCHOOL_ADDRESS_LINE2' value='"+tdSchAdd2.html()+"'/>");
		            		tdSchState.html("<input type='text' id='SCHOOL_STATE' value='"+tdSchState.html()+"'/>");
		            		tdSchCity.html("<input type='text' id='SCHOOL_CITY' value='"+tdSchCity.html()+"'/>");
		            		tdSchPincode.html("<input type='text' id='SCHOOL_PINCODE' value='"+tdSchPincode.html()+"'/>");
		            		tdPhone.html("<input type='text' id='PHONE' value='"+tdPhone.html()+"'/>");
		            		tdEPhone.html("<input type='text' id='EMER_PHONE' value='"+tdEPhone.html()+"'/>");
		            		tdEmail.html("<input type='text' id='EMAIL_ID' value='"+tdEmail.html()+"'/>");
		            		tdUpdateUsrName.html("<input type='text' id='UPDATE_USER_NAME' value='"+tdUpdateUsrName.html()+"'/>");
		            		
		            		tdDOB.html("<input type='text' class ='dateTimePicker1' id='DOB' value='"+tdDOB.html()+"'/>");
		            		tdGender.html("<select>"+optionGender+"</Select>"); 
		            		
		            		tdButtons.html("<img src='images/save.png' class='btnSave mdl-button'/>"); 
		            		 
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
		            		
		            		$("#DOB").change(function(){
		            			if($(this).val()!=undefined ||$(this).val()!=null){
		            			var start = $('#DOB').val();
		            	     	
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
		            		 	
		            			tdAge.html(age);
		            	     }
		            		 
		            		});
		            		
		            		$(".btnSave").bind("click", Save);
		            		};
		            		function Save(){ 
		            			var dataToSend =[];
		            			var par = $(this).parent().parent(); //tr 
		            			var tdPartID = par.children("td:nth-child(1)");
		            			var tdFname = par.children("td:nth-child(2)"); 
			            		var tdMname = par.children("td:nth-child(3)"); 
			            		var tdLname = par.children("td:nth-child(4)"); 
			            		var tdDOB = par.children("td:nth-child(5)");
			            		var tdAge = par.children("td:nth-child(6)");
			            		var tdSchool = par.children("td:nth-child(7)");
			            		var tdAdd1 = par.children("td:nth-child(8)");
			            		var tdAdd2 = par.children("td:nth-child(9)");
			            		var tdState = par.children("td:nth-child(10)");
			            		var tdCity = par.children("td:nth-child(11)");
			            		var tdPincode = par.children("td:nth-child(12)");
			            		var tdSchAdd1 = par.children("td:nth-child(13)");
			            		var tdSchAdd2 = par.children("td:nth-child(14)");
			            		var tdSchState = par.children("td:nth-child(15)");
			            		var tdSchCity = par.children("td:nth-child(16)");
			            		var tdSchPincode = par.children("td:nth-child(17)");
			            		var tdGender = par.children("td:nth-child(18)");
			            		var tdPhone = par.children("td:nth-child(19)");
			            		var tdEPhone = par.children("td:nth-child(20)");
			            		var tdEmail = par.children("td:nth-child(21)");
			            		var tdInsertUsrName = par.children("td:nth-child(22)");
			            		var tdUpdateUsrName = par.children("td:nth-child(23)");
			            		var tdButtons = par.children("td:nth-child(24)"); 
			            		var data = {};
			            					            		
			            						            	
			            		$('input:text').each(function(index){			
			            	        data[$(this).attr('id')] =$(this).val();
			            	        dataToSend.push(data);
	            				});
	            				
			            		
			            		
			            		tdGender.html(tdGender.children($('optionGender')).val());
		            			tdButtons.html("<img src='images/edit.png' class='btnEdit mdl-button'/>"); 
		            			$(".btnEdit").live("click", Edit); 
		            			
		            			data["GENDER"] = tdGender.html();
			            		dataToSend.push(data);
			            		
			            		data["PART_ID"] = tdPartID.html();
			            		dataToSend.push(data);
			            		 
			            		data["AGE"] = tdAge.html();
			            		dataToSend.push(data);
			            
		            			console.log("dataToSend length: "+dataToSend.length);
		            			
		            			$.ajax({
		            			       type: "post",
		            			       url:"updateParticipant",
		            			       data:{saveData:JSON.stringify(dataToSend)}, 
		            			     		            			       
		            			       datatype:"json",
		            			       success:function(data, textStatus, jqXHR)
		            		            {
		            			    	   var e=jQuery.parseJSON(data);
		            			              if(e.status==="success"){
		            				    		   alert("Update Done");
		            				    	   }
		            				    	   else if(e.status==="error"){
		            				    		   alert("ERROR");   
		            				    	   }
		            		            }
		            			});
		            			
		            		}; 
						
		            	
		            }); 
		    	   
		            $('#participanttable').append(trHTML);
		    	  // }                  
	            },
	            error:function(xhr,status)
	            {
	                 console.log(status);
	             }
	            });					  
		 });  
	
	
});
</script>

<title>Update Participant</title>
</head>
<body>
<form method="post">
<div class="mdl-dialog__content">
<div class="mdl-grid">
	<div class="mdl-cell mdl-cell--4-col">
	<div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
		<input type="text" id="searchID" name="searchID" class="mdl-textfield__input" />
		<label class="mdl-textfield__label" for="searchID">Participant ID </label>
	</div>
	<div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
		<input type="text" id="searchFName" name="searchFName" class="mdl-textfield__input"/>
		<label class="mdl-textfield__label" for="searchFName">First name</label> 
	</div>
	<div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
		<input type="text" id="searchMName" name="searchMName" class="mdl-textfield__input"/>
		<label class="mdl-textfield__label" for="searchMName">Middle name</label> 
	</div>
	<div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
		<input type="text" id="searchLName" name="searchLName" class="mdl-textfield__input"/>
		<label class="mdl-textfield__label" for="searchLName">Surname</label>
	</div>
	<div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
		<input type="text" id="searchPhone" name="searchPhone" class="mdl-textfield__input"/>
		<label class="mdl-textfield__label" for="searchPhone">Phone</label>
	</div>	

	<div class="mdl-dialog__actions">
		<button type="button" class="mdl-button mdl-js-button mdl-button--fab mdl-button--mini-fab mdl-js-ripple-effect  mdl-button--accent" 
					id="searchBtn" >Search</button>
	</div>
</div>
</div>	
</div>	
<div id="tablediv">
<table cellspacing="1px" id="participanttable" > 
    <tr class="tr"> 
        <th class="th" scope="col">Participant ID</th> 
        <th class="th" scope="col">First Name</th> 
        <th class="th" scope="col">Middle Name</th> 
        <th class="th" scope="col">Last Name</th> 
        <th class="th" scope="col">Date of Birth</th> 
        <th class="th" scope="col">Age</th>  
        <th class="th" scope="col">School</th>  
        <th class="th" scope="col">Address Line 1</th>  
        <th class="th" scope="col">Address Line 2</th>  
        <th class="th" scope="col">State</th>
        <th class="th" scope="col">City</th>
        <th class="th" scope="col">pincode</th>
        <th class="th" scope="col">School Address Line 1</th>
        <th class="th" scope="col">School Address Line 2</th>
        <th class="th" scope="col">School State</th> 
        <th class="th" scope="col">School City</th> 
        <th class="th" scope="col">School Pincode</th> 
        <th class="th" scope="col">Gender</th>
        <th class="th" scope="col">Phone</th>
        <th class="th" scope="col">Emergency Phone</th>
        <th class="th" scope="col">Email Id</th>
        <th class="th" scope="col">Insert User Name</th>
        <th class="th" scope="col">Update User Name</th>    
        <th class="th" scope="col">Edit/Save</th>       
    </tr> 
</table>

</div>
</form>
</body>
</html>