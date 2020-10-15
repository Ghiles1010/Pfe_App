<?php
require "connect.php";

$description=$_POST["description"];
$id_service=$_POST["id_service"];
$id_client=$_POST["id_client"];
$time=$_POST["time"];



$mysql_qry="INSERT INTO `rdv`(`service`, `client`, `text`, `time`) VALUES ('$id_service','$id_client','$description','$time')";
$result= mysqli_query($conn, $mysql_qry);//it executes the sql query and connects to the DB
echo"rdv_asked";
?>