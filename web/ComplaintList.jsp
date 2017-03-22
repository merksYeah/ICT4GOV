<%-- 
    Document   : ComplaintList
    Created on : 03 20, 17, 9:11:27 PM
    Author     : migue
--%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>

    <title>List of Complaints</title>

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
                    <a href="#" class="navbar-brand" style="color: white;">List of Complaints</a>
                </div>

                <div class="collapse navbar-collapse">
                    <ul class="nav navbar-nav navbar-right">
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" style="color: white;"><span class="glyphicon glyphicon-user" ></span><span class="caret"></span>&nbsp;&nbsp;&nbsp;</a>
                            <ul class="dropdown-menu" role="menu">
                                <li><a href="logout"><span class="glyphicon glyphicon-log-out"></span>&nbsp; Logout</a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </nav>

            <div class="container-fluid">

                <table id="" class="display" cellspacing="0" width="100%">
                    <thead>
                        <tr>
                            <th>Reference Number</th>
                            <th>Subject</th>
                            <th>Complainant Name</th>
                            <th>Vehicle Plate Number</th>
                            <th>Type</th>
                            <th>Date Filed</th>
                            <th>Date Updated</th>
                            <th>Status</th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${complaints}" var="Complaint">
                        <tr>
                        <td><a href="showComplaintDetails?ref_num=<c:out value="${Complaint.reference_number}"/>"><c:out value="${Complaint.reference_number}"/></td>
                        <td><c:out value="${Complaint.subject}"/></td>
                        <td><c:out value="${Complaint.complainant.name}"/></td>
                        <td><c:out value="${Complaint.franchise.vehicles[0].plateNumber}"/></td>
                        <td><c:out value="${Complaint.complaintTypeDesc}"/></td>
                        <td><c:out value="${Complaint.dateFiled}"/></td>
                        <td><c:out value="${Complaint.dateUpdated}"/></td>
                        <td><c:out value="${Complaint.complaintStatusDesc}"/></td>
                        </tr>
                       
                    </c:forEach>

                    </tbody>
                </table>

                <br/>
                <button type="submit" class="btn btn-danger col-sm-1 col-sm-offset-11 togglebutton">Delete</button>

            </div>
            <!-- /#container-fluid -->

        </div>
        <!-- /#page-content-wrapper -->

    </div>

    <script src="js/jquery.js"></script>
    <script src="js/bootstrap.min.js"></script>

    <link rel="stylesheet" type="text/css" href="js/jquery.dataTables.min.css" />

    <script type="text/javascript" src="js/jquery.dataTables.min.js"></script>
    <script>
        $(document).ready(function() {
            $(".togglebutton").hide();
            $("#wrapper").toggleClass("toggled");
            $('table.display').DataTable({
                columnDefs: [{
                    width: 5,
                    targets: 0
                }]
            });

            var checked = 0;

            //select all checkboxes
            $("#select_all").change(function() { //"select all" change
                $(".checkbox").prop('checked', $(this).prop("checked")); //change all ".checkbox" checked status
                $(".togglebutton").fadeIn();

                checked = $('.checkbox:checked').size();

                if ($(this).prop("checked") == false) {
                    $(".togglebutton").fadeOut();
                    checked = 0;
                }
            });

            //".checkbox" change 
            $('.checkbox').change(function() {
                $(".togglebutton").fadeIn();

                if ($(this).prop("checked") == false) {
                    checked--;

                    if (checked <= 0) {
                        $(".togglebutton").fadeOut();
                    }

                }

                //uncheck "select all", if one of the listed checkbox item is unchecked
                if (false == $(this).prop("checked")) { //if this item is unchecked
                    $("#select_all").prop('checked', false); //change "select all" checked status to false

                }
                //check "select all" if all checkbox items are checked
                if ($('.checkbox:checked').length == $('.checkbox').length) {
                    $("#select_all").prop('checked', true);
                }

            });
        });
    </script>

</body>

</html>
