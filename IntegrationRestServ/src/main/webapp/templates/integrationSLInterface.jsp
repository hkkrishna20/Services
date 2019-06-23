<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>integration SL Interface</title>

<script src="webjars/jquery/1.11.1/jquery.min.js"></script>
<script src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<!-- <script src="js/1.js"></script> -->

<link href="webjars/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<!------ Include the above in your HEAD tag ---------->
<link rel="stylesheet"
	href="webjars/font-awesome/4.7.0/css/font-awesome.min.css"></link>
<link href="css/1.css" rel="stylesheet" type="text/css"></link>
</head>

<body>
	<form class="form-horizontal" role="form" action="slRequest"
		method="post">
		<div class="row">
			<div class="col-md-4 col-md-offset-4">
				<div class="container">

					<div class="col-md-5">
						<br style="clear: both">
						<div class="form-group">

							<div class="pure-control-group">
								<legend>Interface Form Details</legend>
								<input type="text" class="form-control" id="appName"
									name="appName" placeholder="Applicant Name" required>
							</div>
						</div>
						<div class="form-group">
							<div class="pure-control-group">
								<input type="text" class="form-control" id="addressLine1"
									name="addressLine1" placeholder="addressLine1" required>
							</div>
						</div>
						<div class="form-group">
							<div class="pure-control-group">
								<input type="text" class="form-control" id="addressLine2"
									name="addressLine2" placeholder="addressLine2" required>
							</div>
						</div>
						<div class="form-group">
							<div class="pure-control-group">
								<input type="text" class="form-control" id="mobile"
									name="mobile" placeholder="Mobile Number" required>
							</div>
						</div>
						<div class="form-group">
							<div class="pure-control-group">
								<input type="text" class="form-control" id="zipCode"
									name="zipCode" placeholder="zipCode" required>
							</div>
						</div>
						<div class="form-group">
							<div class="pure-control-group">
								<input type="text" class="form-control" id="coAppName"
									name="coAppName" placeholder="Co Applicant Name" required>
							</div>
						</div>
						<div class="form-group">
							<div class="pure-control-group">
								<input type="text" class="form-control" id="coAddressLine1"
									name="coAddressLine1" placeholder="coAddressLine1" required>
							</div>
						</div>
						<div class="form-group">
							<div class="pure-control-group">
								<input type="text" class="form-control" id="coAddressLine2"
									name="coAddressLine2" placeholder="coAddressLine2" required>
							</div>
						</div>
						<div class="form-group">
							<div class="pure-control-group">
								<input type="text" class="form-control" id="comobile"
									name="comobile" placeholder="Mobile Number" required>
							</div>
						</div>
						<div class="form-group">
							<div class="pure-control-group">
								<input type="text" class="form-control" id="cozipCode"
									name="cozipCode" placeholder="cozipCode" required>
							</div>
						</div>
						<div class="form-group">
							<div class="pure-control-group">
								<input type="hidden" name="testType" value="InterfaceService">
								<input type="hidden" name="userId" value="InterfaceService">
								<input type="hidden" name="password" value="InterfaceService">
								<input type="hidden" name="environment" value="test">
								 <input 	type="hidden" name="channel" value="pdf">
								  <input type="submit" id="submit" name="submit"
									class="btn btn-primary pull-right" value="submit">
							</div>
						</div>

					</div>
				</div>

			</div>
			<!-- /.col-lg-12 -->
		</div>
	</form>
	<!-- /.row -->
</body>
</html>
