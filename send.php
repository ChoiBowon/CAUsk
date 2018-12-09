<?php
	define('SERVER_API_KEY', 'AAAAxHLPIiE:APA91bGok2gHvYh385DQraUPouJrXQAcqbQt9lDLXqASyB7-FEq5ef7xF23ID2HeLEVJLBxlwtinfHV2G2s6YMorMDgLDb61dNtM1p9Bd6jsOqpIIc_vvEznyMb1PWY_uzABAm8kSSKD');
	$tokens=['c01pasUUVBw:APA91bGOPWLTaAm2lL4E0KWcQgxPI0Xti6aO0xJmQrMAlTBEvbdR1R88IKPjiXLJsLMKfKE7d0paE9MfQxM5Xz1vQAMZ7yYn0GallTfkxLu9BIabDfxKWHJBdx1RVGreeeCKP272Mm4t'];
	
	$header = [
		'Authorization: Key='. SERVER_API_KEY,
		'Content-Type: Application/json'
	];

	$msg = [
		'title'=> $_POST['one'],
		'body'=> $_POST['two'],
		//'icon'=> 'img/icon.png',
		
		//'image'=>'img/d.png',

	];

	$payload = [
	 	'registration_ids'=> $tokens,
		'data'=>$msg	
	];


$curl = curl_init();

curl_setopt_array($curl, array(
	CURLOPT_URL => "https://fcm.googleapis.com/fcm/send",
	CURLOPT_RETURNTRANSFER => true,
	CURLOPT_CUSTOMREQUEST => "POST",
	CURLOPT_POSTFIELDS => json_encode( $payload ),
	CURLOPT_HTTPHEADER => $header
));

$response = curl_exec($curl);
$err = curl_error($curl);

curl_close($curl);

if($err) {
	echo "cURL Error #:" . $err;
}else{
	echo $response;
}

?>
