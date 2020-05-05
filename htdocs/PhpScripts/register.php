<?php
require "connection.php";

 $username=$_POST["name"];
 $email=$_POST["email"];
 $mdp=$_POST["password"];
 $tel=$_POST["phone"];
 $adr=$_POST["address"];
 $response= array();


 $query_exist="select * from client where userName= '$username' or email= '$email' ;";

 $result_query_exist= mysqli_query($conn,$query_exist);
 


	 if(mysqli_num_rows( $result_query_exist)>0) /* si j'essaye d enregister un client deja existant*/
	 {

	 	$row=mysqli_fetch_array($result_query_exist,MYSQLI_NUM);

	 	if($row[1]==$username)
	 	{

	 	
			
			if($row[8]==$email){


				$success= "R000";// 0 means errors
				$message= "2 deja existant";
				array_push($response,$arrayName = array("success" =>$success,"message"=>$message));
				
			}else{

				$success= "R0";// 0 means errors
				$message= "username deja existant";
				array_push($response,$arrayName = array("success" =>$success,"message"=>$message));
			}
 
	 	}else{

		 	$success= "R00";// 0 means errors
			$message= "email deja existant";
			array_push($response,$arrayName = array("success" =>$success,"message"=>$message));

	 	}
		

		//j'envoie ça dans un code json
		echo json_encode(array("register_response" =>$response),JSON_PRETTY_PRINT|JSON_UNESCAPED_UNICODE);

	 } 
	 else  // le client n'existe pas  !
	 {

	 		if(isset($_POST['phone']) && !empty($_POST['phone']) )
		 		{	
		 			$phone_val="'".$tel."'";
		 		}
		 	else
	 			{
	 				$phone_val='NULL';
	 			}
	 
		 	if(isset($_POST['address']) && !empty($_POST['address']) )
		 		{	
		 			$adr_val="'".$adr."'";
		 		}
		 	else
		 		{
		 				$adr_val='NULL';
		 		}


		 	$query_insert=" insert into client(userName,mdpClient,email,numeroTel,adrC) values('$username','$mdp', '$email',$phone_val,$adr_val);";
	 		
	 		$response_query_insert=mysqli_query($conn,$query_insert);

	 		if ($response_query_insert) {


	 			$success = "R1";// 0 means errors
				$message =  "Succes de l'inscription !";

				//GET THE USER ID :


				$get_id_query= "select id_client from client where userName= '$username' and email= '$email' ;";


				$result_get_id_query= mysqli_query($conn,$get_id_query);


				$row=mysqli_fetch_array($result_get_id_query,MYSQLI_NUM);

				$id=$row[0];

				array_push($response,$arrayName = array("success" =>$success,"message"=>$message,"id_client"=>$id,"user"=>$username));
				//j'envoie ça dans un code json
				echo json_encode(array("register_response" =>$response),JSON_PRETTY_PRINT|JSON_UNESCAPED_UNICODE);
	 			
	 		}
	 		else{

	 			$success = "R0";// 0 means errors
				$message =  "y'a erreur ailleurs...";
				array_push($response,$arrayName = array("success" =>$success,"message"=>$message));
				//j'envoie ça dans un code json
				echo json_encode(array("register_response"=>$response),JSON_PRETTY_PRINT|JSON_UNESCAPED_UNICODE);

	 		}
	 }



mysqli_close($conn);

  ?>