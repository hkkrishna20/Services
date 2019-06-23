<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>

<link
	href="//netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//netdna.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<!-- <link
	href="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/css/bootstrap-combined.min.css"
	rel="stylesheet" id="bootstrap-css">

<script
	src="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/js/bootstrap.min.js"></script>
 -->
<!------ Include the above in your HEAD tag ---------->

<style>
body {
	background: #8999A8;
	margin: 40px;
}

.navbar-brand {
	width: 70px;
	height: 50px;
	background: url('') no-repeat center center;
	background-size: 50px;
}

.nav-tabs {
	display: inline-block;
	border-bottom: none;
	padding-top: 15px;
	font-weight: bold;
}

.nav-tabs>li>a, .nav-tabs>li>a:hover, .nav-tabs>li>a:focus, .nav-tabs>li.active>a,
	.nav-tabs>li.active>a:hover, .nav-tabs>li.active>a:focus {
	border: none;
	border-radius: 0;
}

.nav-list {
	border-bottom: 1px solid #eee;
}

.nav-list>li {
	padding: 20px 15px 15px;
	border-left: 1px solid #eee;
}

.nav-list>li:last-child {
	border-right: 1px solid #eee;
}

.nav-list>li>a:hover {
	text-decoration: none;
}

.nav-list>li>a>span {
	display: block;
	font-weight: bold;
	text-transform: uppercase;
}

.mega-dropdown {
	position: static !important;
}

.mega-dropdown-menu {
	padding: 20px 15px 15px;
	text-align: center;
	width: 100%;
}
/*CROUSELE START///////////////////////////*/
.carousel-caption {
	position: absolute;
	right: 15%;
	bottom: 35px;
	width: 60%;
	left: 15%;
	z-index: 10;
	padding-top: 20px;
	padding-bottom: 20px;
	color: #fff;
	text-align: left;
	text-shadow: 0 1px 2px rgba(0, 0, 0, .6);
}

@media screen and (min-width:768px) {
	.carousel-caption h3 {
		font-size: 52px;
	}
	.carousel-caption p {
		font-size: 32px;
	}
	.carousel-caption {
		padding-bottom: 160px;
	}
}

section {
	padding: 60px 0;
}

section .section-title {
	text-align: center;
	color: #007b5e;
	margin-bottom: 50px;
	text-transform: uppercase;
}

#footer {
	background: #007b5e !important;
}

#footer h5 {
	padding-left: 10px;
	border-left: 3px solid #eeeeee;
	padding-bottom: 6px;
	margin-bottom: 20px;
	color: #ffffff;
}

#footer a {
	color: #ffffff;
	text-decoration: none !important;
	background-color: transparent;
	-webkit-text-decoration-skip: objects;
}

#footer ul.social li {
	padding: 3px 0;
}

#footer ul.social li a i {
	margin-right: 5px;
	font-size: 25px;
	-webkit-transition: .5s all ease;
	-moz-transition: .5s all ease;
	transition: .5s all ease;
}

#footer ul.social li:hover a i {
	font-size: 30px;
	margin-top: -10px;
}

#footer ul.social li a, #footer ul.quick-links li a {
	color: #ffffff;
}

#footer ul.social li a:hover {
	color: #eeeeee;
}

#footer ul.quick-links li {
	padding: 3px 0;
	-webkit-transition: .5s all ease;
	-moz-transition: .5s all ease;
	transition: .5s all ease;
}

#footer ul.quick-links li:hover {
	padding: 3px 0;
	margin-left: 5px;
	font-weight: 700;
}

#footer ul.quick-links li a i {
	margin-right: 5px;
}

#footer ul.quick-links li:hover a i {
	font-weight: 700;
}

@media ( max-width :767px) {
	#footer h5 {
		padding-left: 0;
		border-left: transparent;
		padding-bottom: 0px;
		margin-bottom: 10px;
	}
}
</style>
<script>
	$(document).ready(function() {
		$(".dropdown").hover(function() {
			$('.dropdown-menu', this).stop(true, true).slideDown("slow");
			$(this).toggleClass('open');
		}, function() {
			$('.dropdown-menu', this).stop(true, true).slideUp("slow");
			$(this).toggleClass('open');
		});
	});
