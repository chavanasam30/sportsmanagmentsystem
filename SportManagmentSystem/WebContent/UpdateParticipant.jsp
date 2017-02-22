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
		if(validations()){		
		var jsonData={
				"searchID":$('#searchID').val(),
				"searchFName":$('#searchFName').val(),
				"searchMName":$('#searchMName').val(),
				"searchLName":$('#searchLName').val(),
				"searchPhone":$('#searchPhone').val()
		};
		$.ajax({
		       type: "post",
		       url:"/SportManagmentSystem/updateParticipant",
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
 
		    	   if(!(data.length > 0)){
		    		   $("#tablediv").hide(); 
		    	   }else{
		           $.each(data, function(index, participant) {  
		            	trHTML += '<tr class=\"tablerow\"><td id=\"PART_ID\" >' + participant.PART_ID 
		            	+ '</td><td id=\"FNAME\">' + participant.FNAME 
		            	+ '</td><td id=\"MNAME\" >' + participant.MNAME 
		            	+ '</td><td id=\"LNAME\">' + participant.LNAME
		            	+ '</td><td id=\"DOB\">' + participant.DOB
		            	+ '</td><td id=\"AGE\" >' + participant.AGE
		            	+ '</td><td id=\"SCHOOL\" >' + participant.SCHOOL 
		            	+ '</td><td id=\"ADDRESS_LINE1\">'+ participant.ADDRESS_LINE1
		            	+ '</td><td id=\"ADDRESS_LINE2\">'+ participant.ADDRESS_LINE2
		            	+ '</td><td id=\"STATE\">'+ participant.STATE
		            	+ '</td><td id=\"CITY\">'+ participant.CITY
		            	+ '</td><td id=\"PINCODE\">'+ participant.PINCODE
		            	+ '</td><td id=\"SCHOOL_ADDRESS_LINE1\">'+ participant.SCHOOL_ADDRESS_LINE1 
		            	+ '</td><td id=\"SCHOOL_ADDRESS_LINE2\">'+ participant.SCHOOL_ADDRESS_LINE2
		            	+ '</td><td id=\"SCHOOL_STATE\">'+ participant.SCHOOL_STATE
		            	+ '</td><td id=\"SCHOOL_CITY\">'+ participant.SCHOOL_CITY
		            	+ '</td><td id=\"SCHOOL_PINCODE\">'+ participant.SCHOOL_PINCODE
		            	+ '</td><td id=\"GENDER\">'+ participant.GENDER
		            	+ '</td><td id=\"PHONE\">'+ participant.PHONE
		            	+ '</td><td id=\"EMER_PHONE\">'+ participant.EMER_PHONE
		            	+ '</td><td id=\"EMAIL_ID\">'+ participant.EMAIL_ID
		            	+ '</td><td id=\"INSERT_USER_NAME\">'+ participant.INSERT_USER_NAME
		            	+ '</td><td id=\"UPDATE_USER_NAME\">'+ participant.UPDATE_USER_NAME
		            	+ '</td><td><img src=\'css/images/edit.png\' class=\'btnEdit\'/></td></tr>';
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
		            		
		            		/* tdDOB.html("<input type='text' class ='dateTimePicker1' id='DOB1' value='"+tdDOB.html()+"'/>"); */
		            		 tdDOB.html("<div id='DOB1'><select id='day'><option val='0'>DD<option></select><select id='month'><option val='0'>MM<option></select><select id='year'><option val='0'>YYYY<option></select></div>");
		            		
		            		tdGender.html("<select>"+optionGender+"</Select>"); 
		            		
		            		tdButtons.html("<img src='css/images/save.png' class='btnSave mdl-button'/>"); 
		            		 
		            		 var day;
		            		    var month;
		            		    var year;
		            		    $("#day").change(function(){
		            		    	day=$(this).val();
		            		    	calAge();
		            		    });
		            		    $("#month").change(function(){
		            		    	month=$(this).val();
		            		    	calAge();
		            		    });
		            		    $("#year").change(function(){
		            		    	year=$(this).val();
		            		    	calAge();
		            		    });


		            		    calAge =function(e){
		            		    		let d=31;
		            		    		 var valideDate=false;
		            		    		if(!isNaN(day) && !isNaN(month)&&!isNaN(year)){
		            		    			if(month=='February'){
		            		    			 if((this.isLeapyear() && day<30)||day<29){
		            		    				 d=28;
		            		    				 if(this.isLeapyear()){
		            		    					d=28;	 
		            		    				 }
		            		    				 valideDate=true;
		            		    			 }
		            		    			}else if(month=='April' || month=='June' ||month=='September' || month=='November' ){
		            		    				if(day<31){
		            		    				 valideDate=true;
		            		    				 d=31;	 
		            		    			 }
		            		    			}else{
		            		    				valideDate=true;
		            		    			}
		            		    			if(valideDate){
		            		    				console.log(month+'/'+ day+'/'+year);
		            		    				this.dob=new Date(month+'/'+ day+'/'+year);
		            		    				console.log(this.dob);
		            		    				let ageDifMs = new Date('3/15/2017') - new Date(month+'/'+ day+'/'+year).getTime();
		            		    				let ageDate = new Date(ageDifMs);
		            		    				dayofbirth=d-ageDate.getDate();
		            		    				months=12-(ageDate.getMonth()+1);
		            		    				var yrs=Math.abs(ageDate.getUTCFullYear() - 1970);
		            		    				/* $('#ageText').text(yrs); */
		            		    				tdAge.html(yrs);
		            		    				valideDate=false;
		            		    			}
		            		    			
		            		    		}
		            		    		
		            		    	}
		            		    for(var i=1;i<32;i++){$('#day').append('<option value="'+i+'">'+i+'</option>')}
		            		    for(var i=1;i<13;i++){$('#month').append('<option value="'+i+'">'+i+'</option>')}
		            		    for(var i=1970;i<2017;i++){$('#year').append('<option value="'+i+'">'+i+'</option>')}
		            		    
		            		   
		            		    
		            		    
		            		
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
		            			tdButtons.html("<img src='css/images/edit.png' class='btnEdit mdl-button'/>"); 
		            			$(".btnEdit").live("click", Edit); 
		            			
		            			data["GENDER"] = tdGender.html();
			            		dataToSend.push(data);
			            		
			            		data["PART_ID"] = tdPartID.html();
			            		dataToSend.push(data);
			            		 
			            		data["AGE"] = tdAge.html();
			            		dataToSend.push(data);
			            
			            		data["DOB"] = $("#day").val()+"-"+$("#month").val()+"-"+$("#year").val();
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
		    	   }
		            $('#participanttable').append(trHTML);
		    	                  
	            },
	            error:function(xhr,status)
	            {
	                 console.log(status);
	                 $("#tablediv").hide(); 
	             }	
		
			});	
		}else{
			$("#tablediv").hide();  
			alert("Error : Please Enter atlest one parameter to search ");
			
		}
		
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
	</div>
	<div class="mdl-cell mdl-cell--4-col">
	<div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
		<input type="text" id="searchFName" name="searchFName" class="mdl-textfield__input"/>
		<label class="mdl-textfield__label" for="searchFName">First name</label> 
	</div>
	</div>
	<div class="mdl-cell mdl-cell--4-col">
	<div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
		<input type="text" id="searchMName" name="searchMName" class="mdl-textfield__input"/>
		<label class="mdl-textfield__label" for="searchMName">Middle name</label> 
	</div>
	</div>
	<div class="mdl-cell mdl-cell--4-col">
	<div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
		<input type="text" id="searchLName" name="searchLName" class="mdl-textfield__input"/>
		<label class="mdl-textfield__label" for="searchLName">Surname</label>
	</div>
	</div>
	<div class="mdl-cell mdl-cell--4-col">
	<div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
		<input type="text" id="searchPhone" name="searchPhone" class="mdl-textfield__input"/>
		<label class="mdl-textfield__label" for="searchPhone">Phone</label>
	</div>	
	</div>
	<div class="mdl-cell mdl-cell--4-col">
	<div class="mdl-dialog__actions">
		<button type="button" class="mdl-button--accent" 
					id="searchBtn">Search</button>
	</div>
</div>
</div>	
</div>	
<div id="tablediv">
<table id="participanttable" border="1px solid black;" > 
    <tr class="tableHeader"> 
        <th>Participant ID</th> 
        <th>First Name</th> 
        <th>Middle Name</th> 
        <th>Last Name</th> 
        <th>Date of Birth</th> 
        <th>Age</th>  
        <th>School</th>  
        <th>Address Line 1</th>  
        <th>Address Line 2</th>  
        <th>State</th>
        <th>City</th>
        <th>pincode</th>
        <th>School Address Line 1</th>
        <th>School Address Line 2</th>
        <th>School State</th> 
        <th>School City</th> 
        <th>School Pincode</th> 
        <th>Gender</th>
        <th>Phone</th>
        <th>Emergency Phone</th>
        <th>Email Id</th>
        <th>Insert User Name</th>
        <th>Update User Name</th>    
        <th>Edit/Save</th>       
    </tr> 
</table>

</div>
</form>
</body>
</html>