<?php

require "connection.php";

 $value=$_POST["authentification"];
 
 $mdp=$_POST["motDePasse"];

 $response= array();


 $query="select * from client where (userName = '$value' or email ='$value')  and mdpClient = '$mdp' ;";

 $result_query= mysqli_query($conn,$query);
 

	 if(mysqli_num_rows($result_query)>0) /* s'il existe means login success'*/
	 {
		$success= "L1";// 1 means no errors
		$message= "Succes de la connexion !";

		$row=mysqli_fetch_array($result_query,MYSQLI_NUM);

		$id=$row[0];
		$tof=$row[3];
		$username=$row[1];


		array_push($response,$arrayName = array("success" =>$success,"message"=>$message,"id"=>$id,"username"=>$username,"photo"=>$tof));

		//j'envoie ça dans un code json
		echo json_encode(array("login_response" =>$response),JSON_PRETTY_PRINT|JSON_UNESCAPED_UNICODE);

	 } 
	 else  // le client n'existe pas  !
	 { 


	 	$query1="select * from client where userName = '$value' or  email ='$value' ;";

				 $result_query1= mysqli_query($conn,$query1); //executer la requete ...

				 if(mysqli_num_rows($result_query1)>0){

					$success = "L0";// errors ...
					$message =  "Mot de passe incorrect";
					array_push($response,$arrayName = array("success" =>$success,"message"=>$message));
					//j'envoie ça dans un code json
					echo json_encode(array("login_response" =>$response),JSON_PRETTY_PRINT|JSON_UNESCAPED_UNICODE);

				 }else{



					$query2="select * from client where mdpClient = '$mdp' ;";

					$result_query2= mysqli_query($conn,$query2); //executer la requete ...
						
						if(mysqli_num_rows($result_query2)>0){

							$success = "L00";// errors ...
							$message =  "Nom d'utilisateur incorrect";
							array_push($response,$arrayName = array("success" =>$success,"message"=>$message));
							//j'envoie ça dans un code json
							echo json_encode(array("login_response" =>$response),JSON_PRETTY_PRINT|JSON_UNESCAPED_UNICODE);

						 }else{

					 		$success = "L000";// errors ...
							$message ="TOUT incorrect";
							array_push($response,$arrayName = array("success" =>$success,"message"=>$message));
							//j'envoie ça dans un code json
							echo json_encode(array("login_response" =>$response),JSON_PRETTY_PRINT|JSON_UNESCAPED_UNICODE);



								}

						}
			
	 }


mysqli_close($conn);

  ?>