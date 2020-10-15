<?php
require "connect.php";





$id_user=$_POST["id_user"];

$response=array();

$mysql_qry="SELECT * FROM prestataire WHERE user='$id_user'";

$result= mysqli_query($conn, $mysql_qry);//it executes the sql query and connects to the DB

if(mysqli_num_rows($result) >0){

	while($row = mysqli_fetch_assoc($result)){
		array_push($response,$row);
	}
	
	print(json_encode($response));
}

else{
	echo "no_prestataire";
}

?>