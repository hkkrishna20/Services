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

<title>Content Insertion</title>

<script src="webjars/jquery/1.11.1/jquery.min.js"></script>
<script src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<!-- <script src="js/1.js"></script> -->

<link href="webjars/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<!------ Include the above in your HEAD tag ---------->
<link rel="stylesheet"
	href="webjars/font-awesome/4.7.0/css/font-awesome.min.css"></link>
<link href="css/1.css" rel="stylesheet" type="text/css"></link>
<script>
	var ct = 1;
	function new_link() {
		ct++;
		var div1 = document.createElement('div');
		div1.id = ct;
		// link to delete extended form elements
		var delLink = '<div style="text-align:right;margin-right:65px"><a href="javascript:delIt('
				+ ct + ')">Del</a></div>';
		div1.innerHTML = document.getElementById('newlinktpl').innerHTML
				+ delLink;
		document.getElementById('newlink').appendChild(div1);
	}
	// function to delete the newly added set of elements
	function delIt(eleId) {
		d = document;
		var ele = d.getElementById(eleId);
		var parentEle = d.getElementById('newlink');
		parentEle.removeChild(ele);
	}
</script>

</head>

<body>

	<section class="section-white">
		<div class="container">

			<div id="carousel-example-generic" class="carousel slide"
				data-ride="carousel">
				<!-- Indicators -->
				<ol class="carousel-indicators">
					<li data-target="#carousel-example-generic" data-slide-to="0"
						class="active"></li>
					<li data-target="#carousel-example-generic" data-slide-to="1"></li>
					<li data-target="#carousel-example-generic" data-slide-to="2"></li>
				</ol>

				<!-- Wrapper for slides -->
				<div class="carousel-inner">
					<div class="item active" align="center">
						<img src="images/eform-e1404258300362-1024x895.jpg" align="middle"
							height="40" width="40" alt="EndtoEnd">

					</div>
					<div class="item" align="center">
						<img src="images/eform-e1404258300362-1024x895.jpg" align="middle"
							height="40" width="40" alt="EndtoEnd">

					</div>
					<div class="item" align="center">
						<img src="images/eform-e1404258300362-1024x895.jpg" align="middle"
							height="40" width="40" alt="EndtoEnd">

					</div>
				</div>

				<!-- Controls -->
				<a class="left carousel-control" href="#carousel-example-generic"
					data-slide="prev"> <span
					class="glyphicon glyphicon-chevron-left"></span>
				</a> <a class="right carousel-control" href="#carousel-example-generic"
					data-slide="next"> <span
					class="glyphicon glyphicon-chevron-right"></span>
				</a>
			</div>

		</div>
	</section>

	<div class="row">
		<div class="col-md-4 col-md-offset-4">
			<form class="form-horizontal" role="form" action="contentInsert"
				method="post">
				<fieldset>
					<!-- Form Name -->
					<legend>Content Enrich in Database </legend>


					
					<div id="newlink">
					<div class="form-group">
						<div class="pure-control-group">
							<label class="col-sm-4 control-label" for="textinput">Form
								ID :</label>
							<div class="col-sm-8">
								<input type="text" name="formID" id="formID"
									placeholder="formID" class="form-control">
						
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="pure-control-group">
							<label class="col-sm-4 control-label" for="textinput">Form
								Content :</label>
							<div class="col-sm-8">
								
						<textarea rows="10" cols="100" name="formTitle" id="formTitle" placeholder="Describe Content here..."></textarea>
							</div>

						</div>
					</div>
					
					</div>
					<input type="hidden" name="testType" value="FormCreationService">
					<input type="hidden" name="userId" value="FormCreationService">
					<input type="hidden" name="password" value="FormCreationService">
					<input type="hidden" name="environment" value="test"> <input
						type="hidden" name="channel" value="pdf">

					<p id="addnew">
						<a href="javascript:new_link()">Add New </a>
					</p>
					<!-- Template -->
					<div id="newlinktpl" style="display: none">
					<div class="form-group">
						<div class="pure-control-group">
							<label class="col-sm-4 control-label" for="textinput">Form
								ID :</label>
							<div class="col-sm-8">
								<input type="text" name="formID" id="formID"
									placeholder="formID" class="form-control">
						
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="pure-control-group">
							<label class="col-sm-4 control-label" for="textinput">Form
								Content :</label>
							<div class="col-sm-8">
								
						<textarea rows="10" cols="100" name="formTitle" id="formTitle" placeholder="Describe Content here..."></textarea>
							</div>

						</div>
					</div>
				
					</div>
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<div class="pull-right">
									<button type="submit" class="btn btn-default">Cancel</button>
									<button type="submit" class="btn btn-primary" value="submit">submit</button>
								</div>
							</div>
						</div>
				</fieldset>
			</form>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
</body>
</html>
