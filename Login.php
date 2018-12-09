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
        <li><a href="#" style="font-size:20px; text-align:center; color:#FFFF">Login</a></li>
        <li><a href="SignUp.php" style="font-size:20px; text-align:center; color:#FFFF">Signup</a></li>
    </ul>
  </div>
</div>
</nav>

<div class ="col">
<div class="container">
    <div class="section-header">
        <h1 class="section-title wow fadeInUp" data-wow-duration="1000ms" data-wow-delay="300ms" style="font-family: 'Do Hyeon', sans-serif;">Login</h1>
    </div>
        <div class="row">
          <div class="col-md-6 wow fadeInLeft" data-wow-duration="1000ms" data-wow-delay="300ms">
              <form role="form" class="form-horizontal" name="loginform" id="loginform" action="loginprocess.php" method="post">
                <div class="col-md-6 col-md-offset-3">

                <div class="form-group">
                    <label for="InputLoginId">ID</label>
                    <input type="text" name="userID" id="user_login" class="form-control" placeholder="userID">
                </div>
                <div class="form-group">
                  <label for="InputLoginPassword">Password</label>
                    <input type="password" name="password" id="user_pass" class="form-control" placeholder="password">
                </div>
                <div class="form-group">
                  <div class="col-lg-offset-2 col-lg-10">
                    <input type="submit" style="color:#FFF; background-color : #5DA4BE; font-family: 'Do Hyeon', sans-serif; border-radius: 5px;
             padding: 5 9 5 9; margin-left: 330px;" name="wp-submit" id="wp-submit" class="btn btn-common wow fadeInUp" value="로그인!" data-wow-duration="1000ms" data-wow-delay="400ms">
                  </div>
                </div>
                </div>
              </form>


          </div>
        </div>


</div>
    <div class ="panel panel-default" style="border:0px;">

        <div class="panel-footer" style="background-color : #5DA4BE; color:#FFFF; text-align:center; position:absolute;

        bottom:0;  width:100%;">Made by 2018 CAUsk team</div>
    </div>

  </body>
</html>
