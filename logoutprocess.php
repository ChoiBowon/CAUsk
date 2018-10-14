<?php
    session_start();
    session_destroy();

 echo ("<script>alert('로그아웃 되었습니다.');history.go(-2);</script>");
 exit;
     header("location: Main.php");
?>
