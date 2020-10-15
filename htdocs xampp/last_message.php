<?php
require "connect.php";

$id_client=$_POST["id_client"];
$id_service=$_POST["id_service"];
$id_sender=$_POST["id_sender"];

$response=array();
$mysql_qry="select MAX(hour) from message where client like '$id_client' and service like '$id_service' and id_sender <> '$id_sender'";
$result= mysqli_query($conn, $mysql_qry);//it executes the sql query and connects to the DB

if(mysqli_num_rows($result) >0){

	while($row = mysqli_fetch_assoc($result)){
		echo $row['MAX(hour)'];
	}
}

else{
	echo "no messages";
}






?>