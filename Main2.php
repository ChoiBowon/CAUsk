<?php
require_once("connect.php");
session_start();


$result = mysqli_query($conn,"SELECT*FROM menu WHERE storeSerial = '".$_SESSION['storeSerial']."'");
// var_dump($result);

$order = mysqli_query($conn,"SELECT*FROM causk.order WHERE storeName = '".$_SESSION['storeName']."'");
$sql = mysqli_query($conn, "SELECT DISTINCT category FROM menu WHERE storeSerial = '".$_SESSION['storeSerial']."'");
// var_dump($sql);


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
    <link rel="stylesheet" href="dialog.css?v=3">


</head>
<body>
  <script type="text/javascript" src="js/dialog.js?v=1"></script>

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
    <div class="row">
          <?php
          while( $row = mysqli_fetch_assoc($order)){
          //      if(empty($_GET['category']) == true) {
          echo "<div class='col-md-4 col-sm-6 col-xs-12'>";
          echo "<div class='single-team wow fadeInLeft' data-wow-duration='1000ms' data-wow-delay='400ms'>";
          echo "<div class='team-content'>";
          echo "<h4>주문자 : ".$row['userId']."</h4>";
          echo "<h6>".$row['time']."</h6>";
          // echo "<p>".$row['content']."</p>";
          // echo "<ul class='team-social'>
          //     <li><form action='#' method='get'>
          //     <input type='submit' value='Apply'rel='nofollow' class='btn btn-common wow fadeInUp' data-wow-duration='1000ms' data-wow-delay='400ms'>
          //     <input type='hidden' name='menteeapply' value='".$row['name']."'></form>
          //     </li>
          //     </ul>";
              echo "</div>";

              echo "</div>";
              echo "</div>";
          }
           ?>
           <br>
      </form>
    </div>

    <br>
    <br>
    <br>
    <!-- 메뉴판 -->
    <div class="section-header">
        <h1 class="section-title wow fadeInUp" data-wow-duration="1000ms" data-wow-delay="300ms" style="font-family: 'Do Hyeon', sans-serif;">우리 가게 메뉴판</h1>
    </div>
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
    <?php
//result 는 이 storeSerial의 메뉴 전부다
//sql 는 카테고리 8개만 뽑은거
    $category_list = [0];
    $numrow = mysqli_num_rows($sql);
    // var_dump($numrow);

    $i = 0;
    while($row = mysqli_fetch_assoc($sql)){
      $category_list[$i]= $row['category'];
      ++$i;
    }

    //  var_dump($category_list);


       for ($j=0; $j<$numrow; $j++){
         echo "<div class='menu'>";
         echo "<h2 data-wow-duration='1000ms' data-wow-delay='300ms' style='font-family: 'Do Hyeon', sans-serif;'>".$category_list[$j]."</h2>";
         echo "<table class='table table-bordered'  style='font-family: 'Do Hyeon', sans-serif;'>
             <thead>
              <tr>
                  <th scope='col'>Menu</th>
                  <th scope='col'>Size</th>
                  <th scope='col'>Hot/Ice</th>
                  <th scope='col'>Price</th>
              </tr>
              </thead>";

         while($row = mysqli_fetch_assoc($result)){
           if ($row['category'] == $category_list[$j]){
          //  echo "<h2 class='section-title wow fadeInUp' data-wow-duration='1000ms' data-wow-delay='300ms' style='font-family: 'Do Hyeon', sans-serif;'>".$category_list[$j]."</h1>";

               echo "<tbody>";
                echo "<tr>";
                // <a href="#myModal" id="myBtn" data-toggle="modal" data-target="#myModal" style="font-family: 'Do Hyeon', sans-serif; font-size:20px; margin-left: 1060px;" >메뉴 추가</a>
                // echo "<th scope='row'>";
                // echo "<a href='Main2.php?idd=".$row['menuSerial']."'>".$row['menuName']."</a>";
                // echo "</th>";

                echo "<th scope='row'>";
                echo "<a href='#menuModal' class='menuBtn' data-toggle='modal' data-id='".$row['menuSerial']."' data-target='#menuModal'>".$row['menuName']."</a>";                echo "</th>";

                // echo "<th scope='row'>".$row['menuName']."</th>";
                echo "<th scope='row'>".$row['size']."</th>";
                echo "<th scope='row'>".$row['hotIce']."</th>";
                echo "<th scope='row'>".$row['price']."</th>";
                echo "</tr>";
                echo "</tbody>";

         }else{
           break;
         }
       }
           echo "</table>";
           echo "</div>";
     }




          ?>
          <div id="menuModal" class="dialog" tabindex="-1" role="dialog" aria-hidden="true">
            <div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 dialog-content" style="background: #fbfbfb; box-shadow: 5px 5px grey">
              <div class="dialog-header">
                <h2 class="dialog-title" id="ModalLongTitle">메뉴를 수정해주세요.</h2>
                <button type="button" id="close2" class="close" data-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">&times;</span>
                </button>
              </div>
              <div  class="dialog-body">
                <form class="join" action="#" method="post">

                    <div class="form-group">
                    <label for="InputCategory">Category</label>
                    <input id="divdata" type="text" class="form-control" name="Category" style="color:#000" value="커피"  placeholder="Category">
                    </div>

                    <div class="form-group">
                      <label for="InputMenu">메뉴이름</label>
                      <input type="text" class="form-control" name="MenuName" style="color:#000" value="에스프레소 마끼야또" placeholder="ex) 아메리카노">
                    </div>

                    <div class="form-group">
                      <label for="InputSize">size</label>
                      <input type="text" class="form-control" name="Size" style="color:#000" value="ND"  placeholder="필터링으로 할 예정">
                    </div>
                    <div class="form-group">
                      <label for="InputHotIce">hot/ice</label>
                      <input type="text" class="form-control" name="HOTICE" style="color:#000" value="1"  placeholder="필터링으로 할 예정">
                    </div>

                    <div class="form-group">
                      <label for="InputPrice">가격</label>
                      <input type="text" class="form-control" name="Price" style="color:#000" value="3800" placeholder="ex) 5000원">
                    </div>

              </form>
              </div>
              <div class="dialog-footer">
                 <div class="form-group text-center">
                   <button type="submit" id="submit" class="btn btn-default" >메뉴 저장</button>
                </div>
                <!-- <div class="form-group text-center">
                  <button type="submit"  class="btn btn-default" >메뉴 저장</button>
               </div> -->

              </div>
            </form>
          </div>
      </div>




</div>
</div>
    <div class ="panel panel-default" style="border:0px;">

        <div class="panel-footer" style="background-color : #5DA4BE; color:#FFFF; text-align:center;

        bottom:0; width:100%;">Made by 2018 CAUsk team</div>
    </div>


</body>


</html>
