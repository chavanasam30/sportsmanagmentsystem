<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>New Participant Entry</title>
</head>
<body>
	<form action="/newParticipantEntry" >
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
					<td><div class="buttondiv"><input type="button" value="Get Games" id="gameBtn" style="float: right;"></div></td>
				</tr>
				
			</table>
		</div>
		<div class="hiddenDiv" id="gameDiv">
			<div class="container">
				<table>
					<tr>
						<td><div class="labels">SELECT GAME NAMES YOU WANT TO PARTICIPATE: </div></td> 
						<td><div><select id="gameDropDown"></select></div></td>					
						<td><div class="buttondiv"><input type="button" value="Add" id="addBtn" style="float: right;"></div></td>	
					</tr>
				</table>
			</div>	
		</div>
	<h2>Name : ${message}</h2>
	
	</form>
	</body>
</html>