</script>
</head>
<body>

	<nav class="navbar navbar-default" role="navigation">
	<div class="container-fluid">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-megadropdown-tabs">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#"></a>
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse" id="bs-megadropdown-tabs">
			<ul class="nav navbar-nav">
				<li><a class="brand" href="integrationLandingPage"> <!-- UNCOMMENT THE CSS VALUES TO TEST OTHER DIMENTIONS -->
						<!-- <img src="http://placehold.it/150x80&text=Logo" alt=""> --> <img
						src="images/2000px-CGI_logo.svg.png"
						width="80" height="40" alt="CGI Inc.">
				</a></li>

				<li class="dropdown mega-dropdown active"><a href="#"
					class="dropdown-toggle" data-toggle="dropdown">Services <span
						class="caret"></span></a>
					<div class="dropdown-menu mega-dropdown-menu">
						<div class="container-fluid">
							<!-- Tab panes -->
							<div class="tab-content">
								<div class="tab-pane active" id="auxServices">
									<ul class="nav-list list-inline">
										<li><a href=""><img
												src="images/Home-Button.png"
												width="120" height="80"><span>Home</span></a></li>
										<li><a href="integrationRegister"><img
												src="images/reg.png"
												width="120" height="80"><span>Register</span></a></li>
										<li><a href="integrationLogin"><img
												src="images/login.jpg"
												width="120" height="80"><span>Login</span></a></li>
										<li><a href="integrationFetch"><img
												src="images/docFetch.png"
												width="120" height="80"><span>Document Fetch</span></a></li>
										<li><a href="integrationMerge"><img
												src="images/pdf-split-and-merge.png"
												width="120" height="80"><span>Document Merge</span></a></li>
										<li><a href="integrationSplitter"><img
												src="images/feature-split-pdf.jpg"
												width="120" height="80"><span>Document Split</span></a></li>
									</ul>
								</div>
								<div class="tab-pane" id="integrationServices">
									<ul class="nav-list list-inline">
										<li><a href="integrationEndToEnd"><img
												src="images/etl-bg.png"
												width="120" height="80"><span>End To End</span></a></li>
										<li><a href="integrationRoute"><img
												src="images/routing-basics-icon@2x-1.png"
												width="120" height="80"><span>Route Service</span></a></li>
										<li><a href="integrationFormLogic"><img
												src="images/196589-636190609910221326-16x9.jpg"
												width="120" height="80"><span>Feed Logic</span></a></li>
										<li><a href="integrationFormGen"><img
												src="images/eform-e1404258300362-1024x895.jpg"
												width="120" height="80"><span>Generate Document</span></a></li>
										<li><a href="integrationSLInterface"><img
												src="images/slLayerImage.jpg"
												width="120" height="80"><span>Service Interface
													Layer</span></a></li>
									</ul>
								</div>
							</div>
						</div>
						<!-- Nav tabs -->
						<ul class="nav nav-tabs" role="tablist">
							<li><a href="#integrationServices" role="tab"
								data-toggle="tab">Integration Services</a></li>
							<li class="active"><a href="#auxServices" role="tab"
								data-toggle="tab">Auxillary Services</a></li>
						</ul>
					</div></li>
				<li><a href="logout">Logout</a></li>
			</ul>
			<form class="navbar-form navbar-left" role="search">
				<div class="form-group">
					<input type="text" class="form-control" placeholder="Search">
				</div>
				<button type="submit" class="btn btn-default">Submit</button>
			</form>

		</div>
		<!-- /.navbar-collapse -->
	</div>
	</nav>
	<!--=================CAROUSELE START====================-->
<div>
		<h3></h3>
		<p></p>
		<!-- 					<button class="btn btn-danger"></button> -->
	</div>
	<div>
		<h3></h3>
		<p></p>
		<!-- 					<button class="btn btn-danger"></button> -->
	</div>
