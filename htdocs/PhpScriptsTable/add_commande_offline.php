<?php

require "connection.php";

$date_com=$_POST["date"]; 
$heure_com=$_POST["heure"];
$montant=$_POST["montant"];
$liste_plats=$_POST["plats_commandes"];

$query="INSERT INTO `commande`(`date_commande`, `heure_commande`, `montant`, `type_commande`,`id_client`) VALUES('$date_com','$heure_com','$montant','',null);";

$result_query= mysqli_query($conn,$query);
 

	if($result_query) 
	 {
	 		$response["type"]="add_commande_off";
			$response["success"]="add_commande_off_ok";
						
			//j'envoie ça dans un code json
			echo json_encode($response,JSON_PRETTY_PRINT|JSON_UNESCAPED_UNICODE);


			//HAVE TO GET THE ID_COMMANDE

			$query2="select id_commande from commande where  date_commande='$date_com' and heure_commande='$heure_com' and montant='$montant' and type_commande='';";
   			$result_query2= mysqli_query($conn,$query2);


   			if(mysqli_num_rows($result_query2)>0)


			{

				$row=mysqli_fetch_array($result_query2,MYSQLI_NUM);

				$id_commande=$row[0];


				foreach (json_decode($liste_plats,true) as $decode){
	   			
		   			$id_plat=$decode["id"];
		   			$selected_qte=$decode["qte"];
		   			$selected_portion=$decode["portion"];

		   			$query3="insert into plats_commandes(qte,portion,id_plat,id_commande) values('$selected_qte','$selected_portion','$id_plat','$id_commande');";
		   			$result_query3= mysqli_query($conn,$query3);


				}

		   				
			}
		
		


	 } 
	else  
	 {
	 			$response["type"]="add_commande_off";
	 			$response["success"]="add_commande_off_erreur";
				//j'envoie ça dans un code json
				echo json_encode($response,JSON_PRETTY_PRINT|JSON_UNESCAPED_UNICODE);
	 			

	 }



mysqli_close($conn);

  ?>