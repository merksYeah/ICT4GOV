<%-- 
    Document   : NewApplication
    Created on : 03 21, 17, 10:16:44 PM
    Author     : migue
--%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

    <title>New Application</title>

    <link href="css/bootstrap.min.css" rel="stylesheet">

    <link href="css/custom.css" rel="stylesheet">

    <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'>

</head>

<body>

    
        <!-- Page Content -->
        <div id="page-content-wrapper">

            <nav class="navbar navbar-default navbar-static-top">
                <div class="navbar-header">
                    <a href="#" class="navbar-brand" style="color: white;">New Application</a>
                </div>

                <div class="collapse navbar-collapse">
                    <ul class="nav navbar-nav navbar-right">
                        <li class="dropdown">
                            <a href="navigate?page=index" style="color: white;"><span class="glyphicon glyphicon-home" color="white"/></a>
                            
                        </li>
                    </ul>
                </div>
            </nav>

			
            <div class="container-fluid">
			 <div id="left-half">
                <form method="post" action="uploadFiles"
                      enctype="multipart/form-data">

                    <div class="row form-group">
                        <div class="col-sm-12">
                            <label class="control-label">First Name:</label>
                            <input type="text" class="form-control" placeholder="Enter name" required="true" name="first_name">
                        </div>
                    </div>
                     <div class="row form-group">
                        <div class="col-sm-12">
                            <label class="control-label">Middle Name:</label>
                            <input type="text" class="form-control" placeholder="Enter name" required="true" name="middle_name">
                        </div>
                    </div>
                     <div class="row form-group">
                        <div class="col-sm-12">
                            <label class="control-label">Last Name:</label>
                            <input type="text" class="form-control" placeholder="Enter name" required="true" name="last_name">
                        </div>
                    </div>
                     <div class="row form-group">
                        <div class="col-sm-12">
                            <label>Your Reference Number: ${app.caseNumber}</label>
                        </div>
                    </div>
				</div>
				 <div id="right-half">
                    <div class="input_fields_wrap">
                         <div class="row form-group">
                        <div class="col-sm-12">
                            <label class="control-label">Verified Petition:</label>
                            <input type="file" name="file" style="margin-top: 10px;" required="true"> 
                        </div>
                        </div>
                        <div class="row form-group">
                        <div class="col-sm-12">
                            <label class="control-label">Valid Government-Issued Id</label>
                            <input type="file" name="file" style="margin-top: 10px;" required="true"> 
                        </div>
                        </div>
                        <div class="row form-group">
                        <div class="col-sm-12">
                            <label class="control-label">Certificate of Registration from LTO:</label>
                            <input type="file" name="file" style="margin-top: 10px;" required="true"> 
                        </div>
                        </div>
                        <div class="row form-group">
                        <div class="col-sm-12">
                            <label class="control-label">Certification of non-delivery of unit/s from the dealer/manufacturer:</label>
                            <input type="file" name="file" style="margin-top: 10px;" required="true"> 
                        </div>
                        </div>
                        <div class="row form-group">
                        <div class="col-sm-12">
                            <label class="control-label">Certification of the LTO Registering Agency:</label>
                            <input type="file" name="file" style="margin-top: 10px;" required="true"> 
                        </div>
                        </div>
      
                    </div>

                    <button type="submit" class="btn btn-primary btn-teal col-sm-3" style="margin-top: 30px;">Submit</button>
                </form>
				</div>
            </div>
            <!-- /#container-fluid -->

        </div>
        <!-- /#page-content-wrapper -->

    </div>

    <script src="js/jquery.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script>
        $("#wrapper").toggleClass("toggled");

        $(document).ready(function() {
            var max_fields      = 5; //maximum input boxes allowed
            var wrapper         = $(".input_fields_wrap"); //Fields wrapper
            var add_button      = $(".add_field_button"); //Add button ID
            
            var x = 1; //initlal text box count
            $(add_button).click(function(e){ //on add input button click
                e.preventDefault();
                if(x < max_fields){ //max input box allowed
                    x++; //text box increment
                    $(wrapper).append('<div><input type="file" name="file" style="margin-top: 5px;" required="true"/><a href="#" class="remove_field">Remove</a></div>'); //add input box
                }
            });
            
            $(wrapper).on("click",".remove_field", function(e){ //user click on remove text
                e.preventDefault(); $(this).parent('div').remove(); x--;
            });
        });
    </script>

</body>

</html>