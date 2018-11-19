<?php
header("Content-Type:text/html;charset=utf-8");
session_start();
require_once("connect.php");

 // $result = mysqli_query($conn, "SELECT * FROM matching");

 //$_SESSION['userId']=$idmentor;
 // $idmentor=$_SESSION['name'];//로그인된 멘토가 멘토링 신청을 accept
 $orderSerial = $_POST['orderSerial'];

 // $bool=TRUE;
 $sql= mysqli_query($conn, "UPDATE causk.order SET complete=1 WHERE orderSerial='".$orderSerial."');

 echo ("<script>alert('주문 완료 처리되었습니다!');history.go(-1);</script>");

 header("Location:Mentoring.php");

?>
