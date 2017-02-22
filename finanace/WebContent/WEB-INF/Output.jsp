<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" session="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Output</title>
<link rel="stylesheet" type="text/css" href="css/style.css">

<script type="text/javascript" src="js/jquery.1.7.2.min.js"></script>
<script type="text/javascript" src="js/validationFile.js"></script>
<script type="text/javascript" src="js/jquery-ui.js"></script> 
<script type="text/javascript" src="js/jquery.currency.js"></script>

<script type="text/javascript">
$(document).ready(function(){
	 $("#tablediv").hide();
	 
	 $.ajax({
	       type: "post",
	       url:"outputServlet",
	       data:{dataSend:"clientName"},   
	       datatype:"json",
	       success:function(data, textStatus, jqXHR)
       {
	    	   //console.log("legalEntity"+data);
	    	  var e=jQuery.parseJSON(data);
      
  		//console.log("legalEntity"+e);    
      var options = '';
      options += '<option value="NotSelected">Select the Client Name</option>';
		 if(e!=undefined && e!=null && e!="" ){ 
			$.each(e, function(key, value){
				options += '<option value="' + key + '">' + value + '</option>';
			});
		}
      $("#clientName").html(options);
  },
  error:function(xhr,status){
         console.log("clientName "+status);
  }
  });
	 
		$.ajax({
		       type: "post",
		       url:"outputServlet",
		       data:{dataSend:"clientId"},   
		       datatype:"json",
		       success:function(data, textStatus, jqXHR)
	         {
		    	   //console.log("legalEntity"+data);
		    	  var e=jQuery.parseJSON(data);
	        
	    		//console.log("legalEntity"+e);    
	        var options = '';
	        options += '<option value="NotSelected">Select the Client Id</option>';
			 if(e!=undefined && e!=null && e!="" ){ 
				$.each(e, function(key, value){
					options += '<option value="' + key + '">' + value + '</option>';
				});
			}
	        $("#clientId").html(options);
	    },
	    error:function(xhr,status){
	           console.log("clientId :"+status);
	    }
	    });
		
		
	 $.ajax({
	       type: "post",
	       url:"outputServlet",
	       data:{dataSend:"month"},   
	       datatype:"json",
	       success:function(data, textStatus, jqXHR)
   {
	    	   //console.log("legalEntity"+data);
	    	  var e=jQuery.parseJSON(data);
  
		//console.log("legalEntity"+e);    
  var options = '';
  options += '<option value="NotSelected">Select Month</option>';
		 if(e!=undefined && e!=null && e!="" ){ 
			$.each(e, function(key, value){
				options += '<option value="' + key + '">' + value + '</option>';
			});
		}
  $("#month").html(options);
},
error:function(xhr,status){
     console.log("month "+status);
}
});
	 
	 $.ajax({
	       type: "post",
	       url:"outputServlet",
	       data:{dataSend:"year"},   
	       datatype:"json",
	       success:function(data, textStatus, jqXHR)
 {
	    	  
	  var e=jQuery.parseJSON(data);  
	  var options = '';
	  options += '<option value="NotSelected">Select Year</option>';
	 if(e!=undefined && e!=null && e!="" ){ 
		$.each(e, function(key, value){
					options += '<option value="' + key + '">' + value + '</option>';
				});
			}
	  $("#year").html(options);
	},
	error:function(xhr,status){
	     console.log("year "+status);
	}
});
	$("input[name ='rad']").click(function(){
		var rel=$(this).attr("rel");
		$(".radioDiv").hide();
		$(".radioDiv[rel='"+rel+"']").show(500);
		$(".radioDiv").find("input[type='text']").each(function(){$(this).val("");});
    });
	 
		$("#submitBtn1").click(function(){
			 var jsonData={
						"CLIENT_ID":null,
						"CLIENT_NAME":null,
						"MONTH":$('#month').val(),
						"YEAR":$('#year').val()
				};	
			$.ajax({
			       type: "post",
			       url:"outputServlet",
			       data:{dataSubmit:JSON.stringify(jsonData)},   
			       datatype:"json",
			       success:function(data, textStatus, jqXHR)
		            {
			    	 		             
				    	   $("#slabtable").find("tr:gt(0)").remove();
				    	   var trHTML = '';	
				    	   $('#tablediv').show();
				    	   console.log("submit:"+data);
				    	   console.log("length :"+data.length);
				    	  if(data.length > 0){
				            $.each(data, function(index, output) {  
				            	trHTML += '<tr><td class=\"td\" >' + output.srNumber + '</td><td class=\"td\">' 
				            	+ output.businessUnit + '</td><td class=\"td\" >' 
				            	+ output.clientId + '</td><td class=\"td\">'
				            	+ output.clientLocationNo + '</td><td class=\"td\">' 
				            	+ output.clientName	+ '</td><td class=\"td\">'
				            	+ output.nickName + '</td><td class=\"td\">'
				            	+ output.transactionType + '</td><td class=\"td\">'
				            	+ output.charges + '</td><td class=\"td\">'
				            	+ output.productId + '</td><td class=\"td\">'
				            	+ output.quantity + '</td><td class=\"td\">'
				            	+ output.rate + '</td><td class=\"td\">'
				            	+ output.amount + '</td><td class=\"td\">'
				            	+ output.defaultText + '</td><td class=\"td\">'
				            	+ output.referenceNo + '</td><td class=\"td\">'
				            	+ output.line + '</td><td class=\"td\">'
				            	+ output.processDate + '</td></tr>';
				            	console.log(output);
				            });
				            $('#slabtable').append(trHTML);
				    	  }
				    	  else{
				    		  $("#tablediv").hide();
				    		  alert("No data To Display. Please select proper date.");
				    	  }
			     },
			     error:function(xhr,status)
			     {
			         console.log("slabtable:"+status);
			     }
		    }); 
		});
		
		$("#submitBtn2").click(function(){
			 var jsonData={
						"CLIENT_ID":$('#clientId').val(),
						"CLIENT_NAME":$('#clientName').val(),
						"MONTH":null,
						"YEAR":null
				};	
			$.ajax({
			       type: "post",
			       url:"outputServlet",
			       data:{dataSubmit:JSON.stringify(jsonData)},   
			       datatype:"json",
			       success:function(data, textStatus, jqXHR)
		            {
			    	 		             
				    	   $("#slabtable").find("tr:gt(0)").remove();
				    	   var trHTML = '';	
				    	   $('#tablediv').show();
				    	   console.log("submit:"+data);
				    	   console.log("length :"+data.length);
				    	  if(data.length > 0){
				            $.each(data, function(index, output) {  
				            	trHTML += '<tr><td class=\"td\" >' + output.srNumber + '</td><td class=\"td\">' 
				            	+ output.businessUnit + '</td><td class=\"td\" >' 
				            	+ output.clientId + '</td><td class=\"td\">'
				            	+ output.clientLocationNo + '</td><td class=\"td\">' 
				            	+ output.clientName	+ '</td><td class=\"td\">'
				            	+ output.nickName + '</td><td class=\"td\">'
				            	+ output.transactionType + '</td><td class=\"td\">'
				            	+ output.charges + '</td><td class=\"td\">'
				            	+ output.productId + '</td><td class=\"td\">'
				            	+ output.quantity + '</td><td class=\"td\">'
				            	+ output.rate + '</td><td class=\"td\">'
				            	+ output.amount + '</td><td class=\"td\">'
				            	+ output.defaultText + '</td><td class=\"td\">'
				            	+ output.referenceNo + '</td><td class=\"td\">'
				            	+ output.line + '</td><td class=\"td\">'
				            	+ output.processDate + '</td></tr>';
				            	console.log(output);
				            });
				            $('#slabtable').append(trHTML);
				    	  }
				    	  else{
				    		  $("#tablediv").hide();
				    		  alert("No data To Display. Please select proper date.");
				    	  }
			     },
			     error:function(xhr,status)
			     {
			         console.log("slabtable:"+status);
			     }
		    }); 
		});
	
});
</script>

