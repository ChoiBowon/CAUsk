<?php
// session_start();
require_once("connect.php");

 // $result = mysqli_query($conn, "SELECT * FROM matching");

 //$_SESSION['userId']=$idmentor;
 // $idmentor=$_SESSION['name'];//로그인된 멘토가 멘토링 신청을 accept
 $orderSerial = $_POST['orderSerial'];

 // $bool=TRUE;
 $sql="UPDATE causk.order SET complete=1, completedTime = date_add(now(), INTERVAL 9 HOUR) WHERE orderSerial='".$orderSerial."'";

$result=mysqli_query($conn, $sql);

 echo ("<script>alert('주문 완료 처리되었습니다!');history.go(-1);</script>");

 header("Location:Main2.php");

?>
