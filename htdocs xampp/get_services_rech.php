<?php
require "connect.php";


$response=array();

$mysql_qry="SELECT * FROM service";

$result= mysqli_query($conn, $mysql_qry);//it executes the sql query and connects to the DB




if(mysqli_num_rows($result) >0){//numrows returns nbr of rows in result
	
	while($row = mysqli_fetch_assoc($result)){
		
		array_push($response,$row);
		
	}
	

	print(json_encode($response));
	
}



?>