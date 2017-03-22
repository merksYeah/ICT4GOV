
    <title>Re-register Unit</title>

    <link href="css/bootstrap.min.css" rel="stylesheet">

    <link href="css/custom.css" rel="stylesheet">

    <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'>

</head>

<body>

    
        <!-- Page Content -->
        <div id="page-content-wrapper">

            <nav class="navbar navbar-default navbar-static-top">
                <div class="navbar-header">
                    <a href="#" class="navbar-brand" style="color: white;">Re-register Unit</a>
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
                <form method="post" action="uploadFilesRe"
                      enctype="multipart/form-data">

                   
                    <div class="row form-group">
                        <div class="col-sm-12">
                            <label class="control-label">Franchise Name:</label>
                            <input type="text" class="form-control" placeholder="Enter name" required="true" name="franchise">
                        </div>
                    </div>
                    <div class="row form-group">
                        <div class="col-sm-12">
                            <label class="control-label">Vehicle Plate Number:</label>
                            <input type="text" class="form-control" placeholder="Enter name" required="true"name="plate_number">
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
                        <button class="add_field_button btn btn-warning" style="">Add File Input</button>
                        <div><input type="file" name="file" style="margin-top: 10px;" required="true"> <a href="#" class="remove_field">Remove</a></div>
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
                    $(wrapper).append('<div><input type="file" name="" style="margin-top: 5px;" required="true"/><a href="#" class="remove_field">Remove</a></div>'); //add input box
                }
            });
            
            $(wrapper).on("click",".remove_field", function(e){ //user click on remove text
                e.preventDefault(); $(this).parent('div').remove(); x--;
            });
        });
    </script>

</body>

</html>