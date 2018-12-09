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
$latitude=isset($_POST['latitude']) ? $_POST['latitude'] : '';
$longitude=isset($_POST['longitude']) ? $_POST['longitude'] : '';
$android = strpos($_SERVER['HTTP_USER_AGENT'], "Android");

    $sql="SELECT storeName, ( 6371 * acos( cos( radians('$latitude') ) * cos( radians(latitude) ) * cos( radians(longitude) - radians('$longitude') ) + sin( radians('$latitude') ) * sin( radians( latitude ) ) ) ) AS distance FROM causk.store HAVING distance < 0.075 ORDER BY distance LIMIT 0 , 10;";

   
    $result=mysqli_query($link,$sql);
    $data = array();   
    if($result){  
    
        $row_count = mysqli_num_rows($result);

        if ( 0 == $row_count ){
            
            array_push($data,
                array( 'storeName'=>'NULL')
            );

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
        else{

            while($row=mysqli_fetch_array($result)){
                array_push($data, 
                    array(
                    'storeName'=>$row["storeName"],
                    'distance'=>$row["distance"]
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


mysqli_close($link);  

?>

<?php

$android = strpos($_SERVER['HTTP_USER_AGENT'], "Android");

if (!$android){
?>

<html>
   <body>
   
      <form action="<?php $_PHP_SELF ?>" method="POST">
         현재위치//
         위도: <input type = "text" name = "latitude" />
         경도:<input type = "text" name = "longitude" />
         <input type = "submit" />
      </form>
   
   </body>
</html>
<?php
}

?>
