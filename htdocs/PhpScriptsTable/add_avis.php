<?php

require "connection.php";

$id_client=$_POST["id_client"];
$date_avis=$_POST["date"]; 
$heure_avis=$_POST["heure"];
$comment=$_POST["comment"];
$nb_etoile=$_POST["nb_etoile"];

if($id_client!="null")
	{	
		$id="'".$id_client."'";
	}
else
	{
		$id='NULL';
	}

	if($comment!="null")
	{	
		$com="'".$comment."'";
	}
else
	{
		$com='NULL';
	}



$query="INSERT INTO `avis`(`date_avis`, `nb_etoile`, `heure_avis`,`id_client`,`commentaire`) VALUES ('$date_avis','$nb_etoile','$heure_avis',$id,$com);";

$result_query= mysqli_query($conn,$query);
 

	if($result_query) 
	 {
	 		$response["type"]="add_avis";
			$response["success"]="add_avis_ok";
						
			//j'envoie ça dans un code json
			echo json_encode($response,JSON_PRETTY_PRINT|JSON_UNESCAPED_UNICODE);

	 } 
	else  
	 {
	 			$response["type"]="add_avis";
	 			$response["success"]="avis_erreur";
				//j'envoie ça dans un code json
				echo json_encode($response,JSON_PRETTY_PRINT|JSON_UNESCAPED_UNICODE);
	 			
	 }



mysqli_close($conn);

  ?>