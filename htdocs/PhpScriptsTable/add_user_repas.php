<?php

require "connection.php";

$id_client=$_POST["id_client"];
$nom_repas=$_POST["nom_repas"];
$liste_repas=$_POST["repas_client"];



$query="insert into repas_personnalises(nom_repas,id_client) values('$nom_repas','$id_client');";

$result_query= mysqli_query($conn,$query);
 

	if($result_query) 
	 {
	 		$response["type"]="add_user_repas";
			$response["success"]="AUR1";
						
			//j'envoie ça dans un code json
			echo json_encode($response,JSON_PRETTY_PRINT|JSON_UNESCAPED_UNICODE);


			//HAVE TO GET THE ID_REPAS

			$query2="select id_repas from repas_personnalises where  nom_repas='$nom_repas' and id_client ='$id_client';";
   			$result_query2= mysqli_query($conn,$query2);


   			if(mysqli_num_rows($result_query2)>0)


			{

				$row=mysqli_fetch_array($result_query2,MYSQLI_NUM);

				$id_repas=$row[0];


				foreach (json_decode($liste_repas,true) as $decode){
	   			
		   			$id_plat=$decode["id"];
		   			$selected_qte=$decode["qte"];
		   			$selected_portion=$decode["portion"];

		   			$query3="insert into repas_personnalise_plats values('$id_repas','$selected_qte','$selected_portion','$id_plat');";
		   			$result_query3= mysqli_query($conn,$query3);


				}

		   				
			}

	 } 
	else  
	 {
	 			$response["type"]="add_user_repas";
	 			$response["success"]="AUR0";
				//j'envoie ça dans un code json
				echo json_encode($response,JSON_PRETTY_PRINT|JSON_UNESCAPED_UNICODE);
	 			
	 }

mysqli_close($conn);

  ?>