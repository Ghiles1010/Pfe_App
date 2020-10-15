<?php
require "connect.php";

$conv=$_POST["conv"];
$user=$_POST["user"];

$response=array();
$mysql_qry="select MAX(hour) from message where conversation like '$conv' and user <> '$user'";
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