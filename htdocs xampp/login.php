<?php
require "connect.php";
$user_mail=$_POST["email"];
$user_psw=$_POST["psw"];

$response=array();

$mysql_qry="select * from User where mail like '$user_mail' and psw like '$user_psw' ";
$result= mysqli_query($conn, $mysql_qry);//it executes the sql query and connects to the DB




if(mysqli_num_rows($result) >0){//numrows returns nbr of rows in result

	echo"success";	
}
else{
	echo"veuillez verifier votre adresse mail ou votre mot de passe";
}
?>