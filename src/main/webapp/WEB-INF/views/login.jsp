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
	<div class="container ">
		<div class="col-md-7">
			<form:form method="POST" action="${contextPath}/login"
				class="form-signin">
				<h2>Login</h2>
				<c:if test="${param.error != null}">
					<div class="alert alert-danger">Invalid username and/or
						password.</div>
				</c:if>
				<c:if test="${param.logout != null}">
					<div class="alert alert-secondary">You have been logged out
						successfully.</div>
				</c:if>
				<div class="form-group">
					<div class="form-group ${error != null ? 'has-error' : ''}">
						<div class="form-group">
							<i class="fa fa-user" style="color: gray;"></i><input
								name="username" type="text" class="form-control form-control-sm"
								placeholder="Username" autofocus="autofocus" />
						</div>
					</div>
					<div class="form-group">
						<i class="fa fa-lock" style="color: red;"></i> <input
							name="password" type="password"
							class="form-control form-control-sm" placeholder="Password" /> <input
							type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
					</div>
					<div>
						<button class="btn btn-danger btn-block" type="submit">
							<i class="fas fa-sign-out-alt"></i> Log In
						</button>
					</div>
					<br />
					<div class="form-check">
						<input class="form-check-input" type="checkbox" name="remember-me"
							id="rememme"> <label class="form-check-label"
							for="rememme"> Remember me on this Computer </label>
					</div>
					<br />
					<div>
						Not yet registered? <a href="${contextPath}/registration">Register
							Here</a>
					</div>
				</div>
			</form:form>
		</div>
		<br />
		<%@include file="../fragments/jumbotron.jspf"%>
	</div>
</body>
<%@ include file="../fragments/footer.jspf"%>
</html>
