<?php

require "connection.php";
 
$response= array();

$resto=array();

$resto_list=array();

$rest_name="MyResto";

 $query="select  * from restaurant where nom_rest='$rest_name';"; //recuperer les infos du resto

 $result_query= mysqli_query($conn,$query); //executer la requete ...


	 if($result_query) 
	 {
	 
	 	
	 	if(mysqli_num_rows($result_query)>0)
		{	
			$response["type"]="info_resto";
	 		$response["success"]="info_ok";

			$row=mysqli_fetch_assoc($result_query);
			
				$resto["nom"]=$row["nom_rest"];
				$resto["photo"]=$row["photo_rest"];
				$resto["desc"]=$row["description"];
				$resto["tel"]=$row["num_tel_rest"];
				$resto["email"]=$row["email_rest"];
				$resto["ouvre"]=$row["horaire_ouv"];
				$resto["ferme"]=$row["horaire_ferm"];
				
			array_push($resto_list,$resto);
			array_push($response,$resto_list);

			
			echo json_encode($response,JSON_PRETTY_PRINT|JSON_UNESCAPED_UNICODE);
		}

	 } 
	 else  
	 {
	 			$response["type"]="info_resto";
	 			$response["success"]="info_erreur";
				
				echo json_encode($response,JSON_PRETTY_PRINT|JSON_UNESCAPED_UNICODE);			
	 }


	mysqli_close($conn);

  ?>