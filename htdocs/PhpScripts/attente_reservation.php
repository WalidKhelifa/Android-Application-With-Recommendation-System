<?php

require "connection.php";

$id_client=$_POST["id_client"];
$id_table=$_POST["id_table"];
$date_res=$_POST["date_reserv"]; 
$heure_res=$_POST["heure_reserv"];
$date=$_POST["date"];
$heure=$_POST["heure"];
$etat=$_POST["etat"];
$nb=$_POST["nb"];


$query2="insert into reservation(date_reserv,heure_reserv,date_rdv,heure_rdv,etat_reserv,nb_personnes,id_table,id_client) values('$date_res','$heure_res','$date','$heure','$etat','$nb','$id_table','$id_client');";

		$result_query2= mysqli_query($conn,$query2);
		
		if($result_query2){
	 		
	 			$response["type"]="attenteReservation";
				$response["success"]="ATR1";
				$response["dispo"]="true";
				//j'envoie ça dans un code json
			echo json_encode($response,JSON_PRETTY_PRINT|JSON_UNESCAPED_UNICODE);
			}
			else{


				$response["type"]="attenteReservation";
	 			$response["success"]="TR0";
	 			$response["dispo"]="false";
				//j'envoie ça dans un code json
				echo json_encode($response,JSON_PRETTY_PRINT|JSON_UNESCAPED_UNICODE);
	 			
			}




mysqli_close($conn);

  ?>