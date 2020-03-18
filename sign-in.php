<?php
require "connect.php";

$user_name=$_POST["name"];
$user_surname=$_POST["surname"];
$user_username=$_POST["username"];
$user_email=$_POST["email"];
$user_psw=$_POST["psw"];


function generateKey(){
	
	$keyLength = 22;
	$str="1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_";
	$randStr = substr(str_shuffle($str),0,$keyLength);
	return $randStr;

}


function checkKeys($randStr,$conn){
	
	$sql= "SELECT * FROM user WHERE id_user like '$randStr'";
	
	$result= mysqli_query($conn, $sql);//it executes the sql query and connects to the DB
	
	if(mysqli_num_rows($result) >0){//numrows returns nbr of rows in result
		return false;
    }
	
	else{
		return true;
	}
	
	
}


/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

$id=generateKey();
$result=checkKeys($id,$conn);

while($result=false){
	
	$id=generateKey();
	$result=checkKeys($id,$conn);
}



$sql= "SELECT * FROM user WHERE mail like '$user_email'";	
$result= mysqli_query($conn, $sql);

if(mysqli_num_rows($result) >0){//numrows returns nbr of rows in result
		echo "email already exists";
    }

else{

$mysql_qry="INSERT INTO `user`(`id_user`, `mail`, `psw`, `username`, `name`, `surname`) VALUES ('$id','$user_email','$user_psw','$user_username','$user_name','$user_surname') ";

$result= mysqli_query($conn, $mysql_qry);//it executes the sql query and connects to the DB

}


?>