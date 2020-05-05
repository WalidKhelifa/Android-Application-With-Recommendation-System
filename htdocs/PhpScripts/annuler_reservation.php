<?php

require "connection.php";

$id=$_POST["id_reservation"];

	//j'envoie ça dans un code json
		

$query="update reservation set etat_reserv='annule' where id_reservation='$id';";

$result_query= mysqli_query($conn,$query);
 
if($result_query)
{
	
		$response["type"]="annuler_reservation";
		$response["success"]="AR1";
	

				//j'envoie ça dans un code json
			echo json_encode($response,JSON_PRETTY_PRINT|JSON_UNESCAPED_UNICODE);

}

else{


		$response["type"]="annuler_reservation";
		$response["success"]="AR0";
			
		//j'envoie ça dans un code json
		echo json_encode($response,JSON_PRETTY_PRINT|JSON_UNESCAPED_UNICODE);
			
	}

mysqli_close($conn);


?>