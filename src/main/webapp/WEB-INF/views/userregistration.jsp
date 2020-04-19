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
		<div style="text-align: center">
		<c:if test="${addnewuser}">
			<h4>
				<b>Add New User</b>
			</h4>
		</c:if>
		<c:if test="${editexistinguser}">
			<h4>
				<b>Update User</b>
			</h4>
		</c:if>
		</div>
		<form:form method="POST" modelAttribute="userForm" class="form-signin">
			<form:input type="hidden" path="id" id="id" />
			<!-- 			<div class="card-header bg-info text-white p-0"> -->

			<!-- 			</div> -->
			<br>

			<div class="row">
				<label class="col-md-3 control-label text-right" for="username">User
					Name</label>
				<div class="col-md-7 pull-left">
					<spring:bind path="username">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<c:choose>
								<c:when test="${edit}">
									<form:input type="text" path="username" class="form-control form-control-sm"
										placeholder="Username" autofocus="true" disabled="true"></form:input>
								</c:when>
								<c:otherwise>
									<form:input type="text" path="username" class="form-control form-control-sm"
										placeholder="Username" autofocus="true"></form:input>
								</c:otherwise>
							</c:choose>
							<form:errors path="username" cssClass="error"></form:errors>
						</div>
					</spring:bind>
				</div>
			</div>

			<div class="row">
				<label class="col-md-3 control-label text-right" for="password">Password</label>
				<div class="col-md-7 pull-left">
					<spring:bind path="password">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<form:input type="password" path="password" class="form-control form-control-sm"
								placeholder="Password"></form:input>
							<form:errors path="password" cssClass="error"></form:errors>
						</div>
					</spring:bind>
				</div>
			</div>

			<div class="row">
				<label class="col-md-3 control-label text-right"
					for="passwordConfirm">Confirm Password</label>
				<div class="col-md-7 pull-left">
					<spring:bind path="passwordConfirm">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<form:input type="password" path="passwordConfirm"
								class="form-control form-control-sm" placeholder="Confirm password"></form:input>
							<form:errors path="passwordConfirm" cssClass="error"></form:errors>
						</div>
					</spring:bind>
				</div>
			</div>


			<div class="row">
				<label class="col-md-3 control-label text-right" for="useremail">Email-ID</label>
				<div class="col-md-7 pull-left">
					<spring:bind path="useremail">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<form:input type="text" path="useremail" class="form-control form-control-sm"
								placeholder="Email-id" autofocus="true"></form:input>
							<form:errors path="useremail" cssClass="error"></form:errors>
						</div>
					</spring:bind>
				</div>
			</div>

			<div class="row">
				<label class="col-md-3 control-label text-right" for="userfirstname">First
					Name</label>
				<div class="col-md-7 pull-left">
					<spring:bind path="userfirstname">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<form:input type="text" path="userfirstname" class="form-control form-control-sm"
								placeholder="First Name" autofocus="true"></form:input>
							<form:errors path="userfirstname" cssClass="error"></form:errors>
						</div>
					</spring:bind>
				</div>
			</div>

			<div class="row">
				<label class="col-md-3 control-label text-right" for="userlastname">Last
					Name</label>
				<div class="col-md-7 pull-left">
					<spring:bind path="userlastname">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<form:input type="text" path="userlastname" class="form-control form-control-sm"
								placeholder="Last Name" autofocus="true"></form:input>
							<form:errors path="userlastname" cssClass="error"></form:errors>
						</div>
					</spring:bind>
				</div>
			</div>

			<div class="row">
				<label class="col-md-3 control-label text-right" for="useraddress">Address</label>
				<div class="col-md-7 pull-left">
					<spring:bind path="useraddress">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<form:input type="text" path="useraddress" class="form-control form-control-sm"
								placeholder="Your Address" autofocus="true"></form:input>
							<form:errors path="useraddress" cssClass="error"></form:errors>
						</div>
					</spring:bind>
				</div>
			</div>

			<div class="row">
				<label class="col-md-3 control-label text-right" for="roles">Roles
					Available</label>
				<div class="col-md-5 pull-left">
					<spring:bind path="roles">
						<form:select path="roles" items="${roles}" multiple="true"
							itemValue="id" itemLabel="name"
							class="form-control form-control-sm" />
						<div class="has-error">
							<form:errors path="roles" class="help-inline" cssClass="error" />
						</div>
					</spring:bind>
				</div>
			</div>
			<br>
			<div class="col-md-9">
				<button class="btn btn-sm btn-danger btn-space float-right"
					type="submit">
					<span class="fa fa-check"></span> Submit
				</button>
				<a href="<c:url value='userlist' />"
					class="btn btn-sm btn-secondary btn-space float-right"><i
					class="fas fa-times"></i> Cancel</a>
			</div>
		</form:form>
		<br>
	</div>
</body>
<%@ include file="../fragments/footer.jspf"%>
</html>