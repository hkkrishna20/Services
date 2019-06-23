<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<script src="https://npmcdn.com/tether@1.2.4/dist/js/tether.min.js"></script>
<script src="webjars/jquery/1.11.1/jquery.min.js"></script>
<script src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link href="webjars/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<!------ Include the above in your HEAD tag ---------->
<script
	src="https://npmcdn.com/bootstrap@4.0.0-alpha.5/dist/js/bootstrap.min.js"></script>

<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js"></script>

<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Coming Soon</title>

<!-- Google fonts --->
<link
	href="https://fonts.googleapis.com/css?family=Quicksand:300,400,500,700"
	rel="stylesheet">

<!-- Bootstrap & font awesome Css ---->
<link rel="stylesheet"
	href="webjars/font-awesome/4.7.0/css/font-awesome.min.css"></link>
<style>
/*
 * Globals
 */
/* Links */
a, a:focus, a:hover {
	color: #fff;
}

/* Custom default button */
.btn-default {
	color: #fff;
	text-shadow: none;
	/* Prevent inheritence from `body` */
	background-color: transparent;
	border: 2px solid #fff;
	border-radius: 20px;
	padding: 0.5rem 2rem;
}

.btn-default:hover, .btn-default:focus {
	background-color: rgba(255, 255, 255, 0.3);
}

/*
 * Base structure
 */
html, body {
	height: 100%;
}

.full-screen {
	background-size: cover;
	background-position: center;
	background-repeat: no-repeat;
}

body {
	/* background: url(bg.jpg) no-repeat center center fixed; */
	background-size: cover;
	color: #fff;
	text-align: center;
	font-family: 'Quicksand', sans-serif;
	font-size: 18px !important;
}

h3 {
	display: inline-block;
	padding: 10px;
	background: #B9121B;
	border-top-left-radius: 10px;
	border-top-right-radius: 10px;
}

.cover-copy {
	color: #fff !important;
}

.btn-notify {
	border: 2px solid #fff !important;
}

/* Extra markup and styles for table-esque vertical and horizontal centering */
.site-wrapper {
	display: table;
	width: 100%;
	height: 100%;
	/* For at least Firefox */
	min-height: 100%;
	background: rgba(48, 53, 70, 0.5);
	box-shadow: inset 0 0 100px rgba(0, 0, 0, 0.5);
}

.site-wrapper-inner {
	display: table-cell;
	vertical-align: top;
}

.cover-container {
	margin-right: auto;
	margin-left: auto;
}

/* Padding for spacing */
.inner {
	padding: 30px;
}

/*
 * Header
 */
.masthead-brand {
	margin-top: 10px;
	margin-bottom: 10px;
	color: #fff !important;
}

.nav-masthead {
	text-align: center;
	display: block;
	color: #fff !important;
}

.nav-masthead .nav-link {
	display: inline-block;
	color: #fff !important;
}

@media ( min-width : 768px) {
	.masthead-brand {
		float: left;
	}
	.nav-masthead {
		float: right;
	}
}

/*
 * Cover
 */
.cover {
	padding: 0 20px;
}

.cover .btn-notify {
	padding: 10px 60px;
	font-weight: 500;
	text-transform: uppercase;
	border-radius: 40px;
}

.cover-heading {
	font-weight: 500;
	text-transform: uppercase;
	letter-spacing: 10px;
	font-size: 2rem;
	margin-bottom: 15px !important;
}

.sub-header {
	font-size: 23px;
	letter-spacing: 6px;
	color: #fff !important;
}

.cover-heading {
	color: #fff !important;
}

@media ( min-width : 768px) {
	.cover-heading {
		font-size: 3.4rem;
		letter-spacing: 15px;
	}
}

.cover-copy {
	max-width: 500px;
	margin: 0 auto 3rem;
}

/*
 * Footer
 */
.mastfoot {
	color: #999;
	/* IE8 proofing */
	color: rgba(255, 255, 255, 0.5);
}

/*
 * Subscribe modal box
 */
#subscribeModal .modal-content {
	background-color: #303546;
	color: #fff;
	text-align: left;
}

