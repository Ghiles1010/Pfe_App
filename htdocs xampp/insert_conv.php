<?php
require "connect.php";

$id_client=$_POST["id_client"];
$id_service=$_POST["id_service"];
$nom_client=$_POST["nom_client"];
$nom_service=$_POST["nom_service"];
$time=$_POST["time"];



$mysql_qry="INSERT INTO `conversation`(`service`, `client`, `nom_client`, `nom_service`, `time`) VALUES ('$id_service','$id_client','$nom_client','$nom_service','$time') ";

$result= mysqli_query($conn, $mysql_qry);//it executes the sql query and connects to the DB

//n'oublie pas plus tard de verrifier que l'utilisateur a bien été inscrit





?>