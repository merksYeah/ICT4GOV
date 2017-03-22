<%-- 
    Document   : Login
    Created on : 03 20, 17, 6:43:49 PM
    Author     : migue
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  
  <link rel="stylesheet" href="css/bootstrap.min.css">
  <link rel="stylesheet" href="css/style.css">
  
  
  
</head>
<body>

<section class="mbr-section-full mbr-after-navbar" data-bg-video="https://www.youtube.com/watch?v=RiPWJnCDLoo">

    <div class="mbr-overlay" style="opacity: 0.4; background-color: rgb(0, 0, 0);"></div>

    <div class="mbr-table-cell">

        <div class="container">
            <div style="color:white; background: rgba(0, 0, 0, .3); padding: 50px; text-align:center; border-radius: 10px;">
            <img src="images/LTFRB_Seal.png" width="200" height="200">
            <h2>Login</h2>
              <form method="post" action="login">
                <div class="row form-group col-sm-offset-4">
                  <div class="col-sm-6">
                    <input type="text" placeholder="Username" class="form-control" name="username">
                  </div>
                </div>
                
                <div class="row form-group col-sm-offset-4">
                  <div class="col-sm-6">
                    <input type="password" placeholder="Password" class="form-control" name="password">
                  </div>
                </div>

                <button class="btn btn-primary">Login</button>
              </form>
            </div>
        </div>
    </div>

</section>


  <script src="js/jquery.js"></script>
  <script src="js/jquery.mb.ytplayer.min.js"></script>
  <script src="js/script.js"></script>
  </body>
</html>