<?php
require "connect.php";

$message=$_POST["message"];
$id_service=$_POST["id_service"];
$id_client=$_POST["id_client"];
$id_sender=$_POST["id_sender"];
$time=$_POST["time"];



$mysql_qry="INSERT INTO `message`(`client`,`service`,`id_sender`,`text`,`hour`) VALUES ('$id_client','$id_service','$id_sender','$message','$time')";
$result= mysqli_query($conn, $mysql_qry);//it executes the sql query and connects to the DB
echo"message_sent";
?>