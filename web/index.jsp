<%-- 
    Document   : index
    Created on : 03 20, 17, 6:40:43 PM
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
		<a class="btn btn-lg btn-default" href="navigate?page=Login">Login</a>
							<a class="btn btn-lg btn-danger" href="navigate?page=CompTracker">Complaint Tracker</a><a class="btn btn-lg btn-danger" href="navigate?page=AppTrack">Application Tracker</a><a class="btn btn-lg btn-danger" href="navigate?page=RegTrack">Re-registration Tracker</a>
            
            <div style="color:white; background: rgba(0, 0, 0, .3); padding: 50px; text-align:center; border-radius: 10px;">
            <img src="images/LTFRB_Seal.png" width="200" height="200">
            <h1>Land Transportation Franchising & Regulatory Board</h1>
                    <p>Welcome!</p>
                    <a class="btn btn-lg btn-danger" href="navigate?page=FileComplaint">File a Complaint</a> <a class="btn btn-lg btn-success" href="navigate?page=NewApplication">Register a Franchise</a><a class="btn btn-lg btn-primary" href="navigate?page=ReRegister">Re-register Unit</a>
</div>
        </div>
    </div>

</section>


  <script src="js/jquery.js"></script>
  <script src="js/jquery.mb.ytplayer.min.js"></script>
  <script src="js/script.js"></script>
  </body>
</html>