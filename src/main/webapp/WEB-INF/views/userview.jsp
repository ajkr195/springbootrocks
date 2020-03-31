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

		<form:form method="POST" modelAttribute="userForm" class="form-signin">
			<form:input type="hidden" path="id" id="id" />

			<hr />
			<div style="text-align: center">
				<h4>
					<b>User Details</b>
				</h4>
			</div>
			<hr />

			<div class="row">
				<label class="col-md-3 control-label text-right" for="username">User
					Name</label>
				<div class="col-md-6 pull-left">
					<spring:bind path="username">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<form:input type="text" path="username" class="form-control"
								placeholder="Username" autofocus="true" disabled="true"></form:input>
							<form:errors path="username"></form:errors>
						</div>
					</spring:bind>
				</div>
			</div>



			<div class="row">
				<label class="col-md-3 control-label text-right" for="useremail">Email-ID</label>
				<div class="col-md-6 pull-left">
					<spring:bind path="useremail">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<form:input type="text" path="useremail" class="form-control"
								placeholder="Email-id" autofocus="true" disabled="true"></form:input>
							<form:errors path="useremail"></form:errors>
						</div>
					</spring:bind>
				</div>
			</div>

			<div class="row">
				<label class="col-md-3 control-label text-right" for="userfirstname">First
					Name</label>
				<div class="col-md-6 pull-left">
					<spring:bind path="userfirstname">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<form:input type="text" path="userfirstname" class="form-control"
								placeholder="First Name" autofocus="true" disabled="true"></form:input>
							<form:errors path="userfirstname"></form:errors>
						</div>
					</spring:bind>
				</div>
			</div>

			<div class="row">
				<label class="col-md-3 control-label text-right" for="userlastname">Last
					Name</label>
				<div class="col-md-6 pull-left">
					<spring:bind path="userlastname">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<form:input type="text" path="userlastname" class="form-control"
								placeholder="Last Name" autofocus="true" disabled="true"></form:input>
							<form:errors path="userlastname"></form:errors>
						</div>
					</spring:bind>
				</div>
			</div>

			<div class="row">
				<label class="col-md-3 control-label text-right" for="useraddress">Address</label>
				<div class="col-md-6 pull-left">
					<spring:bind path="useraddress">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<form:input type="text" path="useraddress" class="form-control"
								placeholder="Your Address" autofocus="true" disabled="true"></form:input>
							<form:errors path="useraddress"></form:errors>
						</div>
					</spring:bind>
				</div>
			</div>

			<div class="row">
				<label class="col-md-3 control-label text-right" for="roles">Roles
					Available</label>
				<div class="col-md-6 pull-left">
					<form:select path="roles" items="${roles}" multiple="true"
						itemValue="id" itemLabel="name" disabled="true"
						class="form-control input-sm input-sm" />
					<div class="has-error">
						<form:errors path="roles" class="help-inline" />
					</div>
				</div>
			</div>
			<br>
			<div class="col-md-9">
				<a href="<c:url value='/userlist' />"
					class="btn btn-danger btn-sm  float-right"><i
					class="fas fa-times"></i> Done</a>
			</div>

		</form:form>

	</div>
</body>
<%@ include file="../fragments/footer.jspf"%>
</html>