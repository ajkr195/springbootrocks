<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<head>
<title>SpringBootRocks</title>
<%@ include file="../fragments/header.jspf"%>
<link rel="stylesheet"
	href="https://code.highcharts.com/css/highcharts.css" />
<%@ include file="../fragments/navbar.jspf"%>
</head>
<body>
	<!-- 		<div class="container-fluid"> -->
	<div class="container">
		<!-- 		<div class="card-header bg-info text-white p-0"> -->
		<hr />
		<div class="d-flex align-items-center justify-content-center">
			<div style="text-align: center">
				<h2>
					<b>Users by Date Created (Created Since - in Years)</b>
				</h2>
				<h6>
					<b>Spring Data With Zero Config Datatable</b>
				</h6>
				<div style="text-align: center">
					<button type="button" id="refreshButton"
						class="btn btn-sm btn-secondary btn-rounded" title="Refresh Page">
						<i class="fas fa-sync-alt"></i>
					</button>
					<sec:authorize access="hasAuthority('ADMIN')">
						<a href="<c:url value='/registration' />"
							class="btn btn-sm btn-success btn-rounded" title="Add New User"><span
							class="fa fa-plus"></span></a>
					</sec:authorize>

					<sec:authorize access="hasAuthority('ADMIN')">
						<a href="<c:url value='/alluserreportPDF'  />"
							class="btn btn-sm btn-danger btn-rounded"
							title="Export all to PDF" target="_blank"><i
							class="fas fa-file-pdf"></i></a>
					</sec:authorize>
					<sec:authorize access="hasAuthority('ADMIN')">
						<a href="<c:url value='/alluserreportCSV'  />"
							class="btn btn-sm btn-success btn-rounded"
							title="Export all to CSV"><i class="fas fa-file-code"></i></a>
					</sec:authorize>
					<sec:authorize access="hasAuthority('ADMIN')">
						<a href="<c:url value='/alluserreportExcel'  />"
							class="btn btn-sm btn-primary btn-rounded"
							title="Export all to Excel"><i class="fas fa-file-excel"></i></a>
					</sec:authorize>
					<sec:authorize access="hasAuthority('ADMIN')">
						<a href="<c:url value='/alluserreportJSON'  />"
							class="btn btn-sm btn-secondary btn-rounded"
							title="Export all to JSON" target="_blank"> { }</a>
					</sec:authorize>
					<sec:authorize access="hasAuthority('ADMIN')">
						<a href="<c:url value='/jasper-HTMLEXPORT-report'  />"
							class="btn btn-sm btn-warning btn-rounded"
							title="Export all to JASPER" target="_blank"><i
							class="fa fa-file" aria-hidden="true"></i></a>
					</sec:authorize>
				</div>
			</div>
		</div>
		<hr />

		<!-- 		</div> -->
		<table id="tableitems"
			class="table table-condensed table-hover table-responsive-sm  table_morecondensed width=80%">
			<thead class="thead-secondary">
				<!-- 			<thead class="thead-dark"> -->
				<tr>
					<th>UserName</th>
					<th>No. of Years</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach items="${users}" var="user">
					<tr>
						<td>${user.key}</td>
						<td>${user.value}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<hr />
	</div>

	<!-- #Modal for removing users -->
	<div class="modal fade" id="removeModalCenter" tabindex="-1"
		role="dialog" aria-labelledby="removeModalCenterTitle"
		aria-hidden="true" data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog modal-dialog-top" role="document">
			<!-- modal-dialog-centered -->
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="removeModalCenterTitle">User
						Delete Warning !</h5>
					<button type="button"
						class="btn btn-sm btn-default close-icon pull-right"
						data-dismiss="modal"></button>
				</div>
				<div class="modal-body">
					<p class="alert">You sure about this? This operation can not be
						rolled back.!</p>
				</div>
				<div class="modal-footer">
					<a href="" class="btn btn-sm btn-secondary" id="delRef"><i
						class="fas fa-check"></i> Yes</a>
					<button type="button" class="btn btn-sm btn-danger"
						data-dismiss="modal">
						<i class="fas fa-times"> No</i>
					</button>

				</div>
			</div>
		</div>
	</div>

	<!-- #Modal for viewing user details -->
	<div class="modal fade" id="viewUserDetailsModal" tabindex="-1"
		role="dialog" aria-labelledby="viewUserDetailsModalLabel"
		aria-hidden="true" data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog modal-dialog-top" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="col-12 modal-title text-center"
						id="viewUserDetailsModalLabel"></h5>
					<button type="button"
						class="btn btn-sm btn-default close-icon pull-right"
						data-dismiss="modal"></button>
					<br> <br>
				</div>
				<div class="modal-body">
					<form>
						<div class="row">
							<label for="userid"
								class="font-weight-bold col-sm-4 col-form-label text-right">ID
								:</label>
							<p class="p-label col-sm-8 pull-left" id="userid"></p>
						</div>
						<div class="row">
							<label for="username"
								class="font-weight-bold col-sm-4 col-form-label text-right">Username
								:</label>
							<p class="p-label col-sm-8 pull-left" id="username"></p>
						</div>
						<div class="row">
							<label for="useremail"
								class="font-weight-bold col-sm-4 col-form-label text-right">Email-ID
								:</label>
							<p class="p-label col-sm-8 pull-left" id="useremail"></p>
						</div>
						<div class="row">
							<label for="userfirstname"
								class="font-weight-bold col-sm-4 col-form-label text-right">First
								Name :</label>
							<p class="p-label col-sm-8 pull-left" id="userfirstname"></p>
						</div>
						<div class="row">
							<label for="userlastname"
								class="font-weight-bold col-sm-4 col-form-label text-right">Last
								Name :</label>
							<p class="p-label col-sm-8 pull-left" id="userlastname"></p>
						</div>
						<div class="row">
							<label for="useraddress"
								class="font-weight-bold col-sm-4 col-form-label text-right">Address
								:</label>
							<p class="p-label col-sm-8 pull-left" id="useraddress"></p>
						</div>
						<div class="row">
							<label for="userroles"
								class="font-weight-bold col-sm-4 col-form-label text-right">Roles
								:</label>
							<p class="p-label col-sm-8 pull-left" id="userroles"></p>
						</div>
						<!-- 						<select class="form-control" id="roleslist"></select> -->
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-sm btn-secondary"
						data-dismiss="modal">OK</button>
				</div>
			</div>
		</div>
	</div>

	<!--  <div align="center"> -->
	<!--         <h2>Users Registered Since</h2> -->
	<!--     </div> -->
	<figure class="highcharts-figure">
		<div id="container-bar"></div>
	</figure>






