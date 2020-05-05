<?php

require "connection.php";
 
$id_client=$_POST["id_client"];
$response= array();
$plat=array();
$list_plat = array();


 $query="select id_plat,designation,photo_plat,categorie,modeCuisson,tempsCuisson  from plat;";

 $result_query= mysqli_query($conn,$query);
 

	 if(mysqli_num_rows($result_query)>0) 
	 {
	 	$response["type"]="user_menu_filter_recommander";
	    $response["success"]="UMFR1";
		while ($row=mysqli_fetch_assoc($result_query))
		{
			$id_plat=$row["id_plat"];
			$no_maladie=true;
			$no_allergie= true;

			$query1="select id_ingredient from plat_ingredients where id_plat='$id_plat';";

 			$result_query1= mysqli_query($conn,$query1);

 			while ($row1=mysqli_fetch_assoc($result_query1))
			{

				$id_ingredient = $row1["id_ingredient"];

				$query3="select * from allergies_client where id_ingredient='$id_ingredient' and id_client='$id_client';";

 				$result_query3= mysqli_query($conn,$query3);

					if(mysqli_num_rows($result_query3)>0){

						$no_allergie=false;
					}


 				$query4="select t1.id_maladie from maladies_client t1, maladie_ingredients t2 where id_client='$id_client'and t1.id_maladie=t2.id_maladie and id_ingredient='$id_ingredient'  ;";
 				$result_query4= mysqli_query($conn,$query4);

 				if(mysqli_num_rows($result_query4)>0){

						 $no_maladie=false;
					}

					
			}
					if ($no_maladie && $no_allergie) {


						$plat["id"]=$row["id_plat"];
		
						$plat["designation"]=$row["designation"];
						$plat["photo"]=$row["photo_plat"];
						$plat["categorie"]=$row["categorie"];
						$plat["modeCuisson"]=$row["modeCuisson"];
						$plat["tempsCuisson"]=$row["tempsCuisson"];


								//PRICE

						$get_price_small="select portionPrixUni from portion where id_plat='$id_plat' and portionNom='small' ;";

						$result_price_small_query=mysqli_query($conn,$get_price_small);

						if(mysqli_num_rows($result_price_small_query)>0)
						{
							$row2=mysqli_fetch_assoc($result_price_small_query);
							$plat["smallPrice"]=$row2["portionPrixUni"];
						}
						else
						{
							$plat["smallPrice"]="";
						}


						$get_price_medium="select portionPrixUni from portion where id_plat='$id_plat' and portionNom='medium' ;";

						$result_price_medium_query=mysqli_query($conn,$get_price_medium);

						if(mysqli_num_rows($result_price_medium_query)>0)
						{
							$row4=mysqli_fetch_assoc($result_price_medium_query);
							$plat["mediumPrice"]=$row4["portionPrixUni"];
						}
						else
						{
							$plat["mediumPrice"]="";
						}



						$get_price_large="select portionPrixUni from portion where id_plat='$id_plat' and portionNom='large' ;";

						$result_price_large_query=mysqli_query($conn,$get_price_large);

						if(mysqli_num_rows($result_price_large_query)>0)
						{
							$row3=mysqli_fetch_assoc($result_price_large_query);
							$plat["largePrice"]=$row3["portionPrixUni"];
						}
						else
						{
							$plat["largePrice"]="";
						}


				        //favoris

						$get_if_fav="select * from client_plats_favoris where id_plat='$id_plat' and id_client='$id_client' ;";

						$result_get_if_fav=mysqli_query($conn,$get_if_fav);

						if (mysqli_num_rows($result_get_if_fav)>0)
						{
							$plat["fav"]="true";
						}
						else
						{
							$plat["fav"]="false";
						}



					$query5="select t1.id_ingredient, nom_ing from plat_ingredients t1, ingredient t2 where id_plat='$id_plat'and t1.id_ingredient=t2.id_ingredient  ;";

						$result_query5= mysqli_query($conn,$query5);
		 
						if(mysqli_num_rows($result_query5)>0){

							$list_ingredient= array();

							while ($row5=mysqli_fetch_assoc($result_query5)){

								$ingredient["id_ing"]=$row5["id_ingredient"];

								$ingredient["nom_ing"]=$row5["nom_ing"];

								array_push($list_ingredient,$ingredient);
								
							}

							$plat["liste_ingredients"]=$list_ingredient;



								array_push($list_plat,$plat);


						}


					}
 		
			
		}

				array_push($response,$list_plat);
				//j'envoie ça dans un code json
				echo json_encode($response,JSON_PRETTY_PRINT|JSON_UNESCAPED_UNICODE);

	 } 
	 else  
	 {
	 			$response["type"]="user_menu_filter_recommander";
	 			$response["success"]="UMFR11";
				//j'envoie ça dans un code json
				echo json_encode($response,JSON_PRETTY_PRINT|JSON_UNESCAPED_UNICODE);
	 			

	 }



mysqli_close($conn);

  ?>