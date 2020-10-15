<?php
require "connect.php";





$id_service=$_POST["id_service"];

$response=array();

$mysql_qry="SELECT * FROM conversation WHERE service='$id_service'";

$result= mysqli_query($conn, $mysql_qry);//it executes the sql query and connects to the DB

if(mysqli_num_rows($result) >0){

	while($row = mysqli_fetch_assoc($result)){
		array_push($response,$row);
	}
	
	print(json_encode($response));
}

else{
	echo "no_conversations";
}

?>