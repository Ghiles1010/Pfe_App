<?php
require "connect.php";

$response=array();
$id_service=$_POST["id_service"];
$time=$_POST["time"];
$id_client=$_POST["id_client"];
$id_sender=$_POST["id_sender"];

if(strcmp($id_sender,"")==0){ //Quand le user est vide on selectionne tout les user
	$mysql_qry="select * from message where client like '$id_client' and service like '$id_service' and hour> '$time'";
}

else{
	$mysql_qry="select * from message where client like '$id_client' and service like '$id_service' and id_sender <> '$id_sender' and hour> '$time'";
}
$time_qry="select MAX(hour) from message";

$result= mysqli_query($conn, $mysql_qry);

$result2= mysqli_query($conn, $time_qry);


if(mysqli_num_rows($result) >0){//numrows returns nbr of rows in result
	
	while($row = mysqli_fetch_assoc($result)){
		
		array_push($response,$row);
		
	}
	
	while($roww = mysqli_fetch_assoc($result2)){
		echo $roww['MAX(hour)'];
	}
	

	print(json_encode($response));
	
}
else{
	echo "no messages";
}












?>