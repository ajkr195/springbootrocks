<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<head>
<title>SpringBootRocks</title>
<%@ include file="../fragments/header.jspf"%>
<%@ include file="../fragments/navbar.jspf"%>
</head>
<body>
	<div class="container">
		<div class="panel-body">
			<div style="text-align: center">
				<strong style="color: red;"><h2>Access Denied !!!</h2></strong>
			</div>
			<hr />
			Dear <strong>${loggedinuser},</strong> You are not authorized for
			this operation.
			<hr />
			<div class="col-sm-12">
				<div class="alert alert-danger" role="alert"><i class="fas fa-skull-crossbones"></i>
					Looks like you are trying to perform some operation <strong>you
						are not authorized to. This incident will be reported.</strong>
				</div>
				<div class="alert alert-warning" role="alert"><i class="fas fa-exclamation-circle"></i>
					<strong>Heads up!</strong> This alert needs your attention, but
					it's not super important.
					<p></p>
				</div>
				<div class="alert alert-primary" role="alert"><i class="fas fa-lightbulb"></i>
					<strong>Please make sure you have appropriate permissions.</strong>
					Contact your supervisor if need further help in this regard.
				</div>
				<div class="alert alert-success" role="alert"><i class="fas fa-handshake"></i>
					<strong>This you can do... </strong> Change a few things up and try
					submitting again. Or Logout or go Home if you landed here by
					mistake.
				</div>
			</div>
			<hr />
			<a href="<c:url value='/logout' />"
				class="btn btn-sm btn-warning btn-space "><i
				class="fas fa-sign-out-alt"></i><strong> LOGOUT</strong></a><a
				href="<c:url value='/home' />" class="btn btn-sm btn-primary btn-space"><strong><i
					class="fas fa-home"></i> Go Home</strong></a> <br>

		</div>
	</div>
</body>
<%@ include file="../fragments/footer.jspf"%>
</html>
