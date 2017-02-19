function validate(abc){
		if($(abc).val()==""){
			$(abc).addClass("error");
			return false;
		}
		$(abc).removeClass("error");
		return true;
	}
function setFlags(jsonData,abc){
	var id =$(abc).attr("id");
	if ($(abc).prop("checked")==true){
		jsonData[id]="Y";
	}
	else{
		jsonData[id]="N";
	}
}
function validateNumber(abc){
	
	if(!$.isNumeric($(abc).val())){
		$(abc).addClass("error");
		
		return false;
	}
	$(abc).removeClass("error");
	return true;
}

function validationParticipant(){
	var result=true;
	if ((!validate($('#txtFName'))) && result ==true){
		result=false;
	}if ((!validate($('#txtMName'))) && result ==true){
		result=false;
	}if ((!validate($('#txtLName'))) && result ==true){
		result=false;
	}if ((!validate($('#txtDateOfBirth'))) && result ==true){
		result=false;
	}if ((!validateNumber($('#txtPhone'))) && result ==true){
		result=false;
	}if ((!validateNumber($('#txtEmerPhone'))) && result ==true){
		result=false;
	}if ((!validate($('#txtAdd1'))) && result ==true){
		result=false;
	}if ((!validate($('#txtAdd2'))) && result ==true){
		result=false;
	}if ((!validate($('#txtCity'))) && result ==true){
		result=false;
	}if ((!validate($('#txtState'))) && result ==true){
		result=false;
	}if ((!validateNumber($('#txtPincode'))) && result ==true){
		result=false;
	}if ((!validate($('#txtSch'))) && result ==true){
		result=false;
	}if ((!validate($('#selectGender'))) && result ==true){
		result=false;
	}if ((!validateNumber($('#txtSchPincode'))) && result ==true){
		result=false;
	}
	
	return result;
}
function validations(){
	//alert("validationsMeter");
	var result=true;
	if ( $('#searchID') == null && $('#searchID') == "" && $('#searchFName') == "" &&  $('#searchMName') == "" && $('#searchLName') == "" && $('#searchPhone') == ""){
		 result=false;
	}else if ($('#searchID') == "" && !validateNumber($('#searchID'))){
		 result=false;
	}else if ($('#searchFName') == "" && !validate($('#searchFName'))){
		 result=false;
	}else if ($('#searchMName') == "" && !validate($('#searchMName'))){
		 result=false;
	}else if ($('#searchLName') == "" && !validate($('#searchLName'))){
		 result=false;
	}else if ($('#searchPhone') == "" && !validate($('#searchPhone'))){
		 result=false;
	}
	//alert("Error : Please Enter atlest one parameter to search ");
	return result;
}
	