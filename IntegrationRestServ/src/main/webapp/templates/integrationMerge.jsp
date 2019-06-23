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

<title>Merging documents</title>

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
<style>
#newlink {
	width: 600px
}
</style>
<script>
	$(function() {
		$("#tabs").tabs();
	});
</script>

</head>

<body>
	<div class="row"></div>
	<div class="row"></div>
	<div class="row"></div>

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
						<img src="images/pdf-split-and-merge.png" align="middle"
							height="40" width="40" alt="EndtoEnd">

					</div>
					<div class="item" align="center">
						<img src="images/pdf-split-and-merge.png" align="middle"
							height="40" width="40" alt="EndtoEnd">

					</div>
					<div class="item" align="center">
						<img src="images/pdf-split-and-merge.png" align="middle"
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
	<div class="row"></div>
	<div class="row"></div>
	<div class="row"></div>

	<div class="row">
		<div class="col-md-4 col-md-offset-4">
			<form class="form-horizontal" role="form" action="slRequest"
				method="post">
				<fieldset>
					<!-- Form Name -->
					<legend>Merge Documents</legend>


					<div class="form-group">
						<div class="pure-control-group">
							<div id="newlink">
								<div>Document ID :</div>
								<div>
									<input type="text" name="mergeDocID" id="mergeDocID"
										placeholder="mergeDocID" value="" class="form-control">
								</div>
							</div>
						</div>
					</div>
					<p id="addnew">
						<a href="javascript:new_link()">Add New </a>
					</p>
					<!-- Template -->
					<div id="newlinktpl" style="display: none">

						<div class="form-group">
							<div class="pure-control-group">
								<div>Document ID :</div>
								<div>
									<input type="text" name="mergeDocID" id="mergeDocID"
										placeholder="mergeDocID" value="" class="form-control">
								</div>
							</div>
						</div>
					</div>
					<input type="hidden" name="testType" value="FormMergeService">
					<input type="hidden" name="userId" value="FormMergeService">
					<input type="hidden" name="password" value="FormMergeService">
					<input type="hidden" name="environment" value="test"> <input
						type="hidden" name="channel" value="pdf">
					<p>
						<br> <input type="submit" name="submit1" value="Submit">
						<input type="reset" name="reset1">
					</p>

				</fieldset>
			</form>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
</body>
</html>
