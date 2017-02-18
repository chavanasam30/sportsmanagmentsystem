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
function validationsMeter(){
	//alert("validationsMeter");
	var result=true;
	if ((!validate($('#CLIENT_NAME'))) && result ==true){
		result=false;
	}if ((!validate($('#TRANSACTION_TYPE'))) && result ==true){
		result=false;
	}if((!validate($('#CHARGES'))) && !validateNumber($('#CHARGES'))){
		result=false;
	}if ((!validate($('#QUANTITY'))) && !validateNumber($('#QUANTITY'))){
		result=false;
	}
	return result;
}
	function validation(){
		var result=true;
		if ((!validate($('#LEGAL_ENTITY'))) && result ==true){
			result=false;
		}if ((!validate($('#BUSINESS_UNIT'))) && result ==true){
			result=false;
		}if ((!validate($('#CLIENT_ID'))) && result ==true){
			result=false;
		}if ((!validate($('#CLIENT_LOCATION_NO'))) && result ==true){
			result=false;
		}if ((!validate($('#CLIENT_NAME'))) && result ==true){
			result=false;
		}if ((!validate($('#PRODUCT_ID'))) && result ==true){
			result=false;
		}if ((!validate($('#PRODUCT_NAME'))) && result ==true){
			result=false;
		}if ((!validate($('#CATEGORY'))) && result ==true){
			result=false;
		}if ((!validate($('#CONTRACT_START_DATE'))) && result ==true){
			result=false;
		}if ((!validate($('#CONTRACT_END_DATE'))) && result ==true){
			result=false;
		}
		if($('#ONE_TIME_SETUP_FLAG').prop("checked")){
			if ((!validate($('#ONE_TIME_SETUP_CHARGE_DATE')))){
				result=false;
			}
			if(validate($('#ONE_TIME_SETUP_FEE'))&&!validateNumber($('#ONE_TIME_SETUP_FEE'))){
				result=false;
			}	
		}
		if($('#ADDITIONAL_SERVICE_CHARGES_FLAG').prop("checked")){
			if(validate($('#ADDITIONAL_SERVICE_CHARGES'))&&!validateNumber($('#ADDITIONAL_SERVICE_CHARGES'))){
				result=false;
			}
		}
		if($('#regular').prop("checked")){
		if($('#EARLY_TERMINATION_FLAG_div').prop("checked")){
			
			if ((!validate($('#EARLY_TERMINATION_DATE')))){
				result=false;
			}
			if(validate($('#EARLY_TERMINATION_MINIMUM_VOLUME'))&&!validateNumber($('#EARLY_TERMINATION_MINIMUM_VOLUME'))){
				result=false;
			}
			
			if(validate($('#PRO_RATA_CHARGES_FOR_TERMINATION'))&&!validateNumber($('#PRO_RATA_CHARGES_FOR_TERMINATION'))){
				result=false;
			}
		}
		if($('#DISCOUNT_RATE_FLAG').prop("checked")){
			
			if ((!validate($('#DISCOUNT_START_DATE')))){
				result=false;
			}
			if ((!validate($('#DISCOUNT_END_DATE')))){
				result=false;
			}
			if(validate($('#DISCOUNT_RATE'))&&!validateNumber($('#DISCOUNT_RATE'))){
				result=false;
			}
			if($('#DISCOUNT_RATE_FLAG').prop("checked")){
				if (!validate($('#DISCOUNT_EXTENSION_END_DATE'))){
					result=false;
				}
			}
		}
		if($('#SPECIAL_RATE_FLAG').prop("checked")){
			
			if ((!validate($('#SPECIAL_START_DATE')))){
				result=false;
			}
			if ((!validate($('#SPECIAL_END_DATE')))){
				result=false;
			}
			if(validate($('#SPECIAL_RATE'))&&!validateNumber($('#SPECIAL_RATE'))){
				result=false;
			}
		}
		if($('#WARM_UP_FLAG').prop("checked")){
			
			if ((!validate($('#WARM_UP_START_DATE')))){
				result=false;
			}
			if ((!validate($('#WARM_UP_END_DATE')))){
				result=false;
			}
			if($('#WARM_UP_EXTENSION_FLAG').prop("checked")){
				if (!validate($('#WARM_UP_EXTENSION_END_DATE'))){
					result=false;
				}
			}
		}
		if($('#QUARTERLY_SLAB_FLAG').prop("checked")){
			if(validate($('#MIN_QUARTERLY_SLAB_VOLUME'))&&!validateNumber($('#MIN_QUARTERLY_SLAB_VOLUME'))){
				result=false;
			}
			
			if(validate($('#MAX_QUARTERLY_SLAB_VOLUME'))&&!validateNumber($('#MAX_QUARTERLY_SLAB_VOLUME'))){
				result=false;
			}
			if(validate($('#QUARTERLY_RATE'))&&!validateNumber($('#QUARTERLY_RATE'))){
				result=false;
			}
			if(validate($('#PROCESSED_VOLUME_QUARTER'))&&!validateNumber($('#PROCESSED_VOLUME_QUARTER'))){
				result=false;
			}
		}
		if($('#SEMI_ANNUAL_SLAB_FLAG').prop("checked")){
			if(validate($('#MIN_SEMI_ANNUAL_SLAB_VOLUME'))&&!validateNumber($('#MIN_SEMI_ANNUAL_SLAB_VOLUME'))){
				result=false;
			}
			
			if(validate($('#MAX_SEMI_ANNUAL_SLAB_VOLUME'))&&!validateNumber($('#MAX_SEMI_ANNUAL_SLAB_VOLUME'))){
				result=false;
			}
			if(validate($('#SEMI_ANNUAL_SLAB_RATE'))&&!validateNumber($('#SEMI_ANNUAL_SLAB_RATE'))){
				result=false;
			}
			if(validate($('#PROCESSED_VOLUME_SEMI_ANNUAL'))&&!validateNumber($('#PROCESSED_VOLUME_SEMI_ANNUAL'))){
				result=false;
			}
		}
		if($('#ANNUAL_SLAB_FLAG').prop("checked")){
			if(validate($('#MIN_ANNUAL_SLAB_VOLUME'))&&!validateNumber($('#MIN_ANNUAL_SLAB_VOLUME'))){
				result=false;
			}
			
			if(validate($('#MAX_ANNUAL_SLAB_VOLUME'))&&!validateNumber($('#MAX_ANNUAL_SLAB_VOLUME'))){
				result=false;
			}
			if(validate($('#PROCESSED_VOLUME_ANNUAL'))&&!validateNumber($('#PROCESSED_VOLUME_ANNUAL'))){
				result=false;
			}
			if(validate($('#ANNUAL_SLAB_RATE'))&&!validateNumber($('#ANNUAL_SLAB_RATE'))){
				result=false;
			}
		}
		if($('#MONTHLY_SLAB_FLAG').prop("checked")){
			if(validate($('#MIN_MONTHLY_SLAB_VOLUME'))&&!validateNumber($('#MIN_MONTHLY_SLAB_VOLUME'))){
				result=false;
			}
			
			if(validate($('#MAX_MONTHLY_SLAB_VOLUME'))&&!validateNumber($('#MAX_MONTHLY_SLAB_VOLUME'))){
				result=false;
			}
			if(validate($('#MONTHLY_RATE'))&&!validateNumber($('#MONTHLY_RATE'))){
				result=false;
			}
		}
		
		if ((!validate($('#PROCESSED_VOLUME_TILL_DATE')))){
			result=false;
		}
		if ((!validate($('#PROCESSED_VOLUME_UPDATE_DATE')))){
			result=false;
		}
	}
		/*if(validate($('#NO_MONTHS_OF_COMMITMENT'))&&!validateNumber($('#NO_MONTHS_OF_COMMITMENT'))){
			result=false;
		}
		
		if(validate($('#COMMITMENT_QTY'))&&!validateNumber($('#COMMITMENT_QTY'))){
			result=false;
		}
		if(validate($('#ONE_TIME_SETUP_FEE'))&&!validateNumber($('#ONE_TIME_SETUP_FEE'))){
			result=false;
		}
		if(validate($('#PRO_RATA_CHARGES_FOR_TERMINATION'))&&!validateNumber($('#PRO_RATA_CHARGES_FOR_TERMINATION'))){
			result=false;
		}
		if(validate($('#DISCOUNT_RATE'))&&!validateNumber($('#DISCOUNT_RATE'))){
			result=false;
		}
		if(validate($('#SPECIAL_RATE'))&&!validateNumber($('#SPECIAL_RATE'))){
			result=false;
		}
		if(validate($('#MIN_MONTHLY_SLAB_VOLUME'))&&!validateNumber($('#MIN_MONTHLY_SLAB_VOLUME'))){
			result=false;
		}
		if(validate($('#MAX_MONTHLY_SLAB_VOLUME'))&&!validateNumber($('#MAX_MONTHLY_SLAB_VOLUME'))){
			result=false;
		}
		if(validate($('#MONTHLY_RATE'))&&!validateNumber($('#MONTHLY_RATE'))){
			result=false;
		}
		if(validate($('#MIN_QUARTERLY_SLAB_VOLUME'))&&!validateNumber($('#MIN_QUARTERLY_SLAB_VOLUME'))){
			result=false;
		}
		if(validate($('#MAX_QUARTERLY_SLAB_VOLUME'))&&!validateNumber($('#MAX_QUARTERLY_SLAB_VOLUME'))){
			result=false;
		}
		if(validate($('#QUARTERLY_RATE'))&&!validateNumber($('#QUARTERLY_RATE'))){
			result=false;
		}
		if(validate($('#MIN_SEMI_ANNUAL_SLAB_VOLUME'))&&!validateNumber($('#MIN_SEMI_ANNUAL_SLAB_VOLUME'))){
			result=false;
		}
		if(validate($('#MAX_SEMI_ANNUAL_SLAB_VOLUME'))&&!validateNumber($('#MAX_SEMI_ANNUAL_SLAB_VOLUME'))){
			result=false;
		}
		if(validate($('#SEMI_ANNUAL_SLAB_RATE'))&&!validateNumber($('#SEMI_ANNUAL_SLAB_RATE'))){
			result=false;
		}
		if(validate($('#MIN_ANNUAL_SLAB_VOLUME'))&&!validateNumber($('#MIN_ANNUAL_SLAB_VOLUME'))){
			result=false;
		}
		if(validate($('#MAX_ANNUAL_SLAB_VOLUME'))&&!validateNumber($('#MAX_ANNUAL_SLAB_VOLUME'))){
			result=false;
		}
		if(validate($('#ANNUAL_SLAB_RATE'))&&!validateNumber($('#ANNUAL_SLAB_RATE'))){
			result=false;
		}
		if(validate($('#PROCESSED_VOLUME_TILL_DATE'))&&!validateNumber($('#PROCESSED_VOLUME_TILL_DATE'))){
			result=false;
		}
		if(validate($('#PROCESSED_VOLUME_QUARTER'))&&!validateNumber($('#PROCESSED_VOLUME_QUARTER'))){
			result=false;
		}
		if(validate($('#PROCESSED_VOLUME_SEMI_ANNUAL'))&&!validateNumber($('#PROCESSED_VOLUME_SEMI_ANNUAL'))){
			result=false;
		}
		if(validate($('#PROCESSED_VOLUME_ANNUAL'))&&!validateNumber($('#PROCESSED_VOLUME_ANNUAL'))){
			result=false;
		}
		if(validate($('#PROCESSED_VOLUME_UPDATE_DATE'))&&!validateNumber($('#PROCESSED_VOLUME_UPDATE_DATE'))){
			result=false;
		}
		if(validate($('#CLIENT_ID'))&&!validateNumber($('#CLIENT_ID'))){
			result=false;
		}*/
		
		/*if ((!validate($('#SLAB_CATEGORY'))) && result ==true){
			result=false;
		}if ((!validate($('#SLAB_TYPE'))) && result ==true){
			result=false;
		}if ((!validate($('#MIN_SLAB_VOLUME'))) && result ==true){
			result=false;
		}if ((!validate($('#MAX_SLAB_VOLUME'))) && result ==true){
			result=false;
		}if ((!validate($('#CHARGES'))) && result ==true){
			result=false;
		}if ((!validate($('#ACTIVE'))) && result ==true){
			result=false;
		}*/
		return result;
	}