<div>
		<h3></h3>
		<p></p>
		<!-- 					<button class="btn btn-danger"></button> -->
	</div>
	<div>
		<h3></h3>
		<p></p>
		<!-- 					<button class="btn btn-danger"></button> -->
	</div>

	<div id="myCarousel" class="carousel slide" data-ride="carousel">
		<!-- Indicators -->
		<ol class="carousel-indicators">
			<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
			<li data-target="#myCarousel" data-slide-to="1"></li>
			<li data-target="#myCarousel" data-slide-to="2"></li>
			<li data-target="#myCarousel" data-slide-to="3"></li>
			<li data-target="#myCarousel" data-slide-to="4"></li>
			<li data-target="#myCarousel" data-slide-to="5"></li>
			<li data-target="#myCarousel" data-slide-to="6"></li>
			<li data-target="#myCarousel" data-slide-to="7"></li>
			<li data-target="#myCarousel" data-slide-to="8"></li>
			<li data-target="#myCarousel" data-slide-to="9"></li>
			<li data-target="#myCarousel" data-slide-to="10"></li>


		</ol>

		<!-- Wrapper for slides -->
		<div class="carousel-inner" role="listbox">
			<div class="item active" align="center">
				<img src="images/theme-transformation-numerique.png"width:80%; height:80%;>
				<div class="carousel-caption hidden-xs">
					<h3></h3>
					<p></p>
				</div>
			</div>

			<div class="item" align="center">
				<img src="images/img_98201_ibm_bg.jpg"width:80%; height:60%;>
				<div class="carousel-caption hidden-xs">
					<h3></h3>
					<p></p>
					<!-- 					<button class="btn btn-danger"></button> -->
				</div>
			</div>
			<div class="item" align="center">
				<img src="images/Manufacturing.gif"width:80%; height: 80%;>
				<div class="carousel-caption hidden-xs">
					<h3></h3>
					<p></p>
					<!-- 					<button class="btn btn-danger"></button> -->
				</div>
			</div>
			<div class="item" align="center">
				<img src="images/digital-transformation-600.png"width:80%; height:70%;>
				<div class="carousel-caption hidden-xs">
					<h3></h3>
					<p></p>
					<!-- 					<button class="btn btn-danger"></button> -->
				</div>
			</div>
			<div class="item" align="center">
				<img src="images/images.jpg"width:100%; height:110%;>
				<div class="carousel-caption hidden-xs">
					<h3></h3>
					<p></p>
					<!-- 					<button class="btn btn-danger"></button> -->
				</div>
			</div>
			<div class="item" align="center">
				<img src="images/Integration.jpg"width:90%; height:90%;>
				<div class="carousel-caption hidden-xs">
					<h3></h3>
					<p></p>
					<!-- 					<button class="btn btn-danger"></button> -->
				</div>
			</div>
			<div class="item" align="center">
				<img src="images/Content-Management.png"width:80%; height:80%;>
				<div class="carousel-caption hidden-xs">
					<h3></h3>
					<p></p>
					<!-- 					<button class="btn btn-danger"></button> -->
				</div>
			</div>
			<div class="item" align="center">
				<img src="images/Content-Management-System.jpg"width:60%; height:60%;>
				<div class="carousel-caption hidden-xs">
					<h3></h3>
					<p></p>
					<!-- 					<button class="btn btn-danger"></button> -->
				</div>
			</div>
			<div class="item" align="center">
				<img src="images/cms.png"width:70%; height:70%;>
				<div class="carousel-caption hidden-xs">
					<h3></h3>
					<p></p>
					<!-- 					<button class="btn btn-danger"></button> -->
				</div>
			</div>
			<div class="item" align="center">
				<img src="images/processCMS.png"width:80%; height:80%;>
				<div class="carousel-caption hidden-xs">
					<h3></h3>
					<p></p>
					<!-- 					<button class="btn btn-danger"></button> -->
				</div>
			</div>
		</div>

		<!-- Left and right controls -->
		<a class="left carousel-control" href="#myCarousel" role="button"
			data-slide="prev"> <span class="glyphicon glyphicon-chevron-left"
			aria-hidden="true"></span> <span class="sr-only">Previous</span>
		</a> <a class="right carousel-control" href="#myCarousel" role="button"
			data-slide="next"> <span
			class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
			<span class="sr-only">Next</span>
		</a>
	</div>
	<div>
		<h3></h3>
		<p></p>
		<!-- 					<button class="btn btn-danger"></button> -->
	</div>
	<div>
		<h3></h3>
		<p></p>
		<!-- 					<button class="btn btn-danger"></button> -->
	</div>

