<?php

require "connection.php";

 $id_client=$_POST["id_client"];
 
$response= array();
$list_id_plat=array( );

 $query="select id_plat from client_plats_favoris where id_client = '$id_client' ;";

 $result_query= mysqli_query($conn,$query);
 

	 if(mysqli_num_rows($result_query)>0) /* retreive data*/
	 {

		$success="favoris_ok";
		$message= "done";
			
		$response["success"]=$success;
		$response["message"]=$message;

		while ($row=mysqli_fetch_assoc($result_query))
		{
		
		$plat["id_plat"]=$row["id_plat"];
		


		array_push($list_id_plat,$plat);
	}

		array_push($response,$list_id_plat);	
		//j'envoie ça dans un code json
		echo json_encode(($response),JSON_PRETTY_PRINT|JSON_UNESCAPED_UNICODE);

	 } 
	 else  //nothing to do
	 {
	 			
	 	
		$success="favoris_erreur";
		$message= "errorFetching";
		
		$response["success"]=$success;		
		$response["message"]=$message;

		//j'envoie ça dans un code json
		echo json_encode(($response),JSON_PRETTY_PRINT|JSON_UNESCAPED_UNICODE);

	 }



mysqli_close($conn);

  ?>