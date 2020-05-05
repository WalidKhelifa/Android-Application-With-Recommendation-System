<?php

require "connection.php";
 
$id_plat=$_POST["id_plat"];
$response= array();
$ingredient=array();
$list_ingredient = array();
$allergene= array();
$list_allergene = array();
$calorie=array();
$list_calorie = array();

 $query="select id_ingredient from plat_ingredients where  id_plat='$id_plat';";

 $result_query= mysqli_query($conn,$query);
 

	 if(mysqli_num_rows($result_query)>0) 
	 {
	 	$response["type"]="fetch_ingredient_response";
	    $response["success"]="PD1";

		while ($row=mysqli_fetch_assoc($result_query))
		{
			$ingredient["id_ingredient"]=$row["id_ingredient"];

			$id_ingredient=$ingredient["id_ingredient"];
			
	        
			$get_ingredient="select nom_ing, id_allergie from ingredient where id_ingredient='$id_ingredient';";

			$result_get_ingredient_query=mysqli_query($conn,$get_ingredient);

			if(mysqli_num_rows($result_get_ingredient_query)>0)
			{
				$row1=mysqli_fetch_assoc($result_get_ingredient_query);
				
				$ingredient["nom_ing"]=$row1["nom_ing"];

				//ON MET L INGREDIENT DANS SA LISTE

				array_push($list_ingredient,$ingredient);

				$id_allergie=$row1["id_allergie"];


				if(!is_null($id_allergie))
				{

					$get_allergie="select nom_allergie from allergies where id_allergie='$id_allergie';";
					$result_get_allergie_query=mysqli_query($conn,$get_allergie);

					if(mysqli_num_rows($result_get_allergie_query)>0)
					{
						
						$row2=mysqli_fetch_assoc($result_get_allergie_query);
				
						$allergene["nom_allergie"]=$row2["nom_allergie"];

						array_push($list_allergene,$allergene);
					}
				}

			}
		}

		$get_calories_query="select portionNom,portionCalories from portion where  id_plat='$id_plat';";

 		$result_calories_query= mysqli_query($conn,$get_calories_query);
 		
 		if(mysqli_num_rows($result_calories_query)>0)
 		{
				while ($row3=mysqli_fetch_assoc($result_calories_query)) {
				
					$calorie["portion"]=$row3["portionNom"];
					$calorie["calories"]=$row3["portionCalories"];

					array_push($list_calorie,$calorie);
				}	
				
		}

		array_push($response,$list_ingredient);
		array_push($response,$list_allergene);
		array_push($response,$list_calorie);
		//j'envoie ça dans un code json
		echo json_encode($response,JSON_PRETTY_PRINT|JSON_UNESCAPED_UNICODE);
	 } 
	 else  
	 {
	 			$response["type"]="fetch_ingredient_response";
	 			$response["success"]="PD0";
				//j'envoie ça dans un code json
				echo json_encode($response,JSON_PRETTY_PRINT|JSON_UNESCAPED_UNICODE);
	 			

	 }



mysqli_close($conn);

  ?>