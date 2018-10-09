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
        <h1 class="section-title wow fadeInUp" data-wow-duration="1000ms" data-wow-delay="300ms" style="font-family: 'Do Hyeon', sans-serif;">Sign Up</h1>
    </div>
        <div class="row">
          <div class="col-md-6 wow fadeInLeft" data-wow-duration="1000ms" data-wow-delay="300ms">
            <form class="join" action="signupprocess.php" method="post">

            <div class="col-md-6 col-md-offset-3">

                <div class="form-group">
                <label for="InputId">ID</label>
                <input type="text" class="form-control" name="Id" style="color:#000" placeholder="아이디">
                </div>

                <div class="form-group">
                  <label for="InputPassword1">Password</label>
                  <input type="password" class="form-control" name="Password" style="color:#000" placeholder="비밀번호">
                </div>

                <div class="form-group">
                  <label for="InputPassword2">비밀번호 확인</label>
                  <input type="password" class="form-control" name="Password2" style="color:#000" placeholder="비밀번호 확인">
                </div>
                <div class="form-group">
                  <label for="username">가게 이름</label>
                  <input type="text" class="form-control" name="Store_Name" style="color:#000" placeholder="가게 이름을 입력해 주세요 ex) 스타벅스 서현점">
                </div>

                <div class="form-group">
                  <label for="username">지점</label>
                  <input type="text" class="form-control" name="Place" style="color:#000" placeholder="지점 위치를 입력해 주세요 ex) 분당구 서현동">
                </div>

    			      <div class="form-group">
                  <label for="InputEmail">전화번호</label>
                  <input type="text" class="form-control" name="PhoneNum" style="color:#000" placeholder="전화번호를 입력해 주세요">
                </div>
<!--
                <div class="form-group">
                  <label for="InputEmail">이메일 주소</label>
                  <input type="email" class="form-control" name="Email" style="color:#000" placeholder="이메일 주소를 입력해 주세요">
                </div> -->

                <!-- <div class="form-group">
                  <label for="InputDept">소속기관/학교명</label>
                  <input type="text" class="form-control" name="Department" style="color:#000" placeholder="소속기관/학교를 입력해 주세요">
                </div> -->

                <!-- <div class="form-group">
                  <label for="InputYear">직급/학년</label>
                  <input type="text" class="form-control" name="Year" style="color:#000" placeholder="직급/학년을 입력해 주세요">
                </div> -->
                <!-- <div class="form-group">
                  <label for="InputMajor">학과/전공</label>
                  <input type="text" class="form-control" name="Major" style="color:#000" placeholder="학과/전공을 입력해 주세요">
                </div> -->
                <div class="form-group text-center">
                  <button type="submit" class="btn btn-info">회원가입<i class="fa fa-check spaceLeft"></i></button>
                  <button type="submit" class="btn btn-warning">가입취소<i class="fa fa-times spaceLeft"></i></button>
                </div>
            </div>
          </form></div>
        </div>


</div>
    <div class ="panel panel-default" style="border:0px;">

        <div class="panel-footer" style="background-color : #5DA4BE; color:#FFFF; text-align:center; position:absolute;

        bottom:0; width:100%;">Made by 2018 CAUsk team</div>
    </div>

  </body>
</html>
