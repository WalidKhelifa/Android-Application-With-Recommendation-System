<?php

require "connection.php";

$id_client=$_POST["id_client"];
$address=$_POST["address"];

$query="update  client set adrC='$address' where id_client='$id_client';";

$result_query= mysqli_query($conn,$query);
 

	if($result_query) 
	 {
	 		$response["type"]="add_address";
			$response["success"]="AA1";
						
			//j'envoie ça dans un code json
			echo json_encode($response,JSON_PRETTY_PRINT|JSON_UNESCAPED_UNICODE);

	 } 
	else  
	 {
	 			$response["type"]="add_address";
	 			$response["success"]="AA0";
				//j'envoie ça dans un code json
				echo json_encode($response,JSON_PRETTY_PRINT|JSON_UNESCAPED_UNICODE);
	 }

mysqli_close($conn);

  ?>