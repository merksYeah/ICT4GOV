<%-- 
    Document   : NewComplaint
    Created on : 03 19, 17, 10:19:26 PM
    Author     : migue
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>

    <title>New Complaint</title>

    <link href="css/bootstrap.min.css" rel="stylesheet">

    <link href="css/custom.css" rel="stylesheet">

    <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'>

</head>

<body>

  
        <!-- Page Content -->
        <div id="page-content-wrapper">

            <nav class="navbar navbar-default navbar-static-top">
                <div class="navbar-header">
                    <a href="#" class="navbar-brand" style="color: white;">New Complaint</a>
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

                    <form class="row" id="create" action="fileComplaint">

                        <div class="row form-group">
                            <div class="col-sm-12">
                                <label class="control-label">Name:</label>
                                <input type="text" class="form-control" placeholder="Enter name" required="true" name="name">
                            </div>
                        </div>

                        <div class="row form-group">
                            <div class="col-sm-12">
                                <label class="control-label">Email Address:</label>
                                <input type="email" class="form-control" placeholder="Enter email" required="true" name="email">
                            </div>
                        </div>

                        <div class="row form-group">
                            <div class="col-sm-8">
                                <label class="control-label">Phone number:</label>
                                <input type="number" class="form-control" placeholder="Enter phone number" required="true" name="phone">
                            </div>
                        </div>

                        <div class="row form-group">
                            <div class="col-sm-8">
                                <label class="control-label">Mobile Number:</label>
                                <input type="number" class="form-control" placeholder="Enter mobile number" required="true" name="mobile">
                            </div>
                        </div>

                        <div class="row form-group">
                            <div class="col-sm-8">
                                <label class="control-label">Plate No:</label>
                                <input type="text" class="form-control" placeholder="Enter plate number" required="true" name="plate_number">
                            </div>
                        </div>

                </div>

                <div id="right-half">

                    <div class="row">

                        <div class="row form-group">
                            <div class="col-sm-8">
                                <label class="control-label">Subject</label>
                                <input type="text" class="form-control" placeholder="Enter title" required="true" name="subject">
                            </div>
                        </div>

                        <div class="row form-group">
                            <div class="col-sm-5">
                                <label>Type of Complaint:</label>
                                <select class="form-control" name="complaint_type">
                                    <option value="">-</option>
                                    <option value="1">Refusal to convey passenger</option>
                                    <option>Fast Meter</option>
                                    <option>Contracting passenger</option>
                                    <option>Defective Meter</option>
                                    <option>Arrogant/Discourteous Driver</option>
                                    <option>Overcharging/Undercharging</option>
                                    <option>No Flag Down meter</option>
                                    <option>Out of Line</option>
                                    <option>Threatening Passenger</option>
                                    <option>Reckless Driving</option>
                                    <option>Discriminating against passenger</option>
                                    <option>Refusal to grant discount</option>
                                    <option>Hit and Run</option>
                                </select>
                            </div>
                        </div>

                        <div class="row form-group">
                            <div class="col-sm-12">
                                <label class="control-label">Description:</label>
                                <textarea class="form-control" placeholder="Enter description" rows="5" required="true" name="description"></textarea>
                            </div>
                        </div>

                        <div class="row form-group">
                            <div class="col-sm-12">
                                <label class="control-label">Mailing Address:</label>
                                <input type="text" class="form-control" placeholder="Enter mailing address" required="true" name="mailing_address">
                            </div>
                        </div>

                        

                        </form>
                        <button id="submit" type="submit" class="btn btn-primary btn-teal col-sm-2 col-sm-offset-10">Submit</button>
                    </div>

                </div>

            </div>
            <!-- /#container-fluid -->

        </div>
        <!-- /#page-content-wrapper -->

    </div>
     <div id="myModal" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Successfully Filed Complaint!</h4>
      </div>
      <div class="modal-body">
        <p id="ref_num">${message.toString}.</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>

  </div>
</div>
    <script src="js/jquery.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script>
        $("#wrapper").toggleClass("toggled");
         $('#submit').click(function(){
              $.ajax({
            type: "POST",
            url: "fileComplaint",
            data: $("#create").serialize(),
            dataType: "json",
            success: function(data) {
                $('#myModal').modal('show'); 
               $('#ref_num').text("Your Reference Number: " + data.reference_number);
               $('#create').trigger("reset");
                }
            });
        });
        
        
    </script>

</body>

</html>