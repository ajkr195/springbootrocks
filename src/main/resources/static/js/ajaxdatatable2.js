$(document).ready(function() {
	var table = $('#tableitems').DataTable({   
	"pagingType": "full_numbers",
	"sAjaxSource" : "/api/listappusers",
	"sAjaxDataProp" : "",
	"aoColumns" : [
			{
				"data" : "id"
			},
			{
				"data" : "username"
			},
			{
				"data" : "useremail"
			},
			{
				"data" : "userfirstname"
			},
			{
				"data" : "userlastname"
			},
			{
				"targets" : -1,
				"data" : null,
				"defaultContent" : '<div class="btn-group">'
						+ '<a class="btn btn-sm btn-danger bg-red ajaxDeleteCall mr-2" title="Delete this User"><i class="fas fa-user-times"></i></a>'
						+ '</div>'
			}
	// { "mData": "useraddress" },
	// { "mData": "userdatecreated" },
	// { "mData": "usercreatedby" },
	// { "mData": "userdatemodified" },
	// { "mData": "usermodifiedby" }
	],
	"lengthMenu" : [
			[ 5, 7, 10, 25, 50, 100, -1 ],
			[ 5, 7, 10, 25, 50, 100,
					"All" ] ],
					"autoWidth" : false,
					"responsive" : true
});

	$('#tableitems tbody').on('click','.ajaxDeleteCall',function() {
	var data = table.row($(this).parents('tr')).data();
	var currentrow = $(this);
	$.ajax({
				type : "DELETE",
				url : "/api/delete/"+ data.id,
				success : function(resultMsg) {
					$("#resultMsgDiv").html("<p class=\"alert alert-danger\"> User with id - <b>" + data.id + "</b> is deleted successfully!"
					+ "</p>").delay(2000).fadeOut('slow');
					currentrow.closest("tr").remove();
					table.ajax.reload();
				},
				error : function(e) {
					alert("ERROR: ", e);
					console.log( "ERROR: ", e);
				}
			});

	});
});
