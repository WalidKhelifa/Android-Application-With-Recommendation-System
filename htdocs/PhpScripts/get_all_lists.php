<?php

require "connection.php";
 
$response= array();

$ingredient=array();
$list_ingredient = array();

$maladie=array();
$list_maladie = array();

$allergie=array();
$list_allergie = array();

$reussi=0;

 $query="select id_ingredient,nom_ing from ingredient;";

 $result_query= mysqli_query($conn,$query);
 

	 if($result_query) 
	 {
	 	$reussi=1;
	 	
	 	if(mysqli_num_rows($result_query)>0)
		{	
			while ($row=mysqli_fetch_assoc($result_query))
			{
				$ingredient["id"]=$row["id_ingredient"];
				$ingredient["nom"]=$row["nom_ing"];
				
				array_push($list_ingredient,$ingredient);
			}
		}

	 } 
	 else  
	 {
	 			$response["type"]="get_all_lists";
	 			$response["success"]="GAL0";
				//j'envoie ça dans un code json
				echo json_encode($response,JSON_PRETTY_PRINT|JSON_UNESCAPED_UNICODE);			
	 }

 $query2="select id_maladie,libelle from maladies;";

 $result_query2= mysqli_query($conn,$query2);
 

	 if($result_query2) /* s'il existe means login success'*/
	 {
	 	$reussi=1;
	 	
	 	if(mysqli_num_rows($result_query2)>0)
		{	
			while ($row2=mysqli_fetch_assoc($result_query2))
			{
				$maladie["id"]=$row2["id_maladie"];
				$maladie["nom"]=$row2["libelle"];
				
				array_push($list_maladie,$maladie);
			}
		}

	 } 
	 else  
	 {
	 			$response["type"]="get_all_lists";
	 			$response["success"]="GAL0";
				//j'envoie ça dans un code json
				echo json_encode($response,JSON_PRETTY_PRINT|JSON_UNESCAPED_UNICODE);			
	 }

	 if($reussi==1)
	 {


	 	$response["type"]="get_all_lists";
	    $response["success"]="GAL1";
		array_push($response,$list_ingredient);
		array_push($response,$list_maladie);
		
		//j'envoie ça dans un code json
		echo json_encode($response,JSON_PRETTY_PRINT|JSON_UNESCAPED_UNICODE);

	 }


	mysqli_close($conn);

  ?>