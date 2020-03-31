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
		<c:if test="${addusersuccess}">
			<h5 class="alert alert-success col-md-4">User created
				successfully</h5>
			<div class="col-md-6 alert">
				<!-- <div class="alert alert-success lead"> -->
				${addsuccess} <span class="floatRight"> <a
					href="<c:url value='/userlist' />"
					class="btn btn-sm btn-primary btn-space">Go to UsersList</a> <a
					href="<c:url value='/userlist2' />"
					class="btn btn-sm btn-primary btn-space">Go to UsersList2</a><a
					href="<c:url value='/jdbc/userlist3' />"
					class="btn btn-sm btn-primary btn-space">Go to UsersList3</a> <a
					href="<c:url value='/userlist4' />"
					class="btn btn-sm btn-primary btn-space">Go to UsersList4</a>
				</span>
			</div>
		</c:if>
		<c:if test="${editusersuccess}">
			<h5 class="alert alert-success col-md-4">User updated
				successfully</h5>
			<div class="col-md-6 alert">
				<!-- <div class="alert alert-success lead"> -->
				${editsuccess} <span class="floatRight"> <a
					href="<c:url value='/userlist' />"
					class="btn btn-sm btn-primary btn-space">Go to UsersList</a> <a
					href="<c:url value='/userlist2' />"
					class="btn btn-sm btn-primary btn-space">Go to UsersList2</a> <a
					href="<c:url value='/jdbc/userlist3' />"
					class="btn btn-sm btn-primary btn-space">Go to UsersList3</a> <a
					href="<c:url value='/userlist4' />"
					class="btn btn-sm btn-primary btn-space">Go to UsersList4</a>
				</span>
			</div>
		</c:if>

	</div>
</body>
<%@ include file="../fragments/footer.jspf"%>
</html>