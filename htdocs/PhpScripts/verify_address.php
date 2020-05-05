<?php

require "connection.php";

$id_client=$_POST["id_client"];

$query="select adrC  from client where id_client='$id_client';";

$result_query= mysqli_query($conn,$query);
 

	if($result_query) 
	 {
	 	$row=mysqli_fetch_assoc($result_query);

	 	if($row["adrC"]!=NULL)
	 		{
	 			$response["type"]="verify_address";
				$response["success"]="VA1";
				
				//j'envoie ça dans un code json
				echo json_encode($response,JSON_PRETTY_PRINT|JSON_UNESCAPED_UNICODE);

				 } 
				else  
				 {
				 			$response["type"]="verify_address";
				 			$response["success"]="VA0";
							//j'envoie ça dans un code json
							echo json_encode($response,JSON_PRETTY_PRINT|JSON_UNESCAPED_UNICODE);
				 }
	}

mysqli_close($conn);

  ?>