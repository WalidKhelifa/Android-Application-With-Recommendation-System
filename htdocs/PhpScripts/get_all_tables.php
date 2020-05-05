<?php

require "connection.php";
 
$response= array();

$table=array();
$list_table = array();

 $query="select id_table,nb_personnes,caracteristiques from tableconnectee;";

 $result_query= mysqli_query($conn,$query);
 

	 if($result_query) 
	 {
	 
	 	
	 	if(mysqli_num_rows($result_query)>0)
		{	
			$response["type"]="get_all_tables";
	 		$response["success"]="GAT1";

			while ($row=mysqli_fetch_assoc($result_query))
			{
				$table["id"]=$row["id_table"];
				$table["nb"]=$row["nb_personnes"];
				$table["car"]=$row["caracteristiques"];
				
				array_push($list_table,$table);
			}

			array_push($response,$list_table);

			//j'envoie ça dans un code json
			echo json_encode($response,JSON_PRETTY_PRINT|JSON_UNESCAPED_UNICODE);
		}

	 } 
	 else  
	 {
	 			$response["type"]="get_all_tables";
	 			$response["success"]="GAT0";
				//j'envoie ça dans un code json
				echo json_encode($response,JSON_PRETTY_PRINT|JSON_UNESCAPED_UNICODE);			
	 }


	mysqli_close($conn);

  ?>