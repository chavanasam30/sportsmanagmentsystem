<?php
$record = $_REQUEST['record'];
$servername = "localhost";
$username = "dervansp";
$password = "sandy3180";
$dbname = "dervansp_id606597_sport_database";


$conn = new mysqli($servername, $username, $password,$dbname);

if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
} 
$record=json_decode($record);
$vote=$record->partidetails;
$games=$record->games;

$sql =  "INSERT INTO `PARTICIPANT`(`PART_ID`, `FNAME`, `MNAME`, `LNAME`, `DOB`, `AGE`, `SCHOOL`, `ADDRESS_LINE1`, `ADDRESS_LINE2`, `STATE`, `CITY`, `PINCODE`, `SCHOOL_ADDRESS_LINE1`, `SCHOOL_ADDRESS_LINE2`, `SCHOOL_STATE`, `SCHOOL_CITY`, `SCHOOL_PINCODE`, `GENDER`, `PHONE`, `EMER_PHONE`, `EMAIL_ID`,`bankDetails`,`paymentdate`) VALUES (null,'".$vote->firstname."','".$vote->middlename."','".$vote->lastname."','".$vote->dob."','".$vote->age."','".$vote->nameOfSchoolOrClub."','".$vote->addr1."','".$vote->addr2."','".$vote->state."','".$vote->city."','".$vote->pincode."','".$vote->addressOfSchoolOrClub."','".$vote->address2OfSchoolOrClub."','".$vote->schoolstate."','".$vote->schoolcity."','".$vote->schoolpincode."','".$vote->gender."','".$vote->contactno."','".$vote->alternativeno."','".$vote->email."','".$vote->bankDetails."','".$vote->paymentdate."')";

if ($conn->query($sql) === TRUE) {
    $last_id = $conn->insert_id;
	foreach($games as $key => $jsons) { 
	$sql1 =  "INSERT INTO `PARTI_GAME`(`PARTI_GAME_ID`, `PART_ID`, `GAME_ID`) VALUES (null,".$last_id.",".$jsons->eventid.")";
		if ($conn->query($sql1) === TRUE) {
			
		}else {
			
		}
	}
    echo $last_id;
} else {
    echo '-1';
}
	


$conn->close();
?>