<div>
		<h3></h3>
		<p></p>
		<!-- 					<button class="btn btn-danger"></button> -->
	</div>
	<div>
		<h3></h3>
		<p></p>
		<!-- 					<button class="btn btn-danger"></button> -->
	</div>
<div>
		<h3></h3>
		<p></p>
		<!-- 					<button class="btn btn-danger"></button> -->
	</div>
	<div>
		<h3></h3>
		<p></p>
		<!-- 					<button class="btn btn-danger"></button> -->
	</div>

	<!-- Footer -->
	<section id="footer">
	<div class="container">
		<div class="row text-center text-xs-center text-sm-left text-md-left">
			<div class="col-xs-12 col-sm-4 col-md-4">
				<h5>Quick links</h5>
				<ul class="list-unstyled quick-links">
					<li><a href="javascript:void();"><i
							class="fa fa-angle-double-right"></i>Home</a></li>
					<li><a href="javascript:void();"><i
							class="fa fa-angle-double-right"></i>About</a></li>
					<li><a href="javascript:void();"><i
							class="fa fa-angle-double-right"></i>FAQ</a></li>
					<li><a href="javascript:void();"><i
							class="fa fa-angle-double-right"></i>Get Started</a></li>
					<li><a href="javascript:void();"><i
							class="fa fa-angle-double-right"></i>Videos</a></li>
				</ul>
			</div>
			<div class="col-xs-12 col-sm-4 col-md-4">
				<h5>Quick links</h5>
				<ul class="list-unstyled quick-links">
					<li><a href="javascript:void();"><i
							class="fa fa-angle-double-right"></i>Home</a></li>
					<li><a href="javascript:void();"><i
							class="fa fa-angle-double-right"></i>About</a></li>
					<li><a href="javascript:void();"><i
							class="fa fa-angle-double-right"></i>FAQ</a></li>
					<li><a href="javascript:void();"><i
							class="fa fa-angle-double-right"></i>Get Started</a></li>
					<li><a href="javascript:void();"><i
							class="fa fa-angle-double-right"></i>Videos</a></li>
				</ul>
			</div>
			<div class="col-xs-12 col-sm-4 col-md-4">
				<h5>Quick links</h5>
				<ul class="list-unstyled quick-links">
					<li><a href="javascript:void();"><i
							class="fa fa-angle-double-right"></i>Home</a></li>
					<li><a href="javascript:void();"><i
							class="fa fa-angle-double-right"></i>About</a></li>
					<li><a href="javascript:void();"><i
							class="fa fa-angle-double-right"></i>FAQ</a></li>
					<li><a href="javascript:void();"><i
							class="fa fa-angle-double-right"></i>Get Started</a></li>
					<li><a href="https://wwwe.sunlimetech.com"
						title="Design and developed by"><i
							class="fa fa-angle-double-right"></i>Imprint</a></li>
				</ul>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-12 col-sm-12 col-md-12 mt-2 mt-sm-5">
				<ul class="list-unstyled list-inline social text-center">
					<li class="list-inline-item"><a href="javascript:void();"><i
							class="fa fa-facebook"></i></a></li>
					<li class="list-inline-item"><a href="javascript:void();"><i
							class="fa fa-twitter"></i></a></li>
					<li class="list-inline-item"><a href="javascript:void();"><i
							class="fa fa-instagram"></i></a></li>
					<li class="list-inline-item"><a href="javascript:void();"><i
							class="fa fa-google-plus"></i></a></li>
					<li class="list-inline-item"><a href="javascript:void();"
						target="_blank"><i class="fa fa-envelope"></i></a></li>
				</ul>
			</div>
			</hr>
		</div>
	</div>
	</section>
	<!-- ./Footer -->
</body>
</html>