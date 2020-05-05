<?php

require "connection.php";
 
$response= array();
$plat=array();
$list_plat = array();

 $query="select id_plat,designation,photo_plat,categorie,modeCuisson,tempsCuisson  from plat;";

 $result_query= mysqli_query($conn,$query);  //executer la requete ...
 

	 if(mysqli_num_rows($result_query)>0) 
	 {
	 	$response["type"]="fetch_menu";
	    $response["success"]="menu_offline_ok";
		while ($row=mysqli_fetch_assoc($result_query))
		{
		$plat["id"]=$row["id_plat"];
		$id_plat=$row["id_plat"];
		$plat["designation"]=$row["designation"];
		$plat["photo"]=$row["photo_plat"];
		$plat["categorie"]=$row["categorie"];
		$plat["modeCuisson"]=$row["modeCuisson"];
		$plat["tempsCuisson"]=$row["tempsCuisson"];


		//////////recuperer les prix ....

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
			$row1=mysqli_fetch_assoc($result_price_medium_query);
			$plat["mediumPrice"]=$row1["portionPrixUni"];
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


		array_push($list_plat,$plat);
	}

		array_push($response,$list_plat);
		//j'envoie ça dans un code json
		echo json_encode($response,JSON_PRETTY_PRINT|JSON_UNESCAPED_UNICODE);

	 } 
	 else  
	 {
	 			$response["type"]="fetch_menu";
	 			$response["success"]="menu_offline_erreur";
				//j'envoie ça dans un code json
				echo json_encode($response,JSON_PRETTY_PRINT|JSON_UNESCAPED_UNICODE);
	 			

	 }



mysqli_close($conn);

  ?>