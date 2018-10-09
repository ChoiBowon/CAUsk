<?php
require_once("connect.php");

//////////////////////////////////////// 회원가입 시작 ////////////////////////////////////////////
 $memberId = $_POST['Id'];
 $memberPw = $_POST['Password'];
 $memberPw2 = $_POST['Password2'];
 $memberStoreName = $_POST['Store_Name'];
 $memberPlace = $_POST['Place'];
 $memberPhoneNum = $_POST['PhoneNum'];

 //PHP에서 유효성 재확인

 //아이디 중복검사.
 $sql = "SELECT * FROM member WHERE id = '{$memberId}'";
 $res = $conn->query($sql);
 if($res->num_rows >= 1){
   echo ("<script>alert('이미 존재하는 아이디가 있습니다.');history.go(-1);</script>");
     exit;
 }

 //비밀번호 일치하는지 확인
 if($memberPw !== $memberPw2){
   echo ("<script>alert('일치하는 비밀번호가 없습니다.');history.go(-1);</script>");
     exit;
 }

 //이름이 빈 값이 아닌지
 if($memberStoreName == ''){
     echo ("<script>alert('이름의 값이 없습니다.');history.go(-1);</script>");
exit;
 }
 //역할(멘토/멘티)이 빈값이 아닌지
 if($memberPlace == ''){
     echo ("<script>alert('멘토/멘티의 값이 없습니다.');history.go(-1);</script>");
exit;
 }
 //전화번호가 빈값이 아닌지
 if($memberPhoneNum == ''){
     echo ("<script>alert('전화번호의 값이 없습니다.');history.go(-1);</script>");
exit;
 }


 $sql="INSERT INTO user (id,password,store_name,place,phonenumber) VALUES('".$_POST['Id']."','".$_POST['Password']."','".$_POST['Store_Name']."','".$_POST['Place']."','".$_POST['PhoneNum']."')";

   $result=mysqli_query($conn, $sql);

   echo ("<script>alert('회원가입이 완료되었습니다.');history.go(-2);</script>");
  exit;

//////////////////////////////////////////회원가입 끝////////////////////////////////////////////////

   header("Location:Main.php");


 ?>
