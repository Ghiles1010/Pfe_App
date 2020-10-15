<?php
require "connect.php";

$id_service=$_POST["id_service"];
$cat_ids=$_POST["cat_ids"];



$ids=array();
$ids=explode(" ", $cat_ids);



/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
$length=count($ids);

for($i=0;$i<$length;$i=$i+1){

	$mysql_qry="INSERT INTO `category_service`(`id_category`, `id_service`) VALUES ('$ids[$i]','$id_service') ";
	$result= mysqli_query($conn, $mysql_qry); //it executes the sql query and connects to the DB

}






?>