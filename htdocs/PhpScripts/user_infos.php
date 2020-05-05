<?php

require "connection.php";
 
$id_client=$_POST["id_client"];

$response= array();

$client= array();
$liste_infos= array();

$aliment_pref=array();
$list_aliment_pref = array();

$aliment_deteste=array();
$list_aliment_deteste= array();

$allergie= array();
$list_allergie= array();

$maladie= array();
$list_maladie= array();

$query="select userName,mdpClient,photo,dateNaissCl,genre,taille,poids,email,numeroTel,adrC from client where  id_client='$id_client';";

$result_query= mysqli_query($conn,$query);
 

	if(mysqli_num_rows($result_query)>0) 
	 {
	 	$response["type"]="user_infos";
	    $response["success"]="UI1";

		$row=mysqli_fetch_assoc($result_query);
		
		$client["username"]=$row["userName"];
		$client["mdp"]=$row["mdpClient"];
		$client["photo"]=$row["photo"];
		$client["date"]=$row["dateNaissCl"];
		$client["genre"]=$row["genre"];
		$client["taille"]=$row["taille"];
		$client["poids"]=$row["poids"];
		$client["email"]=$row["email"];
		$client["adr"]=$row["adrC"];
		$client["tel"]=$row["numeroTel"];
	      
		array_push($liste_infos,$client);
		array_push($response,$liste_infos);

//GET L ELEMENT PREF 

		$get_aliments_pref_query="select id_ingredient from client_ingredients_aimes where id_client='$id_client';";
		$result_get_aliments_pref_query= mysqli_query($conn,$get_aliments_pref_query);

		if(mysqli_num_rows($result_get_aliments_pref_query)>0)
		{
				while ($row=mysqli_fetch_assoc($result_get_aliments_pref_query))
			{
				$aliment_pref["id"]=$row["id_ingredient"];
				$id_ingredient=$row["id_ingredient"];

				$get_aliment_pref_name_query="select nom_ing from ingredient where id_ingredient='$id_ingredient';";
				$result_get_aliment_pref_name_query= mysqli_query($conn,$get_aliment_pref_name_query);

				$row1=mysqli_fetch_assoc($result_get_aliment_pref_name_query);

				$aliment_pref["nom"]=$row1["nom_ing"];

				array_push($list_aliment_pref,$aliment_pref);
			}
		}

		array_push($response,$list_aliment_pref);

//ELEMENT DETESTES
		$get_aliments_det_query="select id_ingredient from client_ingredients_detestes where id_client='$id_client';";
		$result_get_aliments_det_query= mysqli_query($conn,$get_aliments_det_query);

		if(mysqli_num_rows($result_get_aliments_det_query)>0)
		{
				while ($row2=mysqli_fetch_assoc($result_get_aliments_det_query))
			{
				$aliment_deteste["id"]=$row2["id_ingredient"];
				$id_ingredient1=$row2["id_ingredient"];

				$get_aliment_det_name_query="select nom_ing from ingredient where id_ingredient='$id_ingredient1';";
				$result_get_aliment_det_name_query= mysqli_query($conn,$get_aliment_det_name_query);

				$row3=mysqli_fetch_assoc($result_get_aliment_det_name_query);

				$aliment_deteste["nom"]=$row3["nom_ing"];

				array_push($list_aliment_deteste,$aliment_deteste);
			}

		}
		array_push($response,$list_aliment_deteste);


			//ELEMENT Allergie
		$get_allergie_query="select id_ingredient from allergies_client where id_client='$id_client';";
		$result_get_allergie_query= mysqli_query($conn,$get_allergie_query);

		if(mysqli_num_rows($result_get_allergie_query)>0)
		{
				while ($row4=mysqli_fetch_assoc($result_get_allergie_query))
			{
				$allergie["id"]=$row4["id_ingredient"];
				$id_allergie=$row4["id_ingredient"];

				$get_allergie_name_query="select nom_ing from ingredient where id_ingredient='$id_allergie';";
				$result_get_allergie_name_query= mysqli_query($conn,$get_allergie_name_query);

				$row5=mysqli_fetch_assoc($result_get_allergie_name_query);

				$allergie["nom"]=$row5["nom_ing"];

				array_push($list_allergie,$allergie);
			}

		}

			array_push($response,$list_allergie);


	//ELEMENT Maladie
		$get_maladie_query="select id_maladie from maladies_client where id_client='$id_client';";
		$result_get_maladie_query= mysqli_query($conn,$get_maladie_query);

		if(mysqli_num_rows($result_get_maladie_query)>0)
		{
				while ($row6=mysqli_fetch_assoc($result_get_maladie_query))
			{
				$maladie["id"]=$row6["id_maladie"];
				$id_maladie=$row6["id_maladie"];

				$get_maladie_name_query="select libelle from maladies where id_maladie='$id_maladie';";
				$result_get_maladie_name_query= mysqli_query($conn,$get_maladie_name_query);

				$row7=mysqli_fetch_assoc($result_get_maladie_name_query);

				$maladie["nom"]=$row7["libelle"];

				array_push($list_maladie,$maladie);
			}

		}

		array_push($response,$list_maladie);

		//j'envoie ça dans un code json
		echo json_encode($response,JSON_PRETTY_PRINT|JSON_UNESCAPED_UNICODE);
	 } 
	else  
	 {
	 			$response["type"]="user_infos";
	 			$response["success"]="UI0";
				//j'envoie ça dans un code json
				echo json_encode($response,JSON_PRETTY_PRINT|JSON_UNESCAPED_UNICODE);
	 			

	 }



mysqli_close($conn);

  ?>