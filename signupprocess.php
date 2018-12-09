<?php
require_once("connect.php");

//////////////////////////////////////// 회원가입 시작 ////////////////////////////////////////////
 $memberId = $_POST['Id'];
 $memberPw = $_POST['Password'];
 $memberPw2 = $_POST['Password2'];
 $memberStoreName = $_POST['Store_Name'];
 $memberPlace = $_POST['roadAddress'];
 $memberPhoneNum = $_POST['PhoneNum'];
 $memberPointX = $_POST['pointX'];
 $memberPointY = $_POST['pointY'];

//$sGoogleMapApi = "http://maps.googleapis.com/maps/api/geocode/xml?address=".urlencode($memberPlace)."&sensor=true_or_false" ;
//$budget74 = simplexml_load_file($sGoogleMapApi);

//if($budget74->status!='OK') die("구글맵API에서 주소에 대한 좌표를 받아 오는데 실패하였습니다!");

//$lat = $budget74->result->geometry->location->lat;

 //$lng = $budget74->result->geometry->location->lng;

 //echo("<script src=\"https://ssl.daumcdn.net/dmaps/map_js_init/postcode.v2.js\"></script>");
 /*echo("<script type=\"text/javascript\" src=\"//dapi.kakao.com/v2/maps/sdk.js?appkey=b36d32178aeff64f0c2873b5419d4863&libraries=services\"></script>");
 echo ("<script>;
 var geocoder = new daum.maps.services.Geocoder();

 var callback = function(result, status) {
    if (status === daum.maps.services.Status.OK) {
        console.log(result);
    }
};
geocoder.addressSearch('".$memberPlace."', callback);
</script>");*/
//"lat:{$lat}, lng:{$lng}");

 //PHP에서 유효성 재확인

 //아이디 중복검사.
 $sql = "SELECT * FROM store WHERE id = '{$memberId}'";
 $res = $conn->query($sql);
 if($res->num_rows >= 1){
   echo ("<script>alert('이미 존재하는 아이디가 있습니다.');history.go(-1);</script>");
     exit;
 }

 //비밀번호 일치하는지 확인
 if($memberPw !== $memberPw2){
   echo ("<script>alert('비밀번호가 일치하지 않습니다.');history.go(-1);</script>");
     exit;
 }

 //가게 이름이 빈 값이 아닌지
 if($memberStoreName == ''){
     echo ("<script>alert('가게 이름을 입력해주세요.');history.go(-1);</script>");
exit;
 }
 //지점 이름이 빈값이 아닌지
 if($memberPlace == ''){
     echo ("<script>alert('지점 위치를 입력해주세요.');history.go(-1);</script>");
exit;
 }
 //전화번호가 빈값이 아닌지
 if($memberPhoneNum == ''){
     echo ("<script>alert('전화번호를 입력해주세요.');history.go(-1);</script>");
exit;
 }


 // $sql="INSERT INTO store (id,password,storeName,place,tel) VALUES('".$_POST['Id']."','".$_POST['Password']."','".$_POST['Store_Name']."','".$_POST['Place']."','".$_POST['PhoneNum']."')";
 $sql="INSERT INTO store (id,password,storeName,place,tel,longitude,latitude) VALUES('".$_POST['Id']."',HEX(AES_ENCRYPT('".$_POST['Password']."', MD5('".$_POST['Password']."'))),'".$_POST['Store_Name']."','".$_POST['roadAddress']."','".$_POST['PhoneNum']."',".$memberPointX.",".$memberPointY.")";
   $result=mysqli_query($conn, $sql);

   echo ("<script>alert('회원가입이 완료었습니다');location.href=\"Login.php\"</script>");
  exit;

//////////////////////////////////////////회원가입 끝////////////////////////////////////////////////

//   header("Location:Login.php");


 ?>
