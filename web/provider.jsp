<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8" />
	<link rel="icon" type="image/png" href="assets/img/favicon.ico">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

	<title>Dashboard</title>

	<meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport' />
    <meta name="viewport" content="width=device-width" />


    <!-- Bootstrap core CSS     -->
    <link href="assets/css/bootstrap.min.css" rel="stylesheet" />

    <!-- Animation library for notifications   -->
    <link href="assets/css/animate.min.css" rel="stylesheet"/>

    <!--  Light Bootstrap Table core CSS    -->
    <link href="assets/css/light-bootstrap-dashboard.css" rel="stylesheet"/>

    <!--   Personal style  -->
    <link href="assets/css/styles.css" rel="stylesheet"/>

    <!--  CSS for Demo Purpose, don't include it in your project     -->
    <link href="assets/css/demo.css" rel="stylesheet" />


    <!--     Fonts and icons     -->
    <link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
    <link href='http://fonts.googleapis.com/css?family=Roboto:400,700,300' rel='stylesheet' type='text/css'>
    <link href="assets/css/pe-icon-7-stroke.css" rel="stylesheet" />

</head>
<body>

<div class="wrapper">
    <div class="sidebar" data-color="purple" data-image="assets/img/sidebar-5.jpg">

    <!--   you can change the color of the sidebar using: data-color="blue | azure | green | orange | red | purple" -->


    	<div class="sidebar-wrapper">
            <div class="logo">
                <a class="simple-text">
                    Ride Sharing
                </a>
            </div>

            <ul class="nav">
                <li class="active">
                    <a href="/order">
                        <i class="fa fa-tachometer"></i>
                        <p>Dashboard</p>
                    </a>
                </li>
                <li>
                    <a href="/myorder">
                        <i class="fa fa-map-marker"></i>
                        <p>My Confirmed Order</p>
                    </a>
                </li>
                <li>
                    <a href="user.jsp">
                        <i class="fa fa-user"></i>
                        <p>User Profile</p>
                    </a>
                </li>
            </ul>
    	</div>
    </div>

    <div class="main-panel">
        <nav class="navbar navbar-default navbar-fixed">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navigation-example-2">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="#">Welcome <c:out value = "${user.name}" /></a>
                </div>
                <div class="collapse navbar-collapse">
                    <ul class="nav navbar-nav navbar-left">
                        <li>
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                <i class="fa fa-dashboard"></i>
                            </a>
                        </li>
                    </ul>

                    <ul class="nav navbar-nav navbar-right">
                        <li>
                            <a href="logout">
                                Log out
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
        <div class="content">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-md-12">
                        <div class="card">
                            <div class="header">
                                <h4 class="title">List of available ride requests</h4>
                                <%--<p class="category">Here is a subtitle for this table</p>--%>
                                <a href="/order?action=refresh"><i class="fa fa-refresh pull-right"></i></a>
                            </div>
                            <div class="content table-responsive table-full-width">
                                <table class="table table-hover table-striped">
                                    <thead>
                                    <tr><th>Date</th>
                                        <th>Requestor Name</th>
                                        <th>Departure</th>
                                        <th>Destination</th>
                                        <th>Comment</th>
                                        <th colspan="2"></th>
                                    </tr></thead>
                                    <tbody>

                                    <c:forEach items="${orders}" var="order">
                                        <tr id="${order.id}">
                                            <td><c:out value="${order.date}" /></td>
                                            <td><c:out value="${order.requestorUser.name}" /></td>
                                            <td><c:out value="${order.departure}" /></td>
                                            <td><c:out value="${order.destination}" /></td>
                                            <td><c:out value="${order.reqComment}" /></td>
                                            <td>
                                                <!-- Modal -->
                                                <button type="button" class="btn btn-primary btn_triggerRequestorModal btnCustomLink" data-id="${order.id}" data-comment = ${order.reqComment} data-toggle="modal"  data-target="#userDetailModal"  >
                                                    View Detail
                                                </button>
                                            </td>
                                            <td>
                                                <button type="button" class="btn btn-primary btn_confirmOrderModal btnCustomLink" data-id="${order.id}" data-toggle="modal"  data-target="#confirmModal"  >
                                                    Confirm
                                                </button>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                   </tbody>
                                </table>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>


    </div>
</div>

<div class="modal fade" id="userDetailModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Requestor Detail
                    <button type="button" class="close pull-right" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">X</span>
                    </button>
                </h5>

            </div>
            <div class="modal-body">
                <div class="form-group">
                    <label class="col-form-label">Name :</label>
                    <p id="req_name"></p>
                </div>
                <div class="form-group">
                    <label class="col-form-label">Birthdate : </label>
                    <p id="ride_date"></p>
                </div>
                <div class="form-group">
                    <label class="col-form-label">Address:</label>
                    <p id="ride_address"></p>
                </div>
                <div class="form-group">
                    <label class="col-form-label">Contact No:</label>
                    <p id="ride_phone"></p>
                </div>
                <div class="form-group">
                    <label class="col-form-label">Email :</label>
                    <p id="ride_email"></p>
                </div>

                <div class="form-group">
                    <label class="col-form-label">Departure:</label>
                    <p id="ride_departure"></p>
                </div>
                <div class="form-group">
                    <label class="col-form-label">Destination:</label>
                    <p id="ride_destination"></p>
                </div>
                <div class="form-group">
                    <label class="col-form-label">Requested Date:</label>
                    <p id="riderequest_date"></p>
                </div>
                <div class="form-group">
                    <label class="col-form-label">Comment:</label>
                    <p id="req_comment"></p>
                </div>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="confirmModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Confirm
                    <button type="button" class="close pull-right" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </h5>

            </div>
            <div class="modal-body">
                <form>
                    <div class="form-group">
                       <h3>Are you Sure?</h3>
                    </div>
                    <div class="form-group">
                        <label for="provider-comment" class="col-form-label">Message: (Optional) </label>
                        <textarea class="form-control" id="provider-comment"></textarea>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                <button type="button" id= "btn_confirmOrder" class="btn btn-primary">Confirm Order</button>
            </div>
        </div>
    </div>
</div>
</body>

        <!--   Core JS Files   -->
    <script src="assets/js/jquery-1.10.2.js" type="text/javascript"></script>
	<script src="assets/js/bootstrap.min.js" type="text/javascript"></script>

	<!--  Checkbox, Radio & Switch Plugins -->
	<script src="assets/js/bootstrap-checkbox-radio-switch.js"></script>

	<!--  Charts Plugin -->
	<script src="assets/js/chartist.min.js"></script>

    <!--  Notifications Plugin    -->
    <script src="assets/js/bootstrap-notify.js"></script>

    <!--  Google Maps Plugin    -->
    <script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?sensor=false"></script>
    <script src="assets/js/scripts.js"></script>
    <!-- Light Bootstrap Table Core javascript and methods for Demo purpose -->
	<script src="assets/js/light-bootstrap-dashboard.js"></script>

	<!-- Light Bootstrap Table DEMO methods, don't include it in your project! -->
	<script src="assets/js/demo.js"></script>

    <script>
        // $().ready(function(){
        //     demo.initGoogleMaps();
        // });
    </script>

</html>
