<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<style>
body {
	padding-top: 90px;
	background-image:
		url("http://backgroundcheckall.com/wp-content/uploads/2017/12/bootstrap-background-9.jpg");
}

.panel-login {
	border-color: #ccc;
	-webkit-box-shadow: 0px 2px 3px 0px rgba(0, 0, 0, 0.2);
	-moz-box-shadow: 0px 2px 3px 0px rgba(0, 0, 0, 0.2);
	box-shadow: 0px 2px 3px 0px rgba(0, 0, 0, 0.2);
}

.panel-login>.panel-heading {
	color: #00415d;
	background-color: #fff;
	border-color: #fff;
	text-align: center;
}

.panel-login>.panel-heading a {
	text-decoration: none;
	color: #666;
	font-weight: bold;
	font-size: 15px;
	-webkit-transition: all 0.1s linear;
	-moz-transition: all 0.1s linear;
	transition: all 0.1s linear;
}

.panel-login>.panel-heading a.active {
	color: #029f5b;
	font-size: 18px;
}

.panel-login>.panel-heading hr {
	margin-top: 10px;
	margin-bottom: 0px;
	clear: both;
	border: 0;
	height: 1px;
	background-image: -webkit-linear-gradient(left, rgba(0, 0, 0, 0),
		rgba(0, 0, 0, 0.15), rgba(0, 0, 0, 0));
	background-image: -moz-linear-gradient(left, rgba(0, 0, 0, 0),
		rgba(0, 0, 0, 0.15), rgba(0, 0, 0, 0));
	background-image: -ms-linear-gradient(left, rgba(0, 0, 0, 0),
		rgba(0, 0, 0, 0.15), rgba(0, 0, 0, 0));
	background-image: -o-linear-gradient(left, rgba(0, 0, 0, 0),
		rgba(0, 0, 0, 0.15), rgba(0, 0, 0, 0));
}

.panel-login input[type="text"], .panel-login input[type="email"],
	.panel-login input[type="password"] {
	height: 45px;
	border: 1px solid #ddd;
	font-size: 16px;
	-webkit-transition: all 0.1s linear;
	-moz-transition: all 0.1s linear;
	transition: all 0.1s linear;
}

.panel-login input:hover, .panel-login input:focus {
	outline: none;
	-webkit-box-shadow: none;
	-moz-box-shadow: none;
	box-shadow: none;
	border-color: #ccc;
}

.btn-login {
	background-color: #59B2E0;
	outline: none;
	color: #fff;
	font-size: 14px;
	height: auto;
	font-weight: normal;
	padding: 14px 0;
	text-transform: uppercase;
	border-color: #59B2E6;
}

.btn-login:hover, .btn-login:focus {
	color: #fff;
	background-color: #53A3CD;
	border-color: #53A3CD;
}

.forgot-password {
	text-decoration: underline;
	color: #888;
}

.forgot-password:hover, .forgot-password:focus {
	text-decoration: underline;
	color: #666;
}

.btn-register {
	background-color: #1CB94E;
	outline: none;
	color: #fff;
	font-size: 14px;
	height: auto;
	font-weight: normal;
	padding: 14px 0;
	text-transform: uppercase;
	border-color: #1CB94A;
}

