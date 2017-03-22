<%-- 
    Document   : ComplaintDetails
    Created on : 03 21, 17, 11:01:38 AM
    Author     : migue
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>

    <title>Complaint Details</title>

    <link href="css/bootstrap.min.css" rel="stylesheet">

    <link href="css/custom.css" rel="stylesheet">

    <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'>

</head>

<body>

    <div id="wrapper">

        <!-- Sidebar -->
        <div id="sidebar-wrapper">
            <ul class="sidebar-nav">
                <li><a href="showHelpDeskComplaints"><span class="glyphicon glyphicon-home" color="white"></a>
                </li>
                <li><a href="showHelpDeskComplaints"> C<span class="glyphicon glyphicon-list-alt" color="white"></a>
                </li>				
            </ul>
        </div>

        <!-- Page Content -->
        <div id="page-content-wrapper">

            <nav class="navbar navbar-default navbar-static-top">
                <div class="navbar-header">
                    <a href="#" class="navbar-brand" style="color: white;">Complaint Details</a>
                </div>

                <div class="collapse navbar-collapse">
                    <ul class="nav navbar-nav navbar-right">
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" style="color: white;"><span class="glyphicon glyphicon-user" ></span> <span class="caret"></span></a>
                            <ul class="dropdown-menu" role="menu">
                                <li><a href="logout"><span class="glyphicon glyphicon-log-out"></span>&nbsp; Logout</a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </nav>

            <div class="container-fluid">

                <div id="left-half">

                    <form action="updateComplaintStatus">

                        <div class="row form-group">
                            <div class="col-sm-12">
                                <label>Name: ${complaint.complainant.name}</label>
                            </div>
                        </div>

                        <div class="row form-group">
                            <div class="col-sm-12">
                                <label>Email Address: ${complaint.complainant.email}</label>
                               </div>
                        </div>

                        <div class="row form-group">
                            <div class="col-sm-8">
                                <label>Phone number: ${complaint.complainant.phoneNumber}</label>
                            </div>
                        </div>

                        <div class="row form-group">
                            <div class="col-sm-8">
                                <label>Mobile Number: ${complaint.complainant.mobileNumber}</label>
                            </div>
                        </div>

                        <div class="row form-group">
                            <div class="col-sm-12">
                                <label>Mailing Address: ${complaint.complainant.mailingAddress}</label>
                            </div>
                        </div>


                        <div class="row form-group">
                            <div class="col-sm-8">
                                <label>Trade Name: ${complaint.franchise.tradeName}</label>
                            </div>
                        </div>

                        <div class="row form-group">
                            <div class="col-sm-8">
                                <label>Body Number: ${complaint.franchise.vehicles[0].bodyNumber}</label>
                            </div>
                        </div>

                        <div class="row form-group">
                            <div class="col-sm-8">
                                <label>Plate No: ${complaint.franchise.vehicles[0].plateNumber}</label>
                            </div>
                        </div>

                        <div class="row form-group">
                            <div class="col-sm-8">
                                <label>Vehicle Type: ${complaint.franchise.vehicles[0].vehicleType}</label>
                            </div>
                        </div>

                </div>

                <div id="right-half">

                    <div class="row">

                        <div class="row form-group">
                            <div class="col-sm-8">
                                <label>Reference Number: ${complaint.reference_number}</label>
                            </div>
                        </div>

                        <div class="row form-group">
                            <div class="col-sm-8">
                                <label>Date Filed: ${complaint.dateFiled}</label>
                            </div>
                        </div>

                        <div class="row form-group">
                            <div class="col-sm-8">
                                <label>Date Updated: ${complaint.dateUpdated}</label>
                            </div>
                        </div>

                        <div class="row form-group">
                            <div class="col-sm-8">
                                <label>Subject: ${complaint.subject}</label>
                            </div>
                        </div>

                        <div class="row form-group">
                            <div class="col-sm-5">
                                <label>Type of Complain: ${complaint.complaintTypeDesc}</label>
                            </div>
                        </div>

                        <div class="row form-group">
                            <div class="col-sm-12">
                                <label>Description:</label>
                            </div>
                        </div>
						<textarea readonly  class="form-control" rows="5" >${complaint.description}</textarea> 
                         <div class="row form-group">
                            <div class="col-sm-12">
                                <label>Respondents Reply:</label>
                            </div>
                        </div>
						<textarea readonly  class="form-control" rows="5" >${complaint.reply}</textarea> 

                       
                        <div class="row form-group">
                            <div class="col-sm-5">
                                <label>Status:</label>
                                <select name="status_code" class="form-control">
                                    <option value=""></option>
                                    <option value="2">Legal Division</option>
                                    <option value="5">Resolved: Respondent has been Fined</option>
                                    <option value="6">Resolved: Respondent not Guilty</option>
                                </select>
                            </div>
                        </div>

                        <button type="submit" class="btn btn-primary btn-teal col-sm-3 col-sm-offset-9">Submit</button>

                        </form>
                    </div>

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
    </script>

</body>

</html>