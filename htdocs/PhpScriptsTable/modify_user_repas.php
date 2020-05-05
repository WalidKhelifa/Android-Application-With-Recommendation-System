
<?php

require "connection.php";

$id_client=$_POST["id_client"];

$nom_repas=$_POST["nom_repas"];

$new_nom_repas=$_POST["new_nom_repas"];


$query="update repas_personnalises set nom_repas='$new_nom_repas' where nom_repas='$nom_repas' and id_client='$id_client';";

$result_query= mysqli_query($conn,$query);
 
	if($result_query) 
	 {
	 		$response["type"]="modify_user_repas";
			$response["success"]="MUR1";
						
			//j'envoie ça dans un code json
			echo json_encode($response,JSON_PRETTY_PRINT|JSON_UNESCAPED_UNICODE);

	 } 
	else  
	 {
	 			$response["type"]="modify_user_repas";
	 			$response["success"]="MUR0";
				//j'envoie ça dans un code json
				echo json_encode($response,JSON_PRETTY_PRINT|JSON_UNESCAPED_UNICODE);
	 			
	 }

mysqli_close($conn);

  ?>
