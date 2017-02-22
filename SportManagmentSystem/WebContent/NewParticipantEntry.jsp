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
	/* $("#txtDateOfBirth").change(function(){
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
	  }); */
	  
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
	    				$('#ageText').text(yrs);
	    				valideDate=false;
	    			}
	    			
	    		}
	    		
	    	}
	    for(var i=1;i<32;i++){$('#day').append('<option value="'+i+'">'+i+'</option>')}
	    for(var i=1;i<13;i++){$('#month').append('<option value="'+i+'">'+i+'</option>')}
	    for(var i=1970;i<2017;i++){$('#year').append('<option value="'+i+'">'+i+'</option>')}
	
	 $("#gameBtn").click(function(){ 
		$("#gameDiv").show();
		
		if(validationParticipant()){
		var jsonData={
				"txtFName":$("#txtFName").val(),
				"txtMName":$("#txtMName").val(),
				"txtLName":$("#txtLName").val(),
				"txtDateOfBirth":$("#day").val()+"-"+$("#month").val()+"-"+$("#year").val(),
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
				"ageText":$('#ageText').text(),
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
		    	   /* $("#gameEvent").append("<br>"); */
		    	   var e=jQuery.parseJSON(data);
		    	   console.log(e);
		    	   if(e!=undefined && e!=null && e!="" ){
		    		   $.each(e, function(key, value){
		    			   console.log(e[key]);
		    			   if(e[key]!=undefined && e[key]!=null && e[key]!="" ){
		    				   $.each(e[key], function(key, value){
		    					   console.log(key+" : "+value);
		    					   $("#gameEvent").append("<h6>"+key+"</h6>");
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
		    	   $('#ageText').text("");
		    	   $("#gameDiv").hide();
	            }
		});
		
	});
	

});
</script>

<title>New Participant Entry</title>
</head>
<body>
	<form method="post" id="cform">
	<h5>Participant Registration</h5>
		<div class="mdl-dialog__content">
				<h6>PERSONAL INFORMATION</h6>
							<div class="mdl-grid">
							<div class="mdl-cell mdl-cell--4-col">
								<div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
									<input type="text" name="txtFName" id="txtFName" class="mdl-textfield__input" maxlength="50"/>
									<label class="mdl-textfield__label" for="txtFName">First name</label>
								</div>
							</div>	
							<div class="mdl-cell mdl-cell--4-col">
								<div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
									<input type="text" name="txtMName" id="txtMName" class="mdl-textfield__input" maxlength="50"/>
									<label class="mdl-textfield__label" for="txtMName">Middle name</label>
								</div>
							</div>
							<div class="mdl-cell mdl-cell--4-col">
								<div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
									<input type="text" name="txtLName" id="txtLName" class="mdl-textfield__input" maxlength="50"/>
									<label class="mdl-textfield__label" for="txtLName">Surname</label>
								</div>
							</div>	
                			<div class="mdl-cell mdl-cell--4-col dobvals">
				                   <label><h6>Date of Birth</h6></label>
				                   <select id="day"><option val="0">DD<option></select>
				                   <select id="month"><option val="0">MM<option></select>
				                   <select id="year"><option val="0">YYYY<option></select>
				               </div>
				                <div class="mdl-cell mdl-cell--4-col dobvals">
				                        <h6>Age:</h6>
				                        <span id="ageText" name="ageText"></span>
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
									<input type="text" name="txtEmailId" id="txtEmailId" class="mdl-textfield__input" maxlength="40"/>
									<label class="mdl-textfield__label" for="txtEmailId">Email-Id</label>
								</div>
							</div>		
							<div class="mdl-cell mdl-cell--4-col">
								<div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
									<input type="text" name="txtPhone" id="txtPhone" class="mdl-textfield__input" maxlength="10"/>
									<label class="mdl-textfield__label" for="txtPhone">Phone</label>
								</div>
							</div>	
							<div class="mdl-cell mdl-cell--4-col">
								<div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
									<input type="text" name="txtEmerPhone" id="txtEmerPhone" class="mdl-textfield__input" maxlength="10"/>
									<label class="mdl-textfield__label" for="txtEmerPhone">Emergency Phone</label>
								</div>
							</div>		
		 </div>
		 <h6>ADDRESS</h6>		
		 <div class="mdl-grid">
					<div class="mdl-cell mdl-cell--4-col">
						<div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
							<input type="text" name="txtAdd1" id="txtAdd1" class="mdl-textfield__input" maxlength="250"/>
							<label class="mdl-textfield__label" for="txtAdd1">Address Line 1</label>
						</div>
					</div>		
					<div class="mdl-cell mdl-cell--4-col">
						<div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
							<input type="text" name="txtAdd2" id="txtAdd2" class="mdl-textfield__input" maxlength="250"/>
							<label class="mdl-textfield__label" for="txtAdd2">Address Line 2</label>
						</div>
					</div>	
					<div class="mdl-cell mdl-cell--4-col">
						<div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
							<input type="text" name="txtCity" id="txtCity" class="mdl-textfield__input" maxlength="20"/>
							<label class="mdl-textfield__label" for="txtCity">City</label>
						</div>
					</div>		
					<div class="mdl-cell mdl-cell--4-col">
						<div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
							<input type="text" name="txtState" id="txtState" class="mdl-textfield__input" maxlength="20"/>
							<label class="mdl-textfield__label" for="txtState">State</label>
						</div>
					</div>		
					<div class="mdl-cell mdl-cell--4-col">
						<div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
							<input type="text" name="txtPincode" id="txtPincode" class="mdl-textfield__input" maxlength="6"/>
							<label class="mdl-textfield__label" for="txtPincode">Pincode</label>
						</div>
					</div>	
		</div>
		<h6>SCHOOL ADDRESS</h6>
		<div class="mdl-grid">
					<div class="mdl-cell mdl-cell--4-col">
						<div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
							<input type="text" name="txtSch" id="txtSch" class="mdl-textfield__input" maxlength="150"/>
							<label class="mdl-textfield__label" for="txtSch">School Name</label>
						</div>
					</div>		
					<div class="mdl-cell mdl-cell--4-col">
						<div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
							<input type="text" name="txtSchAdd1" id="txtSchAdd1" class="mdl-textfield__input" maxlength="250"/>
							<label class="mdl-textfield__label" for="txtSchAdd1">School Address Line 1</label>
						</div>
					</div>	
					<div class="mdl-cell mdl-cell--4-col">
						<div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
							<input type="text" name="txtSchAdd2" id="txtSchAdd2" class="mdl-textfield__input" maxlength="250"/>
							<label class="mdl-textfield__label" for="txtSchAdd2">School Address Line 2</label>
						</div>
					</div>	
					<div class="mdl-cell mdl-cell--4-col">
						<div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
							<input type="text" name="txtSchCity" id="txtSchCity" class="mdl-textfield__input" maxlength="20"/>
							<label class="mdl-textfield__label" for="txtSchCity">School City</label>
						</div>
					</div>
					<!-- </td>
					<td> -->
					<div class="mdl-cell mdl-cell--4-col">
						<div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
							<input type="text" name="txtSchState" id="txtSchState" class="mdl-textfield__input" maxlength="20"/>
							<label class="mdl-textfield__label" for="txtSchState">School State</label>
						</div>
					</div>		
					<div class="mdl-cell mdl-cell--4-col">
						<div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
							<input type="text" name="txtSchPincode" id="txtSchPincode" class="mdl-textfield__input" maxlength="6"/>
							<label class="mdl-textfield__label" for="txtSchPincode">School Pincode</label>
						</div>
					</div>
		</div>
		<div class="mdl-grid">
					<div class="mdl-dialog__actions">
						<button type="button" class="mdl-button--fab mdl-button--accent" 
					id="gameBtn" >Next</button>
					
					</div>
		</div>
		<div  id="gameDiv">
			<div class="mdl-grid">
					<div id="gameEvent">
						<label class="mdl-textfield__label">SELECT GAME NAMES YOU WANT TO PARTICIPATE:</label>
					</div>				
			</div>			
					<div class="mdl-dialog__content">
						<button type="button" class="mdl-button--fab mdl-button--accent" id="addBtn">Add</button>
					</div>	
	
			
		</div>
	
	</form>
</body>
</html>