<?php
require_once("connect.php");
// $result = mysqli_query($conn,'SELECT*FROM post');
 ?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>사장님 사이트</title>
    <link rel="stylesheet" href="bootstrap.css">
    <link href="https://fonts.googleapis.com/css?family=Do+Hyeon" rel="stylesheet">
</head>
<body>
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
        <li><a href="#logout" style="font-size:20px; text-align:center; color:#FFFF">Logout</a></li>
        <li><a href="Login.php" style="font-size:20px; text-align:center; color:#FFFF">Login</a></li>
        <li><a href="SignUp.php" style="font-size:20px; text-align:center; color:#FFFF">Signup</a></li>
    </ul>
  </div>
</div>
</nav>

<div class ="col">
<div class="container">
    <div class="section-header">
        <h1 class="section-title wow fadeInUp" data-wow-duration="1000ms" data-wow-delay="300ms" style="font-family: 'Do Hyeon', sans-serif;">우리 가게 메뉴판</h1>
    </div>

    <table class="table table-bordered"  style="font-family: 'Do Hyeon', sans-serif;">
        <thead>
        <tr>
            <th scope="col">Beverage (HOT/ICED)</th>
            <th scope="col">R</th>
            <th scope="col">L</th>
            <th scope="col">Espresso (HOT/ICED)</th>
            <th scope="col">R</th>
            <th scope="col">L</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <th scope="row">핫초코</th>
            <td>4.9</td>
            <td>5.4</td>
            <td>플랫화이트</td>
            <td>4.4</td>
            <td>0</td>
        </tr>
        <tr>
            <th scope="row">홍차라떼</th>
            <td>4.9</td>
            <td>5.4</td>
            <td>에스프레소</td>
            <td>3.5</td>
            <td>4.0</td>
        </tr>
        <tr>
            <th scope="row">녹차라뗴</th>
            <td>4.9</td>
            <td>5.4</td>
            <td>아메리카</td>
            <td>4.1</td>
            <td>4.6</td>
        </tr>
        <tr>
            <th scope="row">차이라떼</th>
            <!--<td colspan="2">Larry the Bird</td>-->
            <td>4.9</td>
            <td>5.4</td>
            <td>카페라떼</td>
            <td>4.6</td>
            <td>5.1</td>
        </tr>
        </tbody>
    </table>

    <div class="row-md-6" >
        <p style="position:right">
            <a href="#" style="font-family: 'Do Hyeon', sans-serif; font-size:20px; margin-left: 1060px;" >메뉴 수정</a>
        </p>
    </div>

</div>
</div>
    <div class ="panel panel-default" style="border:0px;">

        <div class="panel-footer" style="background-color : #5DA4BE; color:#FFFF; text-align:center; position:absolute;

        bottom:0; width:100%;">Made by 2018 CAUsk team</div>
    </div>


</body>


</html>
