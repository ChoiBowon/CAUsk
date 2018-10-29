<?php
require_once("connect.php");
session_start();

 $Category = $_POST['Category'];
 $MenuName = $_POST['MenuName'];
 $Size = $_POST['Size'];
 $HotIce = $_POST['HOTICE'];
 $Price = $_POST['Price'];

 echo ("들어옴");


 // $sql = "SELECT storeSerial FROM store WHERE id = '$_SESSION['userId']'";
 // $res = mysqli_query($conn,'SELECT storeSerial FROM store WHERE id = '$_SESSION['userId']'');

 // $res = $conn->query($sql);
 // if($res->num_rows >= 1){
 //   echo ("<script>alert('이미 존재하는 아이디가 있습니다.');history.go(-1);</script>");
 //     exit;
 // }



 $sql="INSERT INTO menu (storeSerial,category,menuName,size,hotIce,price) VALUES('".$_SESSION['storeSerial']."','".$_POST['Category']."','".$_POST['MenuName']."','".$_POST['Size']."','".$_POST['HOTICE']."','".$_POST['Price']."')";

   $result=mysqli_query($conn, $sql);

   echo ("<script>alert('메뉴가 추가되었습니다.');history.go(-1);</script>");
  exit;


   header("Location:Main2.php");


 ?>
