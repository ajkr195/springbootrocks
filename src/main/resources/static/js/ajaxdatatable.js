$(document).ready( function () {
	 var table = $('#tableitems').DataTable({
			"sAjaxSource": "/api/listappusers",
			"sAjaxDataProp": "",
			"order": [[ 0, "asc" ]],
			"aoColumns": [
			      { "mData": "id"},
		          { "mData": "username" },
				  { "mData": "useremail" },
				  { "mData": "userfirstname" },
				  { "mData": "userlastname" },
				  { "mData": "useraddress" }
			]
	 ,"lengthMenu" : [ [ 5, 7, 10, 25, 50, 100, -1 ],
			[ 5, 7, 10, 25, 50, 100, "All" ] ]
	 })
});