</head>
<body>
<form>
<img src="/Finance/images/Experian_logo.png"/>
<h3 class="heading">All processed Entries</h3>
<div>
	<div class="chkLabel radioLabel"><input type="radio" name ="rad" class="radioFlag" rel="1"  id="MONTH_YEAR_SEARCH"/>Search From Month and Year</div>
	<div class="chkLabel radioLabel"><input type="radio" name ="rad" class="radioFlag" rel="2" id="ID_NAME_SEARCH" >Search From Client Id and Client Name</div>
	<div class="clear"></div>
</div>

<div id="MONTH_YEAR_SEARCH_DIV" class="hiddenDiv divLayout divLayout radioDiv" rel="1">
<div><div class="labels"><span>Month:</span></div><select id="month"></select></div>
<div><div class="labels"><span>Year:</span></div><select id="year"></select></div>
<div class="buttondiv"><input type="button" value="Submit" id="submitBtn1" style="float: left;"></div>
<div class="clear"></div>
</div>

<div id="ID_NAME_SEARCH_DIV" class="hiddenDiv divLayout divLayout radioDiv" rel="2">
<div><div class="labels"><span>Client Name:</span></div><select id="clientName"></select></div>
<div><div class="labels"><span>Client Id:</span></div><select id="clientId"></select></div>
<div class="buttondiv"><input type="button" value="Submit" id="submitBtn2" style="float: left;"></div>
<div class="clear"></div>
</div>

<!-- <div class="lable">Please provide client id or client name</div> -->


<div id="tablediv">
<table cellspacing="0" id="slabtable" class="table"> 
    <tr class="tr"> 
		<th class="th" scope="col">SR_NO</th>
		<th class="th" scope="col">BUSINESS_UNIT</th>
		<th class="th" scope="col">CLIENT_ID</th>
		<th class="th" scope="col">CLIENT_LOCATION_NO</th>
		<th class="th" scope="col">CLIENT_NAME</th>
		<th class="th" scope="col">NICK_NAME</th>
		<th class="th" scope="col">TRANSACTION_TYPE</th>
		<th class="th" scope="col">CHARGES</th>
		<th class="th" scope="col">PRODUCT_ID</th>
		<th class="th" scope="col">QUANTITY</th>
		<th class="th" scope="col">RATE</th>
		<th class="th" scope="col">AMOUNT</th>
		<th class="th" scope="col">DEFAULT_TEXT</th>
		<th class="th" scope="col">REFERENCE_NO</th>
		<th class="th" scope="col">LINE</th>
		<th class="th" scope="col">PROCESS_DATE</th>
    </tr> 
</table>
</div>
<div class="buttondiv"><input type="button" value="Back" onclick="history.go(-1);"/></div>
</form>
<jsp:include page="footer.jsp"/>
</body>
</html>