<?php
require_once("connect.php");
session_start();

 // $Category = $_POST['category_selbox'];
 // $MenuName = $_POST['MenuName'];
 // $Size = $_POST['size_selbox'];
 $HotIce = $_POST['hot_selbox'];
 // $Price = $_POST['Price'];

$hoticeDic = array('hot' => 0, 
'ice'=> 1,
'hot and ice'=>3,
'not choice'=>2);

echo('<pre>'); print_r($_POST); echo('</pre>');

if ($HotIce == 'hot'){
  $HotIce = 0;
}elseif ($HotIce == 'ice') {
  $HotIce = 1;
}
echo('$HotIce');

 // $sql="INSERT INTO menu (storeSerial,category,menuName,size,hotIce,price) VALUES('".$_SESSION['storeSerial']."','".$_POST['CategoryDirect']."','".$_POST['MenuName']."','".$_POST['SizeDirect']."','".$_POST['hotDirect']."','".$_POST['Price']."')";
 $sql="INSERT INTO menu (storeSerial,category,menuName,size,hotIce,price) VALUES('".$_SESSION['storeSerial']."','".$_POST['category_selbox']."','".$_POST['MenuName']."','".$_POST['size_selbox']."','"$hoticeDic[$_POST['hot_selbox']]"','".$_POST['Price']."')";

   $result=mysqli_query($conn, $sql);

  //  echo ("<script>alert('메뉴가 추가되었습니다.');history.go(-1);</script>");
  // exit;
  //
  //
  // header("Location:Main2.php");


 ?>
