<?php
require "connect.php";

$id_user=$_POST["id_user"];



function generateKey(){
	
	$keyLength = 22;
	$str="1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_";
	$randStr = substr(str_shuffle($str),0,$keyLength);
	return $randStr;

}


function checkKeys($randStr,$conn){
	
	$sql= "SELECT * FROM prestataire WHERE id_prestataire like '$randStr'";
	
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



$mysql_qry="INSERT INTO `prestataire`(`id_prestataire`, `user`) VALUES ('$id','$id_user') ";

$result= mysqli_query($conn, $mysql_qry);//it executes the sql query and connects to the DB

//n'oublie pas plus tard de verrifier que l'utilisateur a bien été inscrit
echo"ID=$id $id_user";




?>