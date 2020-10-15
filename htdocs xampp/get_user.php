<?php
require "connect.php";

$email=$_POST["mail"];

$response=array();
$mysql_qry="SELECT * FROM `user` WHERE mail like '$email'";
$result= mysqli_query($conn, $mysql_qry);//it executes the sql query and connects to the DB

if(mysqli_num_rows($result) >0){

	while($row = mysqli_fetch_assoc($result)){
		array_push($response,$row);
	}
	
	print(json_encode($response));
}

else{
	echo "error_return_id";
}

?>