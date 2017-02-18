<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" session="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/jquery-ui.structure.css">
<link rel="stylesheet" type="text/css" href="css/jquery-ui.theme.css">

<link rel="stylesheet" type="text/css" href="css/style.css">
<script type="text/javascript" src="js/jquery.1.7.2.min.js"></script>
<script type="text/javascript" src="js/validationFile.js"></script>
<script type="text/javascript" src="js/jquery-ui.js"></script> 
<script type="text/javascript" src="js/jquery.currency.js"></script> 

<style>

</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>New Slab Entry</title>
</head>
<body>
	<form  method="post" id="target">
	<img src="/Finance/images/Experian_logo.png"/>
	<div class="container">
	<h3 class="heading">New Slab Entry</h3>
	<div>
	<div class="labels">LEGAL ENTITY<span class="astrik">*</span> :</div>
	<div class=""><input type="text" name="txtLEGAL_ENTITY" id="LEGAL_ENTITY" /></div>
</div>
<div>
	<div class="labels">BUSINESS UNIT<span class="astrik">*</span> :</div>
	<div class=""><input type="text" name="txtBUSINESS_UNIT" id="BUSINESS_UNIT" /></div>
</div>
<div>
	<div class="labels">CLIENT ID<span class="astrik">*</span> :</div>
	<div class=""><input type="text" name="txtCLIENT_ID" id="CLIENT_ID" /></div>
</div>
<div>
	<div class="labels">CLIENT LOCATION NO<span class="astrik">*</span> :</div>
	<div class=""><input type="text" name="txtCLIENT_LOCATION_NO" id="CLIENT_LOCATION_NO" /></div>
</div>
<div>
	<div class="labels">CLIENT NAME<span class="astrik">*</span> :</div>
	<div class=""><input type="text" name="txtCLIENT_NAME" id="CLIENT_NAME" /></div>
</div>
<div>
	<div class="labels">PRODUCT ID<span class="astrik">*</span> :</div>
	<div class=""><input type="text" name="txtPRODUCT_ID" id="PRODUCT_ID" /></div>
</div>
<!-- <div>
	<div class="labels">SLAB CATEGORY<span class="astrik">*</span> :</div>
	<div class=""><input type="text" name="txtSLAB_CATEGORY" id="SLAB_CATEGORY" /></div>
</div> -->
<!-- <div>
	<div class="labels">SLAB TYPE<span class="astrik">*</span> :</div>
	<div class=""><input type="text" name="txtSLAB_TYPE" id="SLAB_TYPE" /></div>
</div> -->
<div>
	<div class="labels">SLAB CATEGORY<span class="astrik">*</span> :</div>
	<div class=""><select id="SLAB_CATEGORY" name="txtSLAB_CATEGORY">
	<option value="NORMAL">NORMAL</option>
	<option value="WARM_UP">WARM UP</option>
	</select>
	</div>
</div>
<div>
	<div class="labels">SLAB TYPE<span class="astrik">*</span> :</div>
	<div class=""><select id="SLAB_TYPE" name="txtSLAB_TYPE">
	<option value="FIXED">FIXED</option>
	<option value="CUMULATIVE">CUMULATIVE</option>
	<option value="INCREMENTAL">INCREMENTAL</option>
	</select></div>
</div>
<div>
	<div class="labels">MIN SLAB VOLUME<span class="astrik">*</span> :</div>
	<div class=""><input type="text" name="txtMIN_SLAB_VOLUME" id="MIN_SLAB_VOLUME" /></div>
</div>
<div>
	<div class="labels">MAX SLAB VOLUME<span class="astrik">*</span> :</div>
	<div class=""><input type="text" name="txtMAX_SLAB_VOLUME" id="MAX_SLAB_VOLUME" /></div>
</div>
<div>
	<div class="labels">CHARGES<span class="astrik">*</span> :</div>
	<div class=""><input type="text" name="txtCHARGES" id="CHARGES" /></div>
</div>
<div>
	<div class="labels">ACTIVE<span class="astrik">*</span> :</div>
	<div class=""><input type="text" name="txtACTIVE" id="ACTIVE" /></div>
</div>
<div>
	<div class="labels">SLAB END DATE<span class="astrik">*</span> :</div>
	<div class=""><input type="text" class="dateTimePicker1" name="txtSLAB_END_DATE" id="SLAB_END_DATE" /></div>
</div>

	<table align="center">
		<tr>
			<td><div class="buttondiv"><input type="submit" value="Submit" id="submitBtn" style="float: right;"></div></td>
			<td><div class="buttondiv"><input type="button" value="Back" onclick="history.go(-1);" style="float: right;"/></div></td>
		</tr>
	</table>
</div>
	
	</form>
	<script type="text/javascript">

$(document).ready(function(){
	$("#submitBtn").click(function(){
		if(validationslab()){
			var jsonData={
					"LEGAL_ENTITY":$('#LEGAL_ENTITY').val(),
					"BUSINESS_UNIT":$('#BUSINESS_UNIT').val(),
					"CLIENT_ID":$('#CLIENT_ID').val(),
					"CLIENT_LOCATION_NO":$('#CLIENT_LOCATION_NO').val(),
					"CLIENT_NAME":$('#CLIENT_NAME').val(),
					"PRODUCT_ID":$('#PRODUCT_ID').val(),
					"SLAB_CATEGORY":$('#SLAB_CATEGORY').val(),
					"SLAB_TYPE":$('#SLAB_TYPE').val(),
					"MIN_SLAB_VOLUME":$('#MIN_SLAB_VOLUME').val(),
					"MAX_SLAB_VOLUME":$('#MAX_SLAB_VOLUME').val(),
					"CHARGES":$('#CHARGES').val(),
					"ACTIVE":$('#ACTIVE').val(),
					"SLAB_END_DATE":$('#SLAB_END_DATE').val()
			}
			$.ajax({
			       type: "post",
			       url:"insertSlabData",
			       data:{dataSend:JSON.stringify(jsonData)},   
			       datatype:"json",
			       success:function(data, textStatus, jqXHR)
		            {
		           var e=jQuery.parseJSON(data);
		           if(e.status==="Already"){
			    		 alert("The data is already present.\n If you want to edit this data please go to edit screen.");
			    	   }
			    	   else if(e.status==="Inserted"){
			    		   alert("Data Inserted");   
			    		  	window.location.reload(true);
			    		   //$("input[name='text']").each(function(){$(this).val("")});
			    	   }
		            },
		            error:function( xhr, textStatus, errorThrown )
		            {
		                var errText = "<b>Error "+xhr.status+" : "+xhr.responseText+" , "+textStatus+", "+errorThrown+" </b>";
		                alert(errText);
		               //$('#'+div).append(errText);
		            }
		            
		        });
			$(document).find("h1").remove();
		}	
		else{
			alert("Errors");
			//$( ".container" ).prepend("")
			$(document).find("h1").remove();
			$("<h1>").insertBefore( $( ".container" ) );
			$(document).find("h1").html("Please Fill the Required Fields marked with Red Border");
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
<jsp:include page="footer.jsp"/>	
</body>
</html>