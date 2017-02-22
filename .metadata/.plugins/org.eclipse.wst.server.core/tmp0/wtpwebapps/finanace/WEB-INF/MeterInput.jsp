<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" session="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>New Meter Entry</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/jquery-ui.structure.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/jquery-ui.theme.css">

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css">


<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.8.1.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui.js"></script> 
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.currency.js"></script>

<script type="text/javascript" src="<%=request.getContextPath()%>/js/validationFile.js"></script>
 
<script>
$(document).ready(function(){
	$.ajax({
	       type: "post",
	       url:"meterInputServlet",
	       data:{dataSend:"legalEntity"},   
	       datatype:"json",
	       success:function(data, textStatus, jqXHR)
         {
	    	  // console.log("legalEntity"+data);
	    	   var e=jQuery.parseJSON(data);
        
   	// console.log("legalEntity"+e);    
        var options = '';
        options += '<option value="NotSelected">Select the Legal Entity</option>';
		if(e!=undefined && e!=null && e!="" ){
			$.each(e, function(key, value){
				options += '<option value="' + key + '">' + value + '</option>';
			});
        	/* for (var i = 0; i < e.length; i++) {
          	options += '<option value="' + e[i].optionValue + '">' + e[i].optionDisplay + '</option>';
        	} */
		}
        $("#legalEntity").html(options);
    },
    error:function(xhr,status){
    		alert("Errors : ");
           console.log(status);
    }
    });
});

$(document).ready(function(){
	$.ajax({
	       type: "post",
	       url:"meterInputServlet",
	       data:{dataSend:"businessUnit"},   
	       datatype:"json",
	       success:function(data, textStatus, jqXHR)
         {
	    	 //  console.log("businessUnit"+data);
	    	   var e=jQuery.parseJSON(data);
        
    console.log("Legal"+e);    
        var options = '';
        options += '<option value="NotSelected">Select the Business Unit</option>';
		if(e!=undefined && e!=null && e!="" ){
			$.each(e, function(key, value){
				options += '<option value="' + key + '">' + value + '</option>';
			});
        	/* for (var i = 0; i < e.length; i++) {
          	options += '<option value="' + e[i].optionValue + '">' + e[i].optionDisplay + '</option>';
        	} */
		}
        $("#businessUnit").html(options);
    },
    error:function(xhr,status){
    		alert("Errors");
           console.log(status);
    }
    });
});

$(document).ready(function(){
	$.ajax({
	       type: "post",
	       url:"meterInputServlet",
	       data:{dataSend:"clientId"},   
	       datatype:"json",
	       success:function(data, textStatus, jqXHR)
         {
	    	 //  console.log("clientId"+data);
	    	   var e=jQuery.parseJSON(data);
        
  		 // console.log("clientId"+e);    
        var options = '';
        options += '<option value="NotSelected">Select the Client Id</option>';
		if(e!=undefined && e!=null && e!="" ){
			$.each(e, function(key, value){
				options += '<option value="' + key + '">' + value + '</option>';
			});
        	/* for (var i = 0; i < e.length; i++) {
          	options += '<option value="' + e[i].optionValue + '">' + e[i].optionDisplay + '</option>';
        	} */
		}
        $("#clientId").html(options);
    },
    error:function(xhr,status){
    		alert("Errors");
           console.log(status);
    }
    });
});

$(document).ready(function(){
	$.ajax({
	       type: "post",
	       url:"meterInputServlet",
	       data:{dataSend:"clientlocationNo"},   
	       datatype:"json",
	       success:function(data, textStatus, jqXHR)
         {
	    	  // console.log("clientlocationNo"+data);
	    	   var e=jQuery.parseJSON(data);
        
  		//  console.log("clientlocationNo"+e);    
        var options = '';
        options += '<option value="NotSelected">Select the Client Location Number</option>';
		if(e!=undefined && e!=null && e!="" ){
			$.each(e, function(key, value){
				options += '<option value="' + key + '">' + value + '</option>';
			});
        	/* for (var i = 0; i < e.length; i++) {
          	options += '<option value="' + e[i].optionValue + '">' + e[i].optionDisplay + '</option>';
        	} */
		}
        $("#clientlocationNo").html(options);
    },
    error:function(xhr,status){
    		alert("Errors");
           console.log(status);
    }
    });
});

$(document).ready(function(){
	$.ajax({
	       type: "post",
	       url:"meterInputServlet",
	       data:{dataSend:"productID"},   
	       datatype:"json",
	       success:function(data, textStatus, jqXHR)
         {
	    	 //  console.log("productID"+data);
	    	   var e=jQuery.parseJSON(data);
        
   		// console.log("productID"+e);    
        var options = '';
        options += '<option value="NotSelected">Select the Product ID</option>';
		if(e!=undefined && e!=null && e!="" ){
			$.each(e, function(key, value){
				options += '<option value="' + key + '">' + value + '</option>';
			});
        	/* for (var i = 0; i < e.length; i++) {
          	options += '<option value="' + e[i].optionValue + '">' + e[i].optionDisplay + '</option>';
        	} */
		}
        $("#productID").html(options);
    },
    error:function(xhr,status){
           console.log(status);
    }
    });
 
});

