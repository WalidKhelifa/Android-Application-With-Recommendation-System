<?php

require "connection.php";

 $id_client=$_POST["id_client"];
 $id_plat=$_POST["id_plat"];


 $response= array();


 $query="select id_plat from client_plats_favoris where id_plat = '$id_plat' and  id_client = '$id_client' ;";

 $result_query= mysqli_query($conn,$query);
 

	 if(mysqli_num_rows($result_query)>0) /* s'il existe means qu'il est dans les favoris donc on le supprime*/
	 {

		$delete_query="delete from client_plats_favoris where id_plat= '$id_plat' and  id_client = '$id_client' ;" ;
		$result_delete_query=mysqli_query($conn,$delete_query);

		if($result_delete_query)//si ça s est bien supprimé
			{
					$success="favoris_ok";
					$message= "deleted";
			}
		else
			{		$success="favoris_erreur";
					$message= "errorDeleting";
			}

		$response["success"]=$success;
		$response["message"]=$message;

		//j'envoie ça dans un code json
		echo json_encode(($response),JSON_PRETTY_PRINT|JSON_UNESCAPED_UNICODE);

	 } 
	 else  // S'il existe pas je l ajoute aux favoris
	 {
	 			
	 	$insert_query="insert into client_plats_favoris values('$id_client','$id_plat');" ;
		$result_insert_query=mysqli_query($conn,$insert_query);


		if($result_insert_query)//si ça s est bien ajouté
			{
					$success="favoris_ok";
					$message="inserted";

			}
		else
			{		$success="favoris_erreur";
					$message= "errorInserting";
			}
		$response["success"]=$success;		
		$response["message"]=$message;

		//j'envoie ça dans un code json
		echo json_encode(($response),JSON_PRETTY_PRINT|JSON_UNESCAPED_UNICODE);

	 }



mysqli_close($conn);

  ?>