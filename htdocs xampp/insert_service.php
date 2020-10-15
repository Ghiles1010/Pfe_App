<?php
require "connect.php";

$nom=$_POST["nom"];
$id_prest=$_POST["id_prest"];
$long=$_POST["longitude"];






function generateKey(){
	
	$keyLength = 22;
	$str="1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_";
	$randStr = substr(str_shuffle($str),0,$keyLength);
	return $randStr;

}


function checkKeys($randStr,$conn){
	
	$sql= "SELECT * FROM service WHERE id_service like '$randStr'";
	
	$result= mysqli_query($conn, $sql);//it executes the sql query and connects to the DB
	
	if(mysqli_num_rows($result) >0){//numrows returns nbr of rows in result
		return false;
    }
	
	else{
		return true;
	}
	
	
}



do{

	$id=generateKey();
	$result=checkKeys($id,$conn);
	
}while($result=false);




/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



$mysql_qry="INSERT INTO `service`(`id_service`, `prestataire`,`nom`,`longitude`) VALUES ('$id','$id_prest','$nom','$long') ";

$result= mysqli_query($conn, $mysql_qry);//it executes the sql query and connects to the DB

//n'oublie pas plus tard de verrifier que l'utilisateur a bien été inscrit
echo"ID=$id";




?>