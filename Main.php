<?php
require_once("connect.php");
// $result = mysqli_query($conn,'SELECT*FROM menu');
 ?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>사장님 사이트</title>
    <link rel="stylesheet" href="bootstrap.css">
    <link href="https://fonts.googleapis.com/css?family=Do+Hyeon" rel="stylesheet">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <link rel="stylesheet" href="dialog.css?v=1">
    <script src="https://www.gstatic.com/firebasejs/5.5.2/firebase.js"></script>

</head>
<body>
  <script type="text/javascript" src="js/dialog.js?v=1"></script>

  <!-- nav -->
  <nav class="navbar navbar-default" style="background-color : #5DA4BE; height:80px;">
    <div class="container-fluid" >
      <!-- Brand and toggle get grouped for better mobile display -->
      <div class="navbar-header" style="margin-top:15px;" >
        <a class="navbar-brand" href="Main.php" style="font-size:40px; text-align:center; color:#FFFF">CAUsk</a>
      </div>

      <!-- Collect the nav links, forms, and other content for toggling -->
      <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1" style="margin-top:15px;">
        <ul class="nav navbar-nav" style="float: right; ">
      <!--//Link -->
      <!-- <li class="active" ><a href="#" style="font-size:20px; text-align:center; color:#FFFF; background-color:#5DA4BE;">Link </a></li> -->
        <!-- <li><a href="" disabled style="font-size:20px; text-align:center; color:#FFFF">{{ request.user.username }}님</a></li> -->
        <!-- <li><a href="#logout" style="font-size:20px; text-align:center; color:#FFFF">Logout</a></li> -->
        <li><a href="Login.php" style="font-size:20px; text-align:center; color:#FFFF">Login</a></li>
        <li><a href="SignUp.php" style="font-size:20px; text-align:center; color:#FFFF">Signup</a></li>
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
        <h1 class="section-title wow fadeInUp" data-wow-duration="1000ms" data-wow-delay="300ms" style="font-family: 'Do Hyeon', sans-serif;">로그인 혹은 회원가입을 먼저 해주세요!</h1>
    </div>
        <div class="row-md-6" >
            <!-- <p style="position:right">
                <a href="#myModal" id="myBtn" data-toggle="modal" data-target="#myModal" style="font-family: 'Do Hyeon', sans-serif; font-size:20px; margin-left: 1060px;" >메뉴 수정</a>
            </p> -->
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
                  <form class="join" action="#" method="post">

                      <div class="form-group">
                      <label for="InputCategory">Category</label>
                      <input type="text" class="form-control" name="Category" style="color:#000" placeholder="Category">
                      </div>

                      <div class="form-group">
                        <label for="InputMenu">메뉴이름</label>
                        <input type="text" class="form-control" name="MenuName" style="color:#000" placeholder="ex) 아메리카노">
                      </div>

                      <div class="form-group">
                        <label for="InputSize">size</label>
                        <input type="text" class="form-control" name="Size" style="color:#000" placeholder="필터링으로 할 예정">
                      </div>
                      <div class="form-group">
                        <label for="InputHotIce">hot/ice</label>
                        <input type="text" class="form-control" name="HOTICE" style="color:#000" placeholder="필터링으로 할 예정">
                      </div>

                      <div class="form-group">
                        <label for="InputPrice">가격</label>
                        <input type="text" class="form-control" name="Price" style="color:#000" placeholder="ex) 5000">
                      </div>

                </form>
                </div>
                <div class="dialog-footer">
                  <button type="button" class="btn btn-primary">저장하기</button>
                </div>
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
