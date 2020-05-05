<?php

require "connection.php"; // inclure la connexion ..

$value=$_POST["user"];
 
$mdp=$_POST["password"];

  $response= array(); // declaration d'un tableau ...


 $query="select * from client where userName = '$value'  and mdpClient = '$mdp' ;";

 $result_query= mysqli_query($conn,$query); //executer la requete ...
 

	 if(mysqli_num_rows($result_query)>0) /* s'il existe means login success'*/ //si il existe une ligne ..
	 {
		$success= "Login_ok";// ok ....
		$message= "Connexion réussie !";

		$row=mysqli_fetch_array($result_query,MYSQLI_NUM); //retourne une ligne de résultat de la requete sous la forme d'un tableau associatif ..

		$id=$row[0]; //recuperer le id du client
		$tof=$row[3]; //recuperer la photo du client 
		$username=$row[1]; //recuperer le user name du client 


		array_push($response,$arrayName = array("success" =>$success,"message"=>$message,"id"=>$id,"username"=>$username,"photo"=>$tof));//inserer les element dans le tableau

		//j'envoie ça dans un code json
		echo json_encode(array("login_response" =>$response),JSON_PRETTY_PRINT|JSON_UNESCAPED_UNICODE);

	 } 
	 else  // le client n'existe pas  !
	 {

	 			 $query1="select * from client where userName = '$value';";

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
							$message =  "TOUT incorrect";
							array_push($response,$arrayName = array("success" =>$success,"message"=>$message));
							//j'envoie ça dans un code json
							echo json_encode(array("login_response" =>$response),JSON_PRETTY_PRINT|JSON_UNESCAPED_UNICODE);



								}

						}
			
	 }


mysqli_close($conn);

  ?>