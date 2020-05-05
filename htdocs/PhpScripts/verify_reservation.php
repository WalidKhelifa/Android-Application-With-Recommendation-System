<?php

require "connection.php";

$id_client=$_POST["id_client"];
$reservation=array();


$query_table="select id_table from tableconnectee where dispo=1;";


$result_query_table= mysqli_query($conn,$query_table);



if(mysqli_num_rows($result_query_table)<=0)
{
 
 	$response["type"]="verify_reservation";
	$response["success"]="VR1";

	//reservations desactivées 
 
	$response["page"]="3";

	//j'envoie ça dans un code json
  echo json_encode($response,JSON_PRETTY_PRINT|JSON_UNESCAPED_UNICODE);
}

else{


$query="select id_reservation,date_rdv,heure_rdv,id_table,nb_personnes from reservation where str_to_date(date_rdv,'%d/%m/%Y') >= CURDATE() and etat_reserv='reserve' and id_client = '$id_client' ;";

$result_query= mysqli_query($conn,$query);
 
if($result_query)
{
 	
 	if(mysqli_num_rows($result_query)>0){

	 		
		$response["type"]="verify_reservation";
		$response["success"]="VR11";
		// DEJA RESERVE
		$response["page"]="1";



		  $row=mysqli_fetch_assoc($result_query);


		  $reservation["id"]=$row["id_reservation"];
		  $reservation["date"]=$row["date_rdv"];
		  $reservation["heure"]=$row["heure_rdv"];
		  $reservation["id_table"]=$row["id_table"];
		  $reservation["nb_personnes"]=$row["nb_personnes"];
		  
		  array_push($response,$reservation);

				//j'envoie ça dans un code json
			echo json_encode($response,JSON_PRETTY_PRINT|JSON_UNESCAPED_UNICODE);
			}
			else{


				$response["type"]="verify_reservation";
	 			$response["success"]="VR1";
	 			//RIEN RESERVE
	 			$response["page"]="2";
				//j'envoie ça dans un code json
				echo json_encode($response,JSON_PRETTY_PRINT|JSON_UNESCAPED_UNICODE);
	 			
			}

	}else{

				$response["type"]="verify_reservation";
	 			$response["success"]="VR0";
	 			$response["page"]="0";

			echo json_encode($response,JSON_PRETTY_PRINT|JSON_UNESCAPED_UNICODE);

	}


}

mysqli_close($conn);

  ?>