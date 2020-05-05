<?php

require "connection.php";

$id_client=$_POST["id_client"];

$nom_repas=$_POST["nom_repas"];


$query="delete from repas_personnalises where nom_repas='$nom_repas'and id_client='$id_client';";

$result_query= mysqli_query($conn,$query);
 

	if($result_query) 
	 {
	 		$response["type"]="delete_user_repas";
			$response["success"]="DUR1";
						
			//j'envoie ça dans un code json
			echo json_encode($response,JSON_PRETTY_PRINT|JSON_UNESCAPED_UNICODE);

	 } 
	else  
	 {
	 			$response["type"]="delete_user_repas";
	 			$response["success"]="DUR0";
				//j'envoie ça dans un code json
				echo json_encode($response,JSON_PRETTY_PRINT|JSON_UNESCAPED_UNICODE);
	 			
	 }

mysqli_close($conn);

  ?>