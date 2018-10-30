<?php
require_once("connect.php");
session_start();


$result = mysqli_query($conn,"SELECT*FROM menu WHERE storeSerial = '".$_SESSION['storeSerial']."'");
var_dump($result);


$sql = mysqli_query($conn, "SELECT DISTINCT category FROM menu WHERE storeSerial = '".$_SESSION['storeSerial']."'");
var_dump($sql);
// $sql = mysqli_query($conn, 'SELECT DISTINCT category FROM menu WHERE storeSerial = '.$_SESSION['storeSerial'].'');
// $result1 = mysqli_query($conn, $sql);

// $sql = "SELECT DISTINCT category FROM menu WHERE storeSerial = '.$_SESSION['storeSerial'].'";
// $sql2 = mysqli_query($conn,$sql);

 ?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>사장님 사이트</title>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="bootstrap.css">
    <link href="https://fonts.googleapis.com/css?family=Do+Hyeon" rel="stylesheet">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <link rel="stylesheet" href="dialog.css?v=2">


</head>
<body>
  <script type="text/javascript" src="js/dialog.js?v=3"></script>

    <!-- nav -->
  <nav class="navbar navbar-default" style="background-color : #5DA4BE; height:80px;">
    <div class="container-fluid" >
  <!-- Brand and toggle get grouped for better mobile display -->
  <div class="navbar-header" style="margin-top:15px;" >
    <a class="navbar-brand" href="Main2.php" style="font-size:40px; text-align:center; color:#FFFF">CAUsk</a>
  </div>

  <!-- Collect the nav links, forms, and other content for toggling -->
  <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1" style="margin-top:15px;">
    <ul class="nav navbar-nav" style="float: right; ">
      <!--//Link -->
      <!-- <li class="active" ><a href="#" style="font-size:20px; text-align:center; color:#FFFF; background-color:#5DA4BE;">Link </a></li> -->
        <!-- <li><a href="" disabled style="font-size:20px; text-align:center; color:#FFFF">{{ request.user.username }}님</a></li> -->
        <li><a href="logoutprocess.php" style="font-size:20px; text-align:center; color:#FFFF">Logout</a></li>
        <?php
        echo "<li><a href='#' style='font-size:20px; text-align:center; color:#FFFF'>" .$_SESSION['storeName']."</a></li>";
         ?>
        <!-- <li><a href="SignUp.php" style="font-size:20px; text-align:center; color:#FFFF">Signup</a></li> -->
    </ul>
  </div>
</div>
</nav>

<div class ="col">
<div class="container">
  <!-- 주문현황 -->
    <div class="section-header">
        <h1 class="section-title wow fadeInUp" data-wow-duration="1000ms" data-wow-delay="300ms" style="font-family: 'Do Hyeon', sans-serif;">주문 현황</h1>
    </div>

    <!-- 메뉴판 -->
    <div class="section-header">
        <h1 class="section-title wow fadeInUp" data-wow-duration="1000ms" data-wow-delay="300ms" style="font-family: 'Do Hyeon', sans-serif;">우리 가게 메뉴판</h1>
    </div>
    <?php
