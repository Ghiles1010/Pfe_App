<?php
require "connect.php";
$user_name="user";
$user_psw="user";
$mysql_qry="select * from User where id_user like '$user_name' and psw like '$user_psw' ";
$result= mysqli_query($conn, $mysql_qry);//it executes the sql query and connects to the DB
if(mysqli_num_rows($result) >0){//numrows returns nbr of rows in result
	echo"login success";
}
else{
	echo"login failed";
}
?>