$(document).ready(function(){
 $("#submitBtn").click(function(){
		if(validationsMeter()){
			var jsonData={
					"LEGAL_ENTITY":$('#legalEntity').val(),
					"BUSINESS_UNIT":$('#businessUnit').val(),
					"CLIENT_ID":$('#clientId').val(),
					"CLIENT_LOCATION_NO":$('#clientlocationNo').val(),
					"PRODUCT_ID":$('#productID').val(),
					"CLIENT_NAME":$('#CLIENT_NAME').val(),
					"NICK_NAME":$('#NICK_NAME').val(),
					"TRANSACTION_TYPE":$('#TRANSACTION_TYPE').val(),
					"CHARGES":$('#CHARGES').val(),
					"QUANTITY":$('#QUANTITY').val(),
					"PROCESS_DATE":$('#PROCESS_DATE').val(),
					"ACCOUNT_SETUP_FLAG":$('#ACCOUNT_SETUP_FLAG').val(),
					"register":"N",
					"notRegister":"N"
			};	
			setFlags(jsonData,$('#register'));
			setFlags(jsonData,$('#notRegister'));
			
			$.ajax({
			       type: "post",
			       url:"meterInputServlet",
			       data:{dataSubmit:JSON.stringify(jsonData)},   
			     
			       datatype:"json",
			       success:function(data, textStatus, jqXHR){
			    	   var e=jQuery.parseJSON(data);
			        		//console.log("data :" +e.status);
			       		  if(e.status==="Already"){
				    		   alert("Data Already present");
				    	   }
			            	else if(e.status==="Inserted"){
				    		   alert("Data is Inserted");   
				    	   }
			            	else if(e.status==="Notregister"){
				    		alert("Data is Not register.");
				    		
				    	   } 
			       		
			       	}, 
		            error:function(xhr,status)
		            {
		            	 var errText = "<b>Error "+xhr.status+" : "+xhr.responseText+" , "+textStatus+", "+errorThrown+" </b>"; 
		                 console.log(errText);
		             }
		         });
			$(document).find("h1").remove();
		 }
		 else{
			// var errText = "<b>Error "+xhr.status+" : "+xhr.responseText+" , "+textStatus+", "+errorThrown+" </b>";
             //alert(errText);
			alert("Error: Please Fill the Required Fields marked with Red Border");
			//$( ".container" ).prepend("")
			
		}  
	  
	 }); 
 $(".dateTimePicker1").datepicker({
     showOn: "button",
     buttonImage: "css/images/calendar.png",
     buttonImageOnly: true,
     buttonText: "",
     dateFormat:"dd-M-yy"
   });
 });	

</script>
</head>
<body>
<form method="post">
<img src="/Finance/images/Experian_logo.png"/>
<div class="container">
<h3 class="heading">New Meter Entry</h3>
	<div>
	<div class="labels">LEGAL ENTITY :</div>
	<div class=""><select id="legalEntity"></select></div>
	</div>
	<div>
	<div class="labels">BUSINESS UNIT :</div>
	<div class=""><select id="businessUnit"></select></div>
	</div>
	<div>
	<div class="labels">CLIENT ID :</div>
	<div class=""><select id="clientId"></select></div>
	</div>
	<div>
	<div class="labels">CLIENT LOCATION NO :</div>
	<div class=""><select id="clientlocationNo"></select></div>
	</div>
	<div>
	<div class="labels">PRODUCT ID :</div>
	<div class=""><select id=productID></select></div>
	</div>
	<div>
	<div class="labels">CLIENT NAME<span class="astrik">*</span> :</div>
	<div class=""><input type="text" name="txtCLIENT_NAME" id="CLIENT_NAME" /></div>
	</div>
	<div>
	<div class="labels">NICK NAME :</div>
	<div class=""><input type="text" name="txtNICK_NAME" id="NICK_NAME" /></div>
	</div>
	<div>
	<div class="labels">TRANSACTION TYPE<span class="astrik">*</span> :</div>
	<div class=""><input type="text" name="txtTRANSACTION_TYPE" id="TRANSACTION_TYPE" /></div>
	</div>
	<div>
	<div class="labels">CHARGES<span class="astrik">*</span> :</div>
	<div class=""><input type="text" name="txtCHARGES" id="CHARGES" /></div>
	</div>
	<div>
	<div class="labels">QUANTITY<span class="astrik">*</span> :</div>
	<div class=""><input type="text" name="txtQUANTITY" id="QUANTITY" /></div>
	</div>
	<div>
	<div class="labels">ACCOUNT SETUP FLAG :</div>
	<div class="">
		<select name="txtACCOUNT_SETUP_FLAG" id="ACCOUNT_SETUP_FLAG">
			<option value="Y">Yes</option>
			<option value="N">No</option>
		</select>
	</div>
	</div>
	<div>
	<div class="labels">PROCESS DATE :</div>
	<div class=""><input type="text" class="dateTimePicker1" name="txtPROCESS_DATE" id="PROCESS_DATE" /></div>
	</div>
	<div>
		<div class="chkLabel radioLabel">
		<input type="radio" name="meterInput" rel="1" id="register" value="Register">Register The Meter Input</div>
		<div class="chkLabel radioLabel">
		<input type="radio" name="meterInput" rel="2" id="notRegister" value="Cancel">Do not Register The Meter Input
		</div>
	</div>
	<br/>
	<div>
		<table style="margin-left: 300px; margin-top: 20px;">
		<tr>
		<td><div class="buttondiv"><input type="button" value="Submit" id="submitBtn" style="float: inherit;"></div></td>
		<td><div class="buttondiv"><input type="button" value="Back" onclick="history.go(-1);" style="float: inherit ;"/></div></td>
		<td><div class="buttondiv"><input type="button" value="Show Output" id="outputBtn" onclick="javascript:window.location='Output.jsp'" style="float: inherit;"></div></td>
		<!-- <td><div class="buttondiv"><input type="button" value="Upload File" id="outputBtn" onclick="javascript:window.location='UploadFile.jsp'" style="float: inherit;"></div></td> -->
		</tr>
		</table>
	</div>
</div>
</form>
<jsp:include page="Upload.jsp"></jsp:include>
<jsp:include page="footer.jsp"/>
</body>
</html>