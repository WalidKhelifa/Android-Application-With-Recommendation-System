<?php

require "connection.php";
 
$id_client=$_POST["id_client"];

$response= array();

$commande= array();
$plat=array();
$liste_plats_commandes= array();
$liste_commandes= array();


$query="select id_commande,date_commande,heure_commande,montant,type_commande from commande where  id_client='$id_client';";

$result_query= mysqli_query($conn,$query);
 
	

if(mysqli_num_rows($result_query)>0) 
 {
	 	
		$response["type"]="user_commandes_recommandation";
	    $response["success"]="UCR1";

		while ($row=mysqli_fetch_assoc($result_query)) {
		
		$liste_plats_commandes= array();

		$commande["id"]=$row["id_commande"];
		$id_commande=$row["id_commande"];
		$commande["date"]=$row["date_commande"];
		$commande["heure"]=$row["heure_commande"];
		$commande["montant"]=$row["montant"];
		$commande["type"]=$row["type_commande"];
		
		$query2="select id_plat,qte,portion from plats_commandes where id_commande='$id_commande';";

		$result_query2= mysqli_query($conn,$query2);
 
		if(mysqli_num_rows($result_query2)>0) 
	 	{
	 
			while ($row2=mysqli_fetch_assoc($result_query2)){

				$id_plat=$row2["id_plat"];
				$plat["id"]=$row2["id_plat"];

				$query3="select designation from plat where id_plat='$id_plat';";

				$result_query3= mysqli_query($conn,$query3);
 
				if(mysqli_num_rows($result_query3)>0){

					$row3=mysqli_fetch_assoc($result_query3);

					$plat["nom"]=$row3["designation"];
				}

				$plat["qte_select"]=$row2["qte"];
				$plat["portion_select"]=$row2["portion"];
				$portion=$row2["portion"];

				$query4="select portionPrixUni from portion where id_plat='$id_plat' and portionNom='$portion' ;";

				$result_query4= mysqli_query($conn,$query4);
 
				if(mysqli_num_rows($result_query4)>0){

					$row4=mysqli_fetch_assoc($result_query4);

					$plat["prix_portion_select"]=$row4["portionPrixUni"];
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

				}

				array_push($liste_plats_commandes,$plat);
				
            }
	
		}
		
		$commande["liste_plats"]=$liste_plats_commandes;

		array_push($liste_commandes,$commande);


	}

		array_push($response,$liste_commandes);

		//j'envoie ça dans un code json
		echo json_encode($response,JSON_PRETTY_PRINT|JSON_UNESCAPED_UNICODE);
}
else  
 {
 			$response["type"]="user_commandes_recommandation";
 			$response["success"]="UCR11";
			//j'envoie ça dans un code json
			echo json_encode($response,JSON_PRETTY_PRINT|JSON_UNESCAPED_UNICODE);
 			

 }



mysqli_close($conn);

  ?>