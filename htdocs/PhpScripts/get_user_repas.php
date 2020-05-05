<?php

require "connection.php";
 
$id_client=$_POST["id_client"];

$response= array();

$repas= array();
$plat=array();
$liste_plats_repas= array();
$liste_repas= array();



$query="select id_repas,nom_repas from repas_personnalises where  id_client='$id_client';";

$result_query= mysqli_query($conn,$query);
 
	

	if(mysqli_num_rows($result_query)>0) 
	 {
	 	
		$response["type"]="get_user_repas";
	    $response["success"]="GUR1";

		while ($row=mysqli_fetch_assoc($result_query)) {
		
		$liste_plats_repas= array();

		$repas["id"]=$row["id_repas"];
		$id_repas=$row["id_repas"];
		$repas["nom"]=$row["nom_repas"];
		
		$query2="select id_plat,qte,portion from repas_personnalise_plats where id_repas='$id_repas';";

		$result_query2= mysqli_query($conn,$query2);
 
		if(mysqli_num_rows($result_query2)>0) 
	 	{
	 
			while ($row2=mysqli_fetch_assoc($result_query2)){

				$id_plat=$row2["id_plat"];
				$plat["id"]=$row2["id_plat"];

				$query3="select designation from plat where id_plat='$id_plat';";

				$result_query3= mysqli_query($conn,$query3);
 
				if(mysqli_num_rows($result_query3)>0){

					$row3=mysqli_fetch_assoc($result_query3);

					$plat["nom"]=$row3["designation"];
				}

				$plat["qte_select"]=$row2["qte"];
				$plat["portion_select"]=$row2["portion"];
				$portion=$row2["portion"];

				$query4="select portionPrixUni from portion where id_plat='$id_plat' and portionNom='$portion' ;";

				$result_query4= mysqli_query($conn,$query4);
 
				if(mysqli_num_rows($result_query4)>0){

					$row4=mysqli_fetch_assoc($result_query4);

					$plat["prix_portion_select"]=$row4["portionPrixUni"];
				}

				array_push($liste_plats_repas,$plat);
				
            }
	
		}
		
		$repas["liste_plats"]=$liste_plats_repas;

		array_push($liste_repas,$repas);


	}

		array_push($response,$liste_repas);

		//j'envoie ça dans un code json
		echo json_encode($response,JSON_PRETTY_PRINT|JSON_UNESCAPED_UNICODE);
	 	}
	else  
	 {
	 			$response["type"]="get_user_repas";
	 			$response["success"]="GUR0";
				//j'envoie ça dans un code json
				echo json_encode($response,JSON_PRETTY_PRINT|JSON_UNESCAPED_UNICODE);
	 			

	 }



mysqli_close($conn);

  ?>