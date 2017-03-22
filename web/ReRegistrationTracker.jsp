<%-- 
    Document   : ApplicationTracker
    Created on : 03 22, 17, 9:01:46 PM
    Author     : migue
--%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>

    <title>Re-registration Tracker</title>

    <link href="css/bootstrap.min.css" rel="stylesheet">

    <link href="css/custom.css" rel="stylesheet">

    <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'>

</head>

<body>

    

        <!-- Page Content -->
        <div id="page-content-wrapper">

           <nav class="navbar navbar-default navbar-static-top">
                <div class="navbar-header">
                    <a href="#" class="navbar-brand" style="color: white;">Re-registration Tracker</a>
                </div>

                <div class="collapse navbar-collapse">
                    <ul class="nav navbar-nav navbar-right">
                        <li class="dropdown">
                            <a href="logout" style="color: white;"><span class="glyphicon glyphicon-home" color="white"></a>
                            
                        </li>
                    </ul>
                </div>
            </nav>

            <div class="container-fluid">
			<div id="left-half">

                <form action="showReRegistrationTracker">

                    <div class="row form-group">
                        <div class="col-sm-12">
                            <label class="control-label">Reference Number:</label>
                            <input type="number" class="form-control" placeholder="Enter reference number" required="true" name="ref_num">
                        </div>
                    </div>
            
                    <button type="submit" class="btn btn-primary btn-teal col-sm-3" style="margin-top: 30px;">Submit</button>
                </form>
			</div>			
			<div id="right-half">
				<div class="row form-group">
                           <div class="panel panel-default">
                            <div class="panel-heading">State of Complaint</div>
                            <c:forEach items="${applications}" var="Application">
                                    <div class="panel-body">
                                         Status: <c:out value="${Application.status}"/>
                                    </div>
                                   
                                   <c:choose>
                                    <c:when test="${empty Application.hearingDate}">
                                    <div class="panel-body">
                                      Hearing Date = Has not been set
                                    </div>
                                    </c:when>
                                    <c:otherwise>
                                    <div class="panel-body">
                                      Hearing Date = <c:out value="${Application.hearingDate}"/>
                                    </div>
                                   
                                    </c:otherwise>
                                    </c:choose>
                                    <c:choose>
                                    <c:when test="${empty Application.inspectionDate}">
                                    <div class="panel-body">
                                      Inspection Date = Has not been set
                                    </div>
                                    </c:when>
                                    <c:otherwise>
                                    <div class="panel-body">
                                      Inspection Date = <c:out value="${Application.inspectionDate}"/>
                                    </div>
                                   
                                    </c:otherwise>
                                    </c:choose>
                                    </textarea
                                </c:forEach>
                           </div>
							
						
                </div>
                               
                           
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