.btn-register:hover, .btn-register:focus {
	color: #fff;
	background-color: #1CA347;
	border-color: #1CA347;
}
</style>
<script>
	$(function() {
		$('#login-form-link').click(function(e) {
			$("#login-form").delay(100).fadeIn(100);
			$("#register-form").fadeOut(100);
			$('#register-form-link').removeClass('active');
			$(this).addClass('active');
			e.preventDefault();
		});
		$('#register-form-link').click(function(e) {
			$("#register-form").delay(100).fadeIn(100);
			$("#login-form").fadeOut(100);
			$('#login-form-link').removeClass('active');
			$(this).addClass('active');
			e.preventDefault();
		});

	});
	$(document).ready(function() {
		$("#login-submit").click(function() {
			$("#login-form").submit(); // Form submission.
		});

		
		
		
		$("#register-submit").click(function() {
			var password = $("#registerPassword").val();
			var confirmPassword = $("#registerConfirm").val();
			if (password == confirmPassword) {
				$("#register-form").submit();
				return true;
			} else {

				return false;
			}
			// Form submission.
		});

	});

	/* $(document).ready(function() {
	 // Submit form with id function.
	 $("#btn_id").click(function() {
	 var name = $("#name").val();
	 var email = $("#email").val();
	 if (validation()) // Calling validation function.
	 {
	 $("#form_id").submit(); // Form submission.
	 alert(" Name : " + name + " n Email : " + email + " n Form id : " + $("#form_id").attr('id') + "nn Form Submitted Successfully......");
	 }
	 });

	 // Submit form with name function.
	 $("#btn_name").click(function() {
	 var name = $("#name").val();
	 var email = $("#email").val();
	 if (validation()) // Calling validation function.
	 {
	 $("form[name='form_name']").submit(); // Form Submission
	 alert(" Name : " + name + " n Email : " + email + " n Form name : " + $("form[name='form_name']").attr('name') + "nn Form Submitted Successfully......");
	 }
	 });

	 // Submit form with class function.
	 $("#btn_class").click(function() {
	 var name = $("#name").val();
	 var email = $("#email").val();
	 if (validation()) // Calling validation function.
	 {
	 $(".form_class").submit(); // Form Submission.
	 alert(" Name : " + name + " n Email : " + email + " n Form class : " + $(".form_class").attr('class') + "nn Form is Submitted Successfully......");
	 }
	 });

	 $("#btn_tag").click(function() {
	 var name = $("#name").val();
	 var email = $("#email").val();
	 if (validation()) // Calling validation function.
	 {
	 $("form").submit(); // Form submission.
	 alert(" Name : " + name + " n Email : " + email + " n Tag : formnn Form Submitted Successfully......");
	 }
	 });

	 // Submit form with Event Handler function.
	 $("#btn_event").click(function() {
	 var name = $("#name").val();
	 var email = $("#email").val();
	 if (validation()) // Calling validation function.
	 {
	 $("#form_id").submit(function() {
	 alert('Form is submitting....');
	 //or Do Something...
	 return true;
	 });
	 $("#form_id").submit(); // Form Submission
	 alert(" Name : " + name + " n Email : " + email + "nn Form Submitted Successfully......");
	 }
	 });

	 // Name and Email validation Function.
	 function validation() {
	 var name = $("#name").val();
	 var email = $("#email").val();
	 var emailReg = /^([w-.]+@([w-]+.)+[w-]{2,4})?$/;
	 if (name === '' || email === '') {
	 alert("Please fill all fields...!!!!!!");
	 return false;
	 } else if (!(email).match(emailReg)) {
	 alert("Invalid Email...!!!!!!");
	 return false;
	 } else {
	 return true;
	 }
	 }
	 }); */

	/* $(function() {
	 $("#submit").click(function() {
	
	 var password = $("#password").val();
	 var confirmPassword = $("#confirm").val();
	 if (password != confirmPassword) {
	 alert("Passwords do not match.");
	 return false;
	 }
	 return true;
	 });
	 }); */
</script>
</head>
<body>


	<div class="container">
		<div class="row">
			<div class="col-md-6 col-md-offset-3">
				<div class="panel panel-login">
					<div class="panel-heading">
						<div class="row">
							<div class="col-xs-6">
								<a href="#" class="active" id="login-form-link">Login</a>
							</div>
							<div class="col-xs-6">
								<a href="#" id="register-form-link">Register</a>
							</div>
						</div>
						<hr>
					</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-lg-12">
								<form id="login-form" name="login-form" action="doLogin" method="post"
									role="form" style="display: block;">
									<div class="form-group">
										<input type="text" name="loginUserId" id="loginUserId"
											tabindex="1" class="form-control" placeholder="Username"
											value="">
									</div>
									<div class="form-group">
										<input type="password" name="loginPassword" id="loginPassword"
											tabindex="2" class="form-control" placeholder="Password">
									</div>
									<div class="form-group text-center">
										<input type="checkbox" tabindex="3" class="" name="remember"
											id="remember"> <label for="remember">
											Remember Me</label>
									</div>
									<div class="form-group">
										<div class="row">
											<div class="col-sm-6 col-sm-offset-3">
												<input type="submit" name="login-submit" id="login-submit"
													tabindex="4" class="form-control btn btn-login"
													value="Log In">
											</div>
										</div>
									</div>
									<div class="form-group">
										<div class="row">
											<div class="col-lg-12">
												<div class="text-center">
													<a href="/recoverPassword" tabindex="5"
														class="forgot-password">Forgot Password?</a>
												</div>
											</div>
										</div>
									</div>
								</form>
								<form id="register-form" name="register-form" action="registerProcess"
									method="post" role="form" style="display: none;">
									<div class="form-group">
										<input type="text" name="registerName" id="registerName"
											tabindex="1" class="form-control" placeholder="Name" value="">
									</div>
									<div class="form-group">
										<input type="text" name="registerUserId" id="registerUserId"
											tabindex="1" class="form-control" placeholder="Username"
											value="">
									</div>

									<div class="form-group">
										<input type="email" name="registerEmail" id="registerEmail"
											tabindex="1" class="form-control" placeholder="Email Address"
											value="">
									</div>
									<div class="form-group">
										<input type="password" name="registerPassword"
											id="registerPassword" tabindex="2" class="form-control"
											placeholder="Password">
									</div>
									<div class="form-group">
										<input type="password" name="registerConfirm"
											id="registerConfirm" tabindex="2" class="form-control"
											placeholder="Confirm Password">
									</div>
									<div class="form-group">
										<div class="row">
											<div class="col-sm-6 col-sm-offset-3">
												<input type="submit" name="register-submit"
													id="register-submit" tabindex="4"
													class="form-control btn btn-register" value="Register Now">
											</div>
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>