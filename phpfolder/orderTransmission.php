<?php  
error_reporting(E_ALL); 
ini_set('display_errors',1); 

$link=mysqli_connect('ec2-13-125-242-247.ap-northeast-2.compute.amazonaws.com','bowon','bowon5263','causk');   
if (!$link)  
{  
    echo "MySQL 접속 에러 : ";
    echo mysqli_connect_error();
    exit();  
}  
mysqli_set_charset($link,"utf8"); 

//POST 값을 읽어온다.
$userId=isset($_POST['userId']) ? $_POST['userId'] : '';
$storeName=isset($_POST['storeName']) ? $_POST['storeName'] : '';
$menuName=isset($_POST['menuName']) ? $_POST['menuName'] : '';
$size=isset($_POST['size']) ? $_POST['size'] : '';
$hotIce=isset($_POST['hotIce']) ? $_POST['hotIce'] : '';
$price=isset($_POST['price']) ? $_POST['price'] : '';
$complete=0;
$android = strpos($_SERVER['HTTP_USER_AGENT'], "Android");

if($hotIce=='hot'){
    $hotIce2=0;
} else if($hotIce=='ice'){
    $hotIce2=1;
}

if ($storeName !="" ){ 
    
    $sql="INSERT INTO `causk`.`order` (`userId`, `menuName`, `storeName`, `time`, `complete`, `hotIce`, `size`, `price`) VALUES ('$userId', '$menuName', '$storeName', DATE_ADD( now() , interval + 9 hour), '0', '$hotIce2','$size', '$price');
";
    
    $result=mysqli_query($link,$sql);
    

}
else {
    echo "오류 입니다.";
}

mysqli_close($link);  

?>

<?php

$android = strpos($_SERVER['HTTP_USER_AGENT'], "Android");

if (!$android){
?>

<html>
   <body>
   
      <form action="<?php $_PHP_SELF ?>" method="POST">
         유저ID: <input type = "text" name = "userId" />
         음식점: <input type = "text" name = "storeName" />
         메뉴: <input type = "text" name = "menuName" />
         사이즈: <input type = "text" name = "size" />
         핫아이스: <input type = "text" name = "hotIce" />
         가격: <input type = "text" name = "price" />
         <input type = "submit" />
      </form>
   
   </body>
</html>
<?php
}

?>
