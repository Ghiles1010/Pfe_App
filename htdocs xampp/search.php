<?php
require "connect.php";
$search=$_POST["token"];
$mysql_qry="select nom,description,picture from service where nom like '$search' or categorie like '$search' ";

if(!$conn){
	die("error in connection:" . mysqli_connect_error());
}
$response = array();

$result= mysqli_query($conn, $mysql_qry);//it executes the sql query and connects to the DB


if(mysqli_num_rows($result) >0){//numrows returns nbr of rows in result
	$service = array();
	while ($row= mysqli_fetch_assoc($result)){
		
		array_push($service,$row);
	}
	$response = $service;
}
else{
	$response['message']= 'no data';
}

echo json_encode($response);
mysqli_close($conn);
?>