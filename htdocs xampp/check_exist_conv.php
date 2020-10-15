<?php
require "connect.php";

$id_client=$_POST["id_client"];
$id_service=$_POST["id_service"];


$response=array();
	
$sql= "SELECT * FROM conversation WHERE service like '$id_service' and client like '$id_client'";
	
$result= mysqli_query($conn, $sql);//it executes the sql query and connects to the DB
	
if(mysqli_num_rows($result) >0){

	while($row = mysqli_fetch_assoc($result)){
		array_push($response,$row);
	}
	
	print(json_encode($response));
}
	
else{
		echo "not_exist";
}
	


?>

