<?php
require_once("connect.php");
session_start();

 $Category = $_POST['CategoryDirect'];
 $MenuName = $_POST['MenuName'];
 $Size = $_POST['SizeDirect'];
 $HotIce = $_POST['hotDirect'];
 $Price = $_POST['Price'];

echo('<pre>'); print_r($_POST); echo('</pre>');


 // $sql="INSERT INTO menu (storeSerial,category,menuName,size,hotIce,price) VALUES('".$_SESSION['storeSerial']."','".$_POST['CategoryDirect']."','".$_POST['MenuName']."','".$_POST['SizeDirect']."','".$_POST['hotDirect']."','".$_POST['Price']."')";
 $sql="INSERT INTO menu (storeSerial,category,menuName,size,price) VALUES('".$_SESSION['storeSerial']."','".$_POST['category_selbox']."','".$_POST['MenuName']."','".$_POST['size_selbox']."','".$_POST['Price']."')";

   $result=mysqli_query($conn, $sql);

  //  echo ("<script>alert('메뉴가 추가되었습니다.');history.go(-1);</script>");
  // exit;


  // header("Location:Main2.php");


 ?>
