<?php
require "connect.php";

$id_client=$_POST["id_client"];
$id_service=$_POST["id_service"];



	
$sql= "SELECT * FROM service WHERE id_service like '$randStr'";
	
$result= mysqli_query($conn, $sql);//it executes the sql query and connects to the DB
	
if(mysqli_num_rows($result) >0){//numrows returns nbr of rows in result
		return false;
  }
	
else{
		return true;
}
	




$mysql_qry="INSERT INTO `service`(`id_service`, `prestataire`,`nom`,`longitude`) VALUES ('$id','$id_prest','$nom','$long') ";

$result= mysqli_query($conn, $mysql_qry);//it executes the sql query and connects to the DB

//n'oublie pas plus tard de verrifier que l'utilisateur a bien été inscrit
echo"ID=$id";




?>