<?php

require "connection.php";

$id_client=$_POST["id_client"];

$query="delete from client where id_client='$id_client';";

$result_query= mysqli_query($conn,$query);
 

	if($result_query) 
	 {
	 		$response["type"]="delete_account";
			$response["success"]="DA1";
						
			//j'envoie ça dans un code json
			echo json_encode($response,JSON_PRETTY_PRINT|JSON_UNESCAPED_UNICODE);

	 } 
	else  
	 {
	 			$response["type"]="delete_account";
	 			$response["success"]="DA0";
				//j'envoie ça dans un code json
				echo json_encode($response,JSON_PRETTY_PRINT|JSON_UNESCAPED_UNICODE);
	 }

mysqli_close($conn);

  ?>