//result 는 이 storeSerial의 메뉴 전부다
//sql 는 카테고리 8개만 뽑은거
    $category_list = [0];
    $numrow = mysqli_num_rows($sql);
    var_dump($numrow);
    for($i=0; $i<$numrow; $i++){

       $category_list[$i]=mysqli_fetch_assoc($sql);
     }
     var_dump($category_list[0]);

     while($row = mysqli_fetch_assoc($result)){
       for ($j=0; $j<$numrow; $j++){
         echo "<h2 class='section-title wow fadeInUp' data-wow-duration='1000ms' data-wow-delay='300ms' style='font-family: 'Do Hyeon', sans-serif;'>".$category_list[$j]."</h1>";

         echo "<table class='table table-bordered'  style='font-family: 'Do Hyeon', sans-serif;'>
             <thead>
              <tr>
                  <th scope='col'>Menu</th>
                  <th scope='col'>Size</th>
                  <th scope='col'>Hot/Ice</th>
                  <th scope='col'>Price</th>
              </tr>
              </thead>";
         if ($category_list[$j] == $row['category']){
          //  echo "<h2 class='section-title wow fadeInUp' data-wow-duration='1000ms' data-wow-delay='300ms' style='font-family: 'Do Hyeon', sans-serif;'>".$category_list[$j]."</h1>";

         }
       }
     }

    //  while($row = mysqli_fetch_assoc($sql)){
    //    $check = mysqli_query($conn, "SELECT * FROM menu WHERE category = '".$row['category']."'");
     //
    //    if ($row['storeSerial'] == $_SESSION['storeSerial']){
    //      echo "<tbody>";
    //      echo "<tr>";
    //      echo "<th scope='row'>".$row['menuName']."</th>";
    //      echo "<th scope='row'>".$row['size']."</th>";
    //      echo "<th scope='row'>".$row['hotIce']."</th>";
    //      echo "<th scope='row'>".$row['price']."</th>";
    //      echo "</tr>";
    //      echo "</tbody>";
    //    }
    //  }



    echo "</table>";
            // for ($i = 0 ; $i<count($category_list); $i++){
            //   if ($row[2] == $category_list[$i]){
            //     echo "<tbody>";
            //     echo "<tr>";
            //     echo "<th scope='row'>".$row['menuName']."</th>";
            //     echo "<th scope='row'>".$row['size']."</th>";
            //     echo "<th scope='row'>".$row['hotIce']."</th>";
            //     echo "<th scope='row'>".$row['price']."</th>";
            //     echo "</tr>";
            //     echo "</tbody>";
            //   }
            // }


  //   console.log($category_list);
  //   echo "<table class='table table-bordered'  style='font-family: 'Do Hyeon', sans-serif;'>
  //       <thead>
  //       <tr>
  //           <th scope='col'>Menu</th>
  //           <th scope='col'>Size</th>
  //           <th scope='col'>Hot/Ice</th>
  //           <th scope='col'>Price</th>
  //       </tr>
  //       </thead>";
  //       var_dump($category_list);
  //       // echo($category_list);
  //       while($row = mysqli_fetch_assoc($result)){
  //         for ($i = 0 ; $i<count($category_list); $i++){
  //           if ($row[2] == $category_list[$i]){
  //             echo "<tbody>";
  //             echo "<tr>";
  //             echo "<th scope='row'>".$row['menuName']."</th>";
  //             echo "<th scope='row'>".$row['size']."</th>";
  //             echo "<th scope='row'>".$row['hotIce']."</th>";
  //             echo "<th scope='row'>".$row['price']."</th>";
  //             echo "</tr>";
  //             echo "</tbody>";
  //           }
  //         }
   //
   //
  //         // <tr>
  //         //     <th scope="row">핫초코</th>
  //         //     <td>4.9</td>
  //         //     <td>5.4</td>
  //         //     <td>플랫화이트</td>
  //         //     <td>4.4</td>
  //         //     <td>0</td>
  //         // </tr>
  //         // </tbody>
  //       }
  //         echo "</table>";

          ?>


    <div class="row-md-6" >
        <p style="position:right">
            <a href="#myModal" id="myBtn" data-toggle="modal" data-target="#myModal" style="font-family: 'Do Hyeon', sans-serif; font-size:20px; margin-left: 1060px;" >메뉴 추가</a>
            <!-- <button type="button" id="myBtn" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal">메뉴 추가</button> -->
        </p>
        <!-- Modal -->
        <div id="myModal" class="dialog" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 dialog-content" style="background: #fbfbfb; box-shadow: 5px 5px grey">
            <div class="dialog-header">
              <h2 class="dialog-title" id="ModalLongTitle">메뉴를 추가해주세요.</h2>
              <button type="button" id="close" class="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">&times;</span>
              </button>
            </div>
            <div class="dialog-body">
              <form class="join" name="join" action="insertmenuprocess.php" method="post">

                  <div class="form-group">
                  <label for="InputCategory">Category</label>
                  <div class="row">
                    <div class="col-xs-3 col-sm-3 col-md-3">
                      <select name="category_selbox" onchange="categoryDirect()">
                        <option value="none">선택</option>
                        <option value="커피">커피</option>
                        <option value="콜드브루">콜드브루</option>
                        <option value="direct">직접 입력</option>
                      </select>
                    </div>
                    <div class="col-xs-9 col-sm-9 col-md-9">
                      <input type="text" value="" disabled id="dfdf" class="form-control" name="CategoryDirect" style="color:#000" placeholder="Category">
                    </div>
                  </div>
                </div>

                  <div class="form-group">
                    <div class="row">
                      <div class="col-xs-3 col-sm-3 col-md-3">
                          <label for="InputMenu">메뉴이름</label>
                      </div>
                      <div class="col-xs-9 col-sm-9 col-md-9">
                        <input type="text" class="form-control" name="MenuName" style="color:#000" placeholder="ex) 아메리카노">

                      </div>
                    </div>

                  </div>

                  <div class="form-group">
                    <label for="InputSize">size</label>
                    <div class="row">
                      <div class="col-xs-3 col-sm-3 col-md-3">
                        <select name="size_selbox" onchange="sizeDirect()">
                          <option value="none">선택</option>
                          <option value="tall">Tall</option>
                          <option value="grande">Grande</option>
                          <option value="direct">직접 입력</option>
                        </select>
                      </div>
                      <div class="col-xs-9 col-sm-9 col-md-9">
                        <input type="text" value="" disabled id="selboxDirect" class="form-control" name="SizeDirect" style="color:#000" placeholder="Size">
                      </div>
                    </div>
                  </div>

                  <div class="form-group">
                    <label for="InputHotIce">hot / ice</label>
                    <div class="row">
                      <div class="col-xs-3 col-sm-3 col-md-3">
                        <select name="hot_selbox" onchange="hot()">
                          <option value="none">선택</option>
                          <option value="hot">Hot only</option>
                          <option value="ice">Ice only</option>
                          <option value="hot and ice">둘 다</option>
                          <option value="not choice">선택 안함</option>
                        </select>
                      </div>
                      <div class="col-xs-9 col-sm-9 col-md-9">
                        <input type="text" value="" disabled id="selboxDirect" class="form-control" name="hotDirect" style="color:#000" placeholder="Hot or Ice">
                      </div>
                    </div>
                  </div>


                  <div class="form-group">
                    <div class="row">
                      <div class="col-xs-3 col-sm-3 col-md-3">
                        <label for="InputPrice">가격</label>
                      </div>
                      <div class="col-xs-6 col-sm-6 col-md-6">
                        <input type="text" class="form-control" name="Price" style="color:#000" placeholder="ex) 5000">
                      </div>
                      <div class="col-xs-3 col-sm-3 col-md-3">
                        <label for="InputPrice">원</label>
                      </div>
                    </div>

                  </div>


            </div>
            <div class="dialog-footer">
               <!-- <div class="form-group text-center">
                 <button type="submit" id="submit" class="btn btn-default" >메뉴 저장</button>
              </div> -->
              <div class="form-group text-center">
                <button type="submit"  class="btn btn-default" >메뉴 저장</button>
             </div>

            </div>
          </form>
        </div>
    </div>
    </div>

</div>
</div>
    <div class ="panel panel-default" style="border:0px;">

        <div class="panel-footer" style="background-color : #5DA4BE; color:#FFFF; text-align:center; position:absolute;

        bottom:0; width:100%;">Made by 2018 CAUsk team</div>
    </div>


</body>


</html>