#subscribeModal .modal-header, #subscribeModal .modal-footer {
	border: 0;
}

#subscribeModal .close {
	color: #fff;
}

#subscribeModal .form-control {
	margin-top: 1rem;
	background: rgba(0, 0, 0, 0.4);
	color: #fff;
}

#subscribeModal .form-control:focus {
	border-color: #49506a;
}

/*
 * Affix and center
 */
@media ( min-width : 768px) {
	/* Pull out the header and footer */
	.masthead {
		position: fixed;
		top: 0;
	}
	.mastfoot {
		position: fixed;
		bottom: 0;
	}
	/* Start the vertical centering */
	.site-wrapper-inner {
		vertical-align: middle;
	}
	/* Handle the widths */
	.masthead, .mastfoot, .cover-container {
		width: 100%;
		/* Must be percentage or pixels for horizontal alignment */
	}
}

@media ( min-width : 992px) {
	.masthead, .mastfoot, .cover-container {
		width: 1060px;
	}
}
</style>

<script>
	$(window).on('resize', function() {
		$wHeight = $(window).height();
		$item.height($wHeight);
	});

	$('.carousel').carousel({
		interval : 6000,
		pause : "false"
	});

	var $item = $('.carousel .item');
	var $wHeight = $(window).height();
	$item.eq(0).addClass('active');
	$item.height($wHeight);
	$item.addClass('full-screen');

	$('.carousel img').each(function() {
		var $src = $(this).attr('src');
		var $color = $(this).attr('data-color');
		$(this).parent().css({
			'background-image' : 'url(' + $src + ')',
			'background-color' : $color
		});
		$(this).remove();
	});
</script>
</head>
<body id="top">
	<div class="site-wrapper">
		<div class="site-wrapper-inner">
			<div class="cover-container">
				<div class="masthead clearfix">
					<div class="inner">
						<h3 class="masthead-brand">CGI Inc.</h3>
					</div>
				</div>
				<br>
				<div id="mycarousel" class="carousel slide" data-ride="carousel">
					<!-- Indicators -->
					<ol class="carousel-indicators">
						<li data-target="#mycarousel" data-slide-to="0" class="active"></li>
						<li data-target="#mycarousel" data-slide-to="1"></li>
						<li data-target="#mycarousel" data-slide-to="2"></li>
						<li data-target="#mycarousel" data-slide-to="3"></li>
						<li data-target="#mycarousel" data-slide-to="4"></li>
					</ol>

					<!-- Wrapper for slides -->
					<div class="carousel-inner" role="listbox">
						<div class="item">
							<img src="#" data-color="lightblue" alt="First Image">
							<div class="carousel-caption"></div>
						</div>
						<div class="item">
							<img src="#" data-color="firebrick" alt="Second Image">
							<div class="carousel-caption"></div>
						</div>
						<div class="item">
							<img src="#" data-color="violet" alt="Third Image">
							<div class="carousel-caption"></div>
						</div>
						<div class="item">
							<img src="#" data-color="lightgreen" alt="Fourth Image">
							<div class="carousel-caption"></div>
						</div>
						<div class="item">
							<img src="#" data-color="tomato" alt="Fifth Image">
							<div class="carousel-caption"></div>
						</div>
					</div>

					<!-- Controls -->
					<a class="left carousel-control" href="#mycarousel" role="button"
						data-slide="prev"> <span
						class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
						<span class="sr-only">Previous</span>
					</a> <a class="right carousel-control" href="#mycarousel" role="button"
						data-slide="next"> <span
						class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
						<span class="sr-only">Next</span>
					</a>
				</div>
				<div class="inner cover">
					<h1 class="cover-heading">Integration</h1>
					<h5 class="sub-header">Click Login To see Transformation</h5>
					<br>

					<p class="lead">
						<input type="button" class="btn btn-lg btn-default btn-notify"
							data-toggle="modal" value="Login" onclick="location.href = 'xLogin';" data-target="#subscribeModal"/>
							<!-- <a href="xLogin" style="color: inherit">Login </a> -->
					</p>
				</div>
				<div class="mastfoot">
					<div class="inner"></div>
				</div>
			</div>
		</div>
</body>
</html>