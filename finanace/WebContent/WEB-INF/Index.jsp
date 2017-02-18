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

<script type="text/javascript">
$(document).ready(function(){
	$("#NO_MONTHS_OF_COMMITMENT").change(function(){
		if($(this).val()!=undefined ||$(this).val()!=null){
		var start = $('#CONTRACT_START_DATE').val();
     	var noMonths = $('#NO_MONTHS_OF_COMMITMENT').val();
	 	var date = new Date(start);
     	var d = date.getDate();
	 	var m = date.getMonth();
     	var y = date.getFullYear();
	 	var edate= new Date(y, m, d+parseInt(noMonths));
	 	var months=["Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"];
	 	d=edate.getDate();
	 	m=months[edate.getMonth()];
	 	y=edate.getFullYear();
	 	$('#CONTRACT_END_DATE').val(d+"-"+m+"-"+y);
     }
	 
	});
	$("#submit").click(function(){
		if(validation()){
			var jsonData={
					"LEGAL_ENTITY":$('#LEGAL_ENTITY').val(),
					"BUSINESS_UNIT":$('#BUSINESS_UNIT').val(),
					"CLIENT_ID":$('#CLIENT_ID').val(),
					"CLIENT_LOCATION_NO":$('#CLIENT_LOCATION_NO').val(),
					"CLIENT_NAME":$('#CLIENT_NAME').val(),
					"PRODUCT_ID":$('#PRODUCT_ID').val(),
					"PRODUCT_NAME":$('#PRODUCT_NAME').val(),
					"CATEGORY":$('#CATEGORY').val(),
					"CONTRACT_START_DATE":$('#CONTRACT_START_DATE').val(),
					"CONTRACT_END_DATE":$('#CONTRACT_END_DATE').val(),
					"AR_PROCESS_DATE":$('#AR_PROCESS_DATE').val(),
					"ONE_TIME_SETUP_CHARGE_DATE":$('#ONE_TIME_SETUP_CHARGE_DATE').val(),
					"ONE_TIME_SETUP_FEE":$('#ONE_TIME_SETUP_FEE').val(),
					"ADDITIONAL_SERVICE_CHARGES":$('#ADDITIONAL_SERVICE_CHARGES').val(),
					"EARLY_TERMINATION_DATE":$('#EARLY_TERMINATION_DATE').val(),
					"EARLY_TERMINATION_MINIMUM_VOLUME":$('#EARLY_TERMINATION_MINIMUM_VOLUME').val(),
					"PRO_RATA_CHARGES_FOR_TERMINATION":$('#PRO_RATA_CHARGES_FOR_TERMINATION').val(),
					"NO_MONTHS_OF_COMMITMENT":$('#NO_MONTHS_OF_COMMITMENT').val(),
					"COMMITMENT_QTY":$('#COMMITMENT_QTY').val(),
					"DISCOUNT_START_DATE":$('#DISCOUNT_START_DATE').val(),
					"DISCOUNT_END_DATE":$('#DISCOUNT_END_DATE').val(),
					"DISCOUNT_RATE":$('#DISCOUNT_RATE').val(),
					"DISCOUNT_EXTENSION_END_DATE":$('#DISCOUNT_EXTENSION_END_DATE').val(),
					"SPECIAL_START_DATE":$('#SPECIAL_START_DATE').val(),
					"SPECIAL_END_DATE":$('#SPECIAL_END_DATE').val(),
					"SPECIAL_RATE":$('#SPECIAL_RATE').val(),
					"WARM_UP_START_DATE":$('#WARM_UP_START_DATE').val(),
					"WARM_UP_END_DATE":$('#WARM_UP_END_DATE').val(),
					"WARM_UP_EXTENSION_FLAG":$('#WARM_UP_EXTENSION_FLAG').val(),
					"WARM_UP_EXTENSION_END_DATE":$('#WARM_UP_EXTENSION_END_DATE').val(),
					"MIN_MONTHLY_SLAB_VOLUME":$('#MIN_MONTHLY_SLAB_VOLUME').val(),
					"MAX_MONTHLY_SLAB_VOLUME":$('#MAX_MONTHLY_SLAB_VOLUME').val(),
					"MONTHLY_RATE":$('#MONTHLY_RATE').val(),
					"MIN_QUARTERLY_SLAB_VOLUME":$('#MIN_QUARTERLY_SLAB_VOLUME').val(),
					"MAX_QUARTERLY_SLAB_VOLUME":$('#MAX_QUARTERLY_SLAB_VOLUME').val(),
					"QUARTERLY_RATE":$('#QUARTERLY_RATE').val(),
					"MIN_SEMI_ANNUAL_SLAB_VOLUME":$('#MIN_SEMI_ANNUAL_SLAB_VOLUME').val(),
					"MAX_SEMI_ANNUAL_SLAB_VOLUME":$('#MAX_SEMI_ANNUAL_SLAB_VOLUME').val(),
					"SEMI_ANNUAL_SLAB_RATE":$('#SEMI_ANNUAL_SLAB_RATE').val(),
					"MIN_ANNUAL_SLAB_VOLUME":$('#MIN_ANNUAL_SLAB_VOLUME').val(),
					"MAX_ANNUAL_SLAB_VOLUME":$('#MAX_ANNUAL_SLAB_VOLUME').val(),
					"ANNUAL_SLAB_RATE":$('#ANNUAL_SLAB_RATE').val(),
					"PROCESSED_VOLUME_TILL_DATE":$('#PROCESSED_VOLUME_TILL_DATE').val(),
					"PROCESSED_VOLUME_QUARTER":$('#PROCESSED_VOLUME_QUARTER').val(),
					"PROCESSED_VOLUME_SEMI_ANNUAL":$('#PROCESSED_VOLUME_SEMI_ANNUAL').val(),
					"PROCESSED_VOLUME_ANNUAL":$('#PROCESSED_VOLUME_ANNUAL').val(),
					"PROCESSED_VOLUME_UPDATE_DATE":$('#PROCESSED_VOLUME_UPDATE_DATE').val(),
					"ONE_TIME_SETUP_FLAG":"N",
					"DISCOUNT_RATE_FLAG":"N",
					"DISCOUNT_EXTENSION_FLAG":"N",
					"SPECIAL_RATE_FLAG":"N",
					"WARM_UP_FLAG":"N",
					"WARM_UP_EXTENSION_FLAG":"N",
					"MONTHLY_SLAB_FLAG":"N",
					"QUARTERLY_SLAB_FLAG":"N",
					"SEMI_ANNUAL_SLAB_FLAG":"N",
					"ANNUAL_SLAB_FLAG":"N",
					"EARLY_TERMINATION_FLAG":"N"
			}
			setFlags(jsonData,$('#ONE_TIME_SETUP_FLAG'));
			setFlags(jsonData,$('#EARLY_TERMINATION_FLAG'));
			setFlags(jsonData,$('#DISCOUNT_RATE_FLAG'));
			setFlags(jsonData,$('#DISCOUNT_EXTENSION_FLAG'));
			setFlags(jsonData,$('#SPECIAL_RATE_FLAG'));
			setFlags(jsonData,$('#WARM_UP_FLAG'));
			setFlags(jsonData,$('#WARM_UP_EXTENSION_FLAG'));
			setFlags(jsonData,$('#MONTHLY_SLAB_FLAG'));
			setFlags(jsonData,$('#QUARTERLY_SLAB_FLAG'));
			setFlags(jsonData,$('#SEMI_ANNUAL_SLAB_FLAG'));
			setFlags(jsonData,$('#ANNUAL_SLAB_FLAG'));
			$.ajax({
			       type: "post",
			       url:"hello",
			       data:{dataSend:JSON.stringify(jsonData)},   
			       datatype:"json",
			       success:function(data, textStatus, jqXHR)
		            {
		           var e=jQuery.parseJSON(data);
		              if(e.status==="fails"){
			    		   alert("Transaction Fails");
			    	   }
			    	   else if(e.status==="already"){
			    		   alert("Data is Already Inserted");   
			    	   }
			    	   else if(e.status==="Inserted"){
			    		alert("Data is inserted");
			    		$("input[name='text']").each(function(){$(this).val("")});
			    		
			    	   }
		               
		            },
		            error:function(xhr,status)
		            {
		                 console.log(status);
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
	//$("#CONTRACT_START_DATE").datepicker("options","dateFormat","d M, y");
	
    
$("input[name ='rad']").click(function(){
	var rel=$(this).attr("rel");
	$(".radioDiv").hide();
	$(".radioDiv[rel='"+rel+"']").show(500);
	$(".radioDiv").find("input[type='text']").each(function(){$(this).val("")});
});
$("input[name='slab']").click(function(){
	var rel=$(this).attr("rel");
	$(".slabDiv").hide();
	$(".slabDiv[rel='"+rel+"']").show(500);
	$(".slabDiv").find("input[type='text']").each(function(){$(this).val("")});
});

	$(".chkBoxFlag").click(function(){
		
		if ($(this).prop("checked")==true){
			$(this).parent().parent().next().fadeIn(500);
		}else{
	    	$(this).parent().parent().next().fadeOut(500);
	    	
	    }
		
	});
});	

</script>
<style>

</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<img src="/Finance/images/Experian_logo.png"/>
	<form  method="post" id="target">
	<div class="container">
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
<div>
	<div class="labels">PRODUCT NAME<span class="astrik">*</span> :</div>
	<div class=""><input type="text" name="txtPRODUCT_NAME" id="PRODUCT_NAME" /></div>
</div>
<div>
	<div class="labels">CATEGORY<span class="astrik">*</span> :</div>
	<div class=""><input type="text" name="txtCATEGORY" id="CATEGORY" /></div>
</div>
<div>
	<div class="labels">CONTRACT START DATE<span class="astrik">*</span> :</div>
	<div class=""><input type="text" class="dateTimePicker1" name="txtCONTRACT_START_DATE" id="CONTRACT_START_DATE" /></div>
</div>
<div>
	<div class="labels">CONTRACT END DATE<span class="astrik">*</span> :</div>
	<div class=""><input type="text" class="dateTimePicker1"  name="txtCONTRACT_END_DATE" id="CONTRACT_END_DATE" /></div>
</div>
<div>
	<div class="labels">AR PROCESS DATE :</div>
	<div class=""><input type="text" class="dateTimePicker1" name="txtAR_PROCESS_DATE" id="AR_PROCESS_DATE" /></div>
</div>
<div>
	<div class="labels">NO MONTHS OF COMMITMENT :</div>
	<div class=""><input type="text" name="txtNO_MONTHS_OF_COMMITMENT" id="NO_MONTHS_OF_COMMITMENT" /></div>
</div>
<div>
	<div class="labels">COMMITMENT QTY :</div>
	<div class=""><input type="text" name="txtCOMMITMENT_QTY" id="COMMITMENT_QTY" /></div>
</div>
<div>
	<div class="chkLabel radioLabel"><input type="radio" name ="rad" class="radioFlag" rel="1"  id="ONE_TIME_SETUP_FLAG"/>ONE TIME SETUP FLAG :</div>
	<div class="chkLabel radioLabel"><input type="radio" class="radioFlag" rel="2" id="ADDITIONAL_SERVICE_CHARGES_FLAG" name ="rad">ADDITIONAL SERVICE CHARGES FLAG</div>
	<div class="chkLabel radioLabel"><input type="radio" rel="3" class="radioFlag" id="regular" name ="rad">Regular</div>
	<div class="clear"></div>
</div>
<div id="ONE_TIME_SETUP_Div" class="hiddenDiv divLayout divLayout radioDiv" rel="1">
<div>
	<div class="labels">ONE TIME SETUP CHARGE DATE :</div>
	<div class=""><input type="text"class="dateTimePicker1" name="txtONE_TIME_SETUP_CHARGE_DATE" id="ONE_TIME_SETUP_CHARGE_DATE" /></div>
</div>
<div>
	<div class="labels">ONE TIME SETUP FEE :</div>
	<div class=""><input type="text" name="txtONE_TIME_SETUP_FEE" id="ONE_TIME_SETUP_FEE" /></div>
</div>
<div class="clear"></div>
</div>

<div id="ADDITIONAL_SERVICE_CHARGES_div" class="hiddenDiv divLayout divLayout radioDiv" rel="2">
<div>
	<div class="labels">ADDITIONAL_SERVICE_CHARGES :</div>
	<div class=""><input type="text" name="txtADDITIONAL_SERVICE_CHARGES" id="ADDITIONAL_SERVICE_CHARGES" /></div>
</div>
</div>

<div id="regular_div" class="hiddenDiv divLayout divLayout radioDiv" rel="3">
<div>
	<div class="chkLabel"><input type="checkbox" class="chkBoxFlag" id="EARLY_TERMINATION_FLAG">EARLY TERMINATION FLAG</div>
</div>
<div id ="EARLY_TERMINATION_FLAG_div" class="hiddenDiv divLayout">
<div>
	<div class="labels">EARLY TERMINATION DATE :</div>
	<div class=""><input type="text" class="dateTimePicker1" name="txtEARLY_TERMINATION_DATE" id="EARLY_TERMINATION_DATE" /></div>
</div>

<div>
	<div class="labels">EARLY TERMINATION MINIMUM VOLUME :</div>
	<div class=""><input type="text" name="txtEARLY_TERMINATION_MINIMUM_VOLUME" id="EARLY_TERMINATION_MINIMUM_VOLUME" /></div>
</div>
<div>
	<div class="labels">PRO RATA CHARGES FOR TERMINATION :</div>
	<div class=""><input type="text" name="txtPRO_RATA_CHARGES_FOR_TERMINATION" id="PRO_RATA_CHARGES_FOR_TERMINATION" /></div>
</div>
</div>
<div>
	<div class="chkLabel"><input type="checkbox" class="chkBoxFlag" id="DISCOUNT_RATE_FLAG">DISCOUNT RATE FLAG :</div>
</div>
<div id="DISCOUNT_RATE_FLAG_div" class="hiddenDiv divLayout">
<div>
	<div class="labels">DISCOUNT START DATE :</div>
	<div class=""><input type="text" class="dateTimePicker1" name="txtDISCOUNT_START_DATE" id="DISCOUNT_START_DATE" /></div>
</div>
<div>
	<div class="labels">DISCOUNT END DATE :</div>
	<div class=""><input type="text" class="dateTimePicker1" name="txtDISCOUNT_END_DATE" id="DISCOUNT_END_DATE" /></div>
</div>
<div>
	<div class="labels">DISCOUNT RATE :</div>
	<div class=""><input type="text" name="txtDISCOUNT_RATE" id="DISCOUNT_RATE" /></div>
</div>

<div>
	<div class="chkLabel"><input type="checkbox" class="chkBoxFlag" id="DISCOUNT_EXTENSION_FLAG">DISCOUNT EXTENSION FLAG :</div>
</div>
<div id ="DISCOUNT_EXTENSION_FLAG_div" class="hiddenDiv divLayout">
<div>
	<div class="labels">DISCOUNT EXTENSION END DATE :</div>
	<div class=""><input type="text" class="dateTimePicker1" name="txtDISCOUNT_EXTENSION_END_DATE" id="DISCOUNT_EXTENSION_END_DATE" /></div>
</div>
</div>
</div>
<div>
	<div class="chkLabel"><input type="checkbox" class="chkBoxFlag" id="SPECIAL_RATE_FLAG">SPECIAL RATE FLAG :</div>
</div>
<div id="SPECIAL_RATE_FLAG_div" class="hiddenDiv divLayout">
<div>
	<div class="labels">SPECIAL START DATE :</div>
	<div class=""><input type="text" name="txtSPECIAL_START_DATE" class="dateTimePicker1" id="SPECIAL_START_DATE" /></div>
</div>
<div>
	<div class="labels">SPECIAL END DATE :</div>
	<div class=""><input type="text" name="txtSPECIAL_END_DATE" class="dateTimePicker1" id="SPECIAL_END_DATE" /></div>
</div>
<div>
	<div class="labels">SPECIAL RATE :</div>
	<div class=""><input type="text" name="txtSPECIAL_RATE" id="SPECIAL_RATE" /></div>
</div>
</div>
<div>
	<div class="chkLabel"><input type="checkbox" class="chkBoxFlag" id="WARM_UP_FLAG">WARM UP FLAG :</div>
</div>
<div id="WARM_UP_FLAG_div" class="hiddenDiv divLayout">
<div>
	<div class="labels">WARM UP START DATE :</div>
	<div class=""><input type="text" name="txtWARM_UP_START_DATE" class="dateTimePicker1" id="WARM_UP_START_DATE" /></div>
</div>
<div>
	<div class="labels">WARM UP END DATE :</div>
	<div class=""><input type="text" name="txtWARM_UP_END_DATE" class="dateTimePicker1" id="WARM_UP_END_DATE" /></div>
</div>

<div>
	<div class="chkLabel"><input type="checkbox" class="chkBoxFlag"  id="WARM_UP_EXTENSION_FLAG">WARM UP EXTENSION FLAG :</div>
</div>
<div id="WARM_UP_EXTENSION_FLAG_div" class="hiddenDiv divLayout">
<div>
	<div class="labels">WARM UP EXTENSION END DATE :</div>
	<div class=""><input type="text" name="txtWARM_UP_EXTENSION_END_DATE" class="dateTimePicker1" id="WARM_UP_EXTENSION_END_DATE" /></div>
</div>
</div>
</div>
<div>
<div>
	<div class="radioLabel slabGroup"><input class="radioFlag" type="radio" id="MONTHLY_SLAB_FLAG" name="slab" rel="1">MONTHLY SLAB FLAG</div>
	<div class="radioLabel slabGroup"><input class="radioFlag" type="radio" id="QUARTERLY_SLAB_FLAG" name="slab" rel="2">QUARTERLY SLAB FLAG</div>
	<div class="clear"></div>
</div>
<div>
	<div class="radioLabel slabGroup"><input class="radioFlag" type="radio" id="SEMI_ANNUAL_SLAB_FLAG" name="slab" rel="3" >SEMI ANNUAL SLAB FLAG</div>
	<div class="radioLabel slabGroup"><input class="radioFlag" type="radio" id="ANNUAL_SLAB_FLAG" name="slab" rel="4" >ANNUAL SLAB FLAG</div>
	<div class="clear"></div>
</div>
<div class="clear"></div>	
</div>
<div id="MONTHLY_SLAB_FLAG_div" class="hiddenDiv divLayout slabDiv" rel="1">
<div>
	<div class="labels">MIN MONTHLY SLAB VOLUME :</div>
	<div class=""><input type="text" name="txtMIN_MONTHLY_SLAB_VOLUME" id="MIN_MONTHLY_SLAB_VOLUME" /></div>
</div>
<div>
	<div class="labels">MAX MONTHLY SLAB VOLUME :</div>
	<div class=""><input type="text" name="txtMAX_MONTHLY_SLAB_VOLUME" id="MAX_MONTHLY_SLAB_VOLUME" /></div>
</div>
<div>
	<div class="labels">MONTHLY RATE :</div>
	<div class=""><input type="text" name="txtMONTHLY_RATE" id="MONTHLY_RATE" /></div>
</div>
</div>

<div id="QUARTERLY_SLAB_FLAG_div" class="hiddenDiv divLayout slabDiv" rel="2">
<div>
	<div class="labels">MIN QUARTERLY SLAB VOLUME :</div>
	<div class=""><input type="text" name="txtMIN_QUARTERLY_SLAB_VOLUME" id="MIN_QUARTERLY_SLAB_VOLUME" /></div>
</div>
<div>
	<div class="labels">MAX QUARTERLY SLAB VOLUME :</div>
	<div class=""><input type="text" name="txtMAX_QUARTERLY_SLAB_VOLUME" id="MAX_QUARTERLY_SLAB_VOLUME" /></div>
</div>
<div>
	<div class="labels">QUARTERLY RATE :</div>
	<div class=""><input type="text" name="txtQUARTERLY_RATE" id="QUARTERLY_RATE" /></div>
</div>
<div>
	<div class="labels">PROCESSED VOLUME QUARTER :</div>
	<div class=""><input type="text" name="txtPROCESSED_VOLUME_QUARTER" id="PROCESSED_VOLUME_QUARTER" /></div>
</div>
</div>

<div id="SEMI_ANNUAL_SLAB_FLAG_div" class="hiddenDiv divLayout slabDiv" rel="3">
<div>
	<div class="labels">MIN SEMI ANNUAL SLAB VOLUME :</div>
	<div class=""><input type="text" name="txtMIN_SEMI_ANNUAL_SLAB_VOLUME" id="MIN_SEMI_ANNUAL_SLAB_VOLUME" /></div>
</div>
<div>
	<div class="labels">MAX SEMI ANNUAL SLAB VOLUME :</div>
	<div class=""><input type="text" name="txtMAX_SEMI_ANNUAL_SLAB_VOLUME" id="MAX_SEMI_ANNUAL_SLAB_VOLUME" /></div>
</div>
<div>
	<div class="labels">SEMI ANNUAL SLAB RATE :</div>
	<div class=""><input type="text" name="txtSEMI_ANNUAL_SLAB_RATE" id="SEMI_ANNUAL_SLAB_RATE" /></div>
</div>
<div>
	<div class="labels">PROCESSED VOLUME SEMI ANNUAL :</div>
	<div class=""><input type="text" name="txtPROCESSED_VOLUME_SEMI_ANNUAL" id="PROCESSED_VOLUME_SEMI_ANNUAL" /></div>
</div>
</div>


<div id="ANNUAL_SLAB_FLAG_div" class="hiddenDiv divLayout slabDiv" rel="4">
<div>
	<div class="labels">MIN ANNUAL SLAB VOLUME :</div>
	<div class=""><input type="text" name="txtMIN_ANNUAL_SLAB_VOLUME" id="MIN_ANNUAL_SLAB_VOLUME" /></div>
</div>
<div>
	<div class="labels">MAX ANNUAL SLAB VOLUME :</div>
	<div class=""><input type="text" name="txtMAX_ANNUAL_SLAB_VOLUME" id="MAX_ANNUAL_SLAB_VOLUME" /></div>
</div>
<div>
	<div class="labels">ANNUAL SLAB RATE :</div>
	<div class=""><input type="text" name="txtANNUAL_SLAB_RATE" id="ANNUAL_SLAB_RATE" /></div>
</div>
<div>
	<div class="labels">PROCESSED VOLUME ANNUAL :</div>
	<div class=""><input type="text" name="txtPROCESSED_VOLUME_ANNUAL" id="PROCESSED_VOLUME_ANNUAL" /></div>
</div>
</div>
<div>
	<div class="labels">PROCESSED VOLUME TILL DATE :</div>
	<div class=""><input type="text" name="txtPROCESSED_VOLUME_TILL_DATE" class="dateTimePicker1" id="PROCESSED_VOLUME_TILL_DATE" /></div>
</div>
<div>
	<div class="labels">PROCESSED VOLUME UPDATE DATE :</div>
	<div class=""><input type="text" name="txtPROCESSED_VOLUME_UPDATE_DATE" class="dateTimePicker1" id="PROCESSED_VOLUME_UPDATE_DATE" /></div>
</div>
<div class="clear"></div>
</div>
<div>
	<div class="buttondiv"><input type="button" name="txtsubmit" id="submit" value="Submit" /></div>
</div>
</div>
</form>
<jsp:include page="footer.jsp"/>
</body>
</html>