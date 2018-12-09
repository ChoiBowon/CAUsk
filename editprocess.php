
<?php
// session_start();
require_once("connect.php");

//update menu set category = 'hello', menuName = 'world', size = 'haha', hotIce = 0, price = '2500' where menuSerial = 378;

$_category = $_POST['Category'];
$_menuName = $_POST['MenuName'];
$_size = $_POST['Size'];
$_hotIce = $_POST['HOTICE'];

if($_hotIce == "ice") {
	$_hotIce = 1;
} else {
	$_hotIce = 0;
}

$_price = $_POST['Price'];
$_menuSerial = $_POST['menuSerial'];

//array(6) { ["Category"]=> string(6) "커피" ["MenuName"]=> string(32) "화이트 초콜릿 마끼아또" ["Size"]=> string(6) "라지" ["HOTICE"]=> string(3) "hot" ["Price"]=> string(4) "5500" ["menuSerial"]=> string(3) "296" }
 // $result = mysqli_query($conn, "SELECT * FROM matching");

 //$_SESSION['userId']=$idmentor;
 // $idmentor=$_SESSION['name'];//로그인된 멘토가 멘토링 신청을 accept
// $orderSerial = $_POST['orderSerial'];

 // $bool=TRUE;
 $sql="UPDATE menu SET category = '".$_category."', menuName = '".$_menuName."', size = '".$_size."', hotIce = ".$_hotIce.", price = '".$_price."' WHERE menuSerial=".$_menuSerial.";";

 $result=mysqli_query($conn, $sql);
 echo $sql;
 echo $result;
// echo ("<script>alert('주문 완료 처리되었습니다!');history.go(-1);</script>");
//var_dump($_POST);
header("Location:Main2.php");

?>
