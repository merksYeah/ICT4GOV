<%-- 
    Document   : ApplicationDetails
    Created on : 03 22, 17, 12:34:55 AM
    Author     : migue
--%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>

    <title>Application Details</title>

    <link href="css/bootstrap.min.css" rel="stylesheet">

    <link href="css/custom.css" rel="stylesheet">

    <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'>

</head>

<body>

    <div id="wrapper">

        <!-- Sidebar -->
       <div id="sidebar-wrapper">
            <ul class="sidebar-nav">
                <li><a href="showMotorApplications"><span class="glyphicon glyphicon-home" color="white"></a>
                </li>
               
				<li><a href="showMotorApplications"> A<span class="glyphicon glyphicon-list-alt" color="white"></a>
                </li>
				<li><a href="showMotorReRegister"> R<span class="glyphicon glyphicon-list-alt" color="white"></a>
                </li>
				
            </ul>
        </div>

        <!-- Page Content -->
        <div id="page-content-wrapper">

            <nav class="navbar navbar-default navbar-static-top">
                <div class="navbar-header">
                    <a href="#" class="navbar-brand" style="color: white;">Application Details</a>
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


                <form method="post" action="updateApplicationStatus">
					<div id="left-half">
                    <div class="row form-group">
                        <div class="col-sm-12">
                            <label>Reference Number: ${applicant.caseNumber}</label>
                        </div>
                    </div>

                    <div class="row form-group">
                        <div class="col-sm-12">
                            <label>Name: ${applicant.applicantName}</label>
                        </div>
                    </div>

                    <div class="row form-group">
                        <div class="col-sm-12">
                            <label>Date Received: ${applicant.dateReceived}</label>
                        </div>
                    </div>

                    <div class="row form-group">
                        <div class="col-sm-12">
                            <label>Date Updated: ${applicant.dateUpdated}</label>
                        </div>
                    </div>

                    <div class="row form-group">
                        <div class="col-sm-12">
                            <label>Attachments:</label>
                            <table>
                                <c:forEach items="${attachments}" var="Attachment">
                                    <tr>
                                        <td><span class="glyphicon glyphicon-file" ></i></td>
                                        <td><a href="downloadFiles?id=<c:out value="${Attachment.id}"/>"><c:out value="${Attachment.fileName}"/></a></td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </div>
                    </div>
					</div>
					<div id="right-half">
                        
					<div class="row form-group">
                            <div class="col-sm-7">
                                <label>Status:</label>
                                <select class="form-control" name="status">
                                    <c:choose>
                                        <c:when test="${applicant.status eq 'Inspection Date Set'}">
                                            <option value="3">Technical Evaluation Division: Preparing Order</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="8">Application Approved</option>
                                        </c:otherwise>
                                    </c:choose>
                                    
                                    
                                    
                                </select>
                            </div>
                        </div>
						<button type="submit" class="btn btn-primary btn-teal col-sm-3 ">Submit</button>
					</div>

                </form>

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