</body>
<%@ include file="../fragments/footer.jspf"%>
<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/modules/exporting.js"></script>
<script src="https://code.highcharts.com/modules/export-data.js"></script>
<script src="https://code.highcharts.com/modules/accessibility.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$.ajax({
			url : "/api/get-data",
			success : function(result) {
				var yearDtls = [];
				var countDtls = [];
				console.log(result);
				Object.keys(result).forEach(function(key) {
					yearDtls.push(key);
					countDtls.push(result[key]);
				});
				drawChart(yearDtls, countDtls);
			}
		});
	});

	function drawChart(year, viewCounts) {
		Highcharts.chart('container-bar', {
			chart : {
				type : 'column',
				styledMode : true
			},
			title : {
				text : 'Users Registered Since'
			},
			xAxis : [ {
				title : {
					text : 'User Names'
				},
				categories : year
			} ],
			yAxis : [ {
				className : 'highcharts-color-0',
				title : {
					text : 'Years Since Registered'
				}
			} ],
			series : [ {
				data : viewCounts
			} ]
		});
	}
</script>
<script type="text/javascript">
	$(document).ready(
			function() {

				$('#tableitems').DataTable(
						{
							"lengthMenu" : [ [ 3, 5, 7, 10, 25, 50, 100, -1 ],
									[ 3, 5, 7, 10, 25, 50, 100, "All" ] ]
						});

				$('#viewUserDetailsModal').on(
						'show.bs.modal',
						function(event) {
							var button = $(event.relatedTarget) // Button that triggered the modal
							var userid = button.data('userid')
							var username = button.data('username')
							var useremail = button.data('useremail')
							var userfirstname = button.data('userfirstname')
							var userlastname = button.data('userlastname')
							var useraddress = button.data('useraddress')
							var userroles = button.data('userroles')
							// If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
							// Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
							var modal = $(this)
							modal.find('.modal-title').text(
									'Details of - ' + username)
							//modal.find('.modal-body input').val(username)
							modal.find('.modal-body #userid').html(userid)
							modal.find('.modal-body #username').html(username)
							modal.find('.modal-body #useremail')
									.html(useremail)
							modal.find('.modal-body #userfirstname').html(
									userfirstname)
							modal.find('.modal-body #userlastname').html(
									userlastname)
							modal.find('.modal-body #useraddress').html(
									useraddress)
							modal.find('.modal-body #userroles').html(
									userroles.replace("AppRole [id=1, name=",
											"").replace("]", "").replace("[",
											"").replace("AppRole [id=2, name=",
											"").replace("AppRole [id=3, name=",
											"").replace("]", "").replace("]]",
											""))

							// for (var i = 0; i < userroles.length; i++) {
							//$('#roleslist').append(
							// '<option value="' + userroles[i] + '">'
							// 	+ userroles[i]
							// + '</option>');
							//}

						})

				$('#refreshButton').click(function() {
					location.reload();
				});

				$('#tableitems').on('click', '.delBtn', function(event) {
					event.preventDefault();
					var href = $(this).attr('href');
					$('#removeModalCenter #delRef').attr('href', href);
					$('#removeModalCenter').modal('show');
				});
			});
</script>
</html>