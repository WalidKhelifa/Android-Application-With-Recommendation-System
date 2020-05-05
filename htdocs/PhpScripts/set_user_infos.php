<?php

require "connection.php";
 
$id_client=$_POST["id_client"];
$username=$_POST["username"]; 
$email=$_POST["email"];
$password=$_POST["password"];
$photo=$_POST["photo"];
$genre=$_POST["genre"];
$date=$_POST["date"];
$taille=$_POST["taille"];
$poids=$_POST["poids"];
$adr=$_POST["adr"];
$phone=$_POST["phone"];
$pref=$_POST["pref"];
$deteste=$_POST["deteste"];
$allergie=$_POST["allergie"];
$maladie=$_POST["maladie"];



if(isset($_POST['genre']) && !empty($_POST['genre']) )
	{	
		$genre_val="'".$genre."'";
	}
else
	{
		$genre_val='NULL';
	}


	if(isset($_POST['date']) && !empty($_POST['date']) )
	{	
		$date_val="'".$date."'";
	}
else
	{
		$date_val='NULL';
	}


	if(isset($_POST['taille']) && !empty($_POST['taille']) )
	{	
		$taille_val="'".$taille."'";
	}
else
	{
		$taille_val='NULL';
	}
	

	if(isset($_POST['poids']) && !empty($_POST['poids']) )
	{	
		$poids_val="'".$poids."'";
	}
else
	{
		$poids_val='NULL';
	}
	

	if(isset($_POST['adr']) && !empty($_POST['adr']) )
	{	
		$adr_val="'".$adr."'";
	}
else
	{
		$adr_val='NULL';
	}


	if(isset($_POST['phone']) && !empty($_POST['phone']) )
	{	
		$phone_val="'".$phone."'";
	}
else
	{
		$phone_val='NULL';
	}


if($_POST['photo']!="NULL")
	{	
		$path = "Users_uploads/".date("M_d_Y_h_i_s") . "_$id_client.jpg";
 
		$actualpath = "http://192.168.43.225/PhpScripts/$path";
	
		$photo_val="'".$actualpath."'";

		$query="update client set userName='$username',mdpClient='$password',photo=$photo_val,dateNaissCl=$date_val,genre=$genre_val,taille=$taille_val,poids=$poids_val,email='$email',numeroTel=$phone_val,adrC=$adr_val where id_client='$id_client';";
		
	}
else
	{
		$actualpath = "null";
		$photo_val='NULL';
		$query="update client set userName='$username',mdpClient='$password',dateNaissCl=$date_val,genre=$genre_val,taille=$taille_val,poids=$poids_val,email='$email',numeroTel=$phone_val,adrC=$adr_val where id_client='$id_client';";
		
	}

			
		$query_verif="select id_client,userName,email from client where ( userName='$username' or email='$email') and id_client != '$id_client';";

		$result_query_verif= mysqli_query($conn,$query_verif);

		if (mysqli_num_rows($result_query_verif)<=0) {
		
			$result_query= mysqli_query($conn,$query);
			 
				if($result_query) 
				 {

					if($_POST['photo']!="NULL")
				 	{file_put_contents($path,base64_decode($photo));}

				 	$response["type"]="set_user_infos";
				    $response["success"]="SUI1";
					$response["photo"]=$actualpath;
					//j'envoie ça dans un code json
					echo json_encode($response,JSON_PRETTY_PRINT|JSON_UNESCAPED_UNICODE);


					//je supprimes les anciens pref et je mets les nouveaux


					$delete_pref_query="delete from client_ingredients_aimes where id_client='$id_client';";
					$result_delete_pref_query= mysqli_query($conn,$delete_pref_query);

					if($result_delete_pref_query)
					{
						foreach (json_decode($pref,true) as $decode){
			   			
			   			$id_ingredient=$decode["id"];
			   			$query2="insert into client_ingredients_aimes values('$id_client','$id_ingredient');";
			   			$result_query2= mysqli_query($conn,$query2);

						}
					}
					

					//je supprimes les anciens detestes et je mets les nouveaux

					$delete_det_query="delete from client_ingredients_detestes where id_client='$id_client';";
					$result_delete_det_query= mysqli_query($conn,$delete_det_query);

					if($result_delete_det_query)
					{
						foreach (json_decode($deteste,true) as $decode1){
			   			
			   			$id_ingredient1=$decode1["id"];
			   			$query3="insert into client_ingredients_detestes values('$id_client','$id_ingredient1');";
			   			$result_query3= mysqli_query($conn,$query3);

						}
					}


					//je supprimes les anciens allergies et je mets les nouveaux

					$delete_allerg_query="delete from allergies_client where id_client='$id_client';";
					$result_delete_allerg_query= mysqli_query($conn,$delete_allerg_query);

					if($result_delete_allerg_query)
					{
						foreach (json_decode($allergie,true) as $decode2){
			   			
			   			$id_allergie=$decode2["id"];
			   			$query4="insert into allergies_client values('$id_client','$id_allergie');";
			   			$result_query4= mysqli_query($conn,$query4);

						}
					}
					

					//je supprimes les ancienes maladies et je mets les nouveaux

					$delete_maladie_query="delete from maladies_client where id_client='$id_client';";
					$result_delete_maladie_query= mysqli_query($conn,$delete_maladie_query);

					if($result_delete_maladie_query)
					{
						foreach (json_decode($maladie,true) as $decode3){
			   			
			   			$id_maladie=$decode3["id"];
			   			$query5="insert into maladies_client values('$id_client','$id_maladie');";
			   			$result_query5= mysqli_query($conn,$query5);

						}
					}


				 } 
				else  
				 {
				 			$response["type"]="set_user_infos";
				 			$response["success"]="SUI0";
							//j'envoie ça dans un code json
							echo json_encode($response,JSON_PRETTY_PRINT|JSON_UNESCAPED_UNICODE);
				 			
				 }

			}else{

					$row=mysqli_fetch_assoc($result_query_verif);

				 	if($row["userName"]==$username)
				 	{				 	
						
						if($row["email"]==$email){

							$response["type"]="set_user_infos";
				 			$response["success"]="SUI00";
							
							echo json_encode($response,JSON_PRETTY_PRINT|JSON_UNESCAPED_UNICODE);
							
						}else{

							$response["type"]="set_user_infos";
				 			$response["success"]="SUI000";
							echo json_encode($response,JSON_PRETTY_PRINT|JSON_UNESCAPED_UNICODE);
						}
			 
				 	}else{

					 	    $response["type"]="set_user_infos";
				 			$response["success"]="SUI0000";
						    echo json_encode($response,JSON_PRETTY_PRINT|JSON_UNESCAPED_UNICODE);

				 	}
			}					


mysqli_close($conn);

  ?>