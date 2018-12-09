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
$storeName=isset($_POST['storeName']) ? $_POST['storeName'] : '';
$android = strpos($_SERVER['HTTP_USER_AGENT'], "Android");

if ($storeName !="" ){ 
    $sql="SELECT tel FROM causk.store where storeName='$storeName';";
    
    $result=mysqli_query($link,$sql);
    $data = array();   
    if($result){  
    
        $row_count = mysqli_num_rows($result);

        if ( 0 == $row_count ){
            
            array_push($data,
                array('tel'=>$tel)
            );

            if (!$android) {

                echo "'";
                echo $storeName;
                echo "'은 찾을 수 없습니다.";

                echo "<pre>"; 
                print_r($data); 
                echo '</pre>';
                
            }else
            {
                header('Content-Type: application/json; charset=utf8');
                $json = json_encode(array("webnautes"=>$data), JSON_PRETTY_PRINT+JSON_UNESCAPED_UNICODE);
                echo $json;
            }

        }
        else{

            while($row=mysqli_fetch_array($result)){
                array_push($data, 
                    array(
                    'tel'=>$row["tel"]
                ));
            }

            if (!$android) {
                echo "<pre>"; 
                print_r($data); 
                echo '</pre>';
            }else
            {
                header('Content-Type: application/json; charset=utf8');
                $json = json_encode(array("webnautes"=>$data), JSON_PRETTY_PRINT+JSON_UNESCAPED_UNICODE);
                echo $json;
            }
        }

        mysqli_free_result($result);

    }
    else{  
        echo "SQL문 처리중 에러 발생 : "; 
        echo mysqli_error($link);
    }
}
else {
    echo "검색할 음식점을 입력하세요 ";
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
         음식점: <input type = "text" name = "storeName" />
         <input type = "submit" />
      </form>
   
   </body>
</html>
<?php
}

?>
