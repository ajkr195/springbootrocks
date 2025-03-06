'use strict';

function showUserInfo(user) {

	let htmlstring = '<div class="row m-0"><div class="text-end col-4 mb-2"><b>Id: </b></div> <div class="text-start col-8">' + user.id + '</div></div>' +
		'<div class="row m-0"><div class="text-end col-4 mb-2"><b>Email: </b></div> <div class="text-start col-8">' + user.useremail + '</div></div>' +
		'<div class="row m-0"><div class="text-end col-4 mb-2"><b>Unique Id: </b></div> <div class="text-start col-8">' + user.useruuid + '</div></div>' +
		'<div class="row m-0" ><div class="text-end col-4 mb-2"><b>Firstname: </b></div> <div class="text-start col-8">' + user.userfirstname + '</div></div > ' +
		'<div class="row m-0"><div class="text-end col-4 mb-2"><b>Lastname: </b></div> <div class="text-start col-8">' + user.userlastname + '</div></div>' +
		'<div class="row m-0"><div class="text-end col-4 mb-2"><b>Active: </b></div> <div class="text-start col-8">' + user.userenabled + '</div></div>' +
		'<div class="row m-0"><div class="text-end col-4 mb-2"><b>Creator: </b></div> <div class="text-start col-8">' + user.createdBy + '</div></div>' +
		'<div class="row m-0"><div class="text-end col-4 mb-2"><b>created Date: </b></div> <div class="text-start col-8">' + user.createdDate + '</div></div>' +
		'<div class="row m-0"><div class="text-end col-4 mb-2"><b>Modifier: </b></div> <div class="text-start col-8">' + user.lastModifiedBy + '</div></div>' +
		'<div class="row m-0"><div class="text-end col-4 mb-2"><b>Modified Date: </b></div> <div class="text-start col-8">' + user.lastModifiedDate + '</div></div>' +
		//'<div class="row m-0"><div class="text-end col-4 mb-2"><b>Roles: </b></div> <div class="text-start col-8">' + user.roles + '</div></div>' +
		//'<div class="row m-0"><div class="text-end col-4 mb-2"><b>Departments: </b></div> <div class="text-start col-8">' + user.departments + '</div></div>' +
		'</br>Auto closing in <b><timer></timer></b> milliseconds.';

	let timerInterval;

	Swal.fire({
		title: user.useremail, // + ' ' + user.userlastname,
		icon: 'info',
		html: htmlstring,
		showCloseButton: true,
		showConfirmButton: false,
		showCancelButton: true,
		cancelButtonText: 'Close',
		cancelButtonColor: '#006666',
		//allowOutsideClick: false,
		//width: '40%',
		timer: 10000,
		timerProgressBar: true,

		didOpen: () => {
			//Swal.showLoading()
			const b = Swal.getHtmlContainer().querySelector('timer')
			timerInterval = setInterval(() => {
				b.textContent = Swal.getTimerLeft()
			}, 100)
		},
		willClose: () => {
			clearInterval(timerInterval)
		}
	})

}

function activateUser(id, Object) {
	Swal.fire({
		title: 'Are you sure?',
		text: "You want to Activate this User?",
		icon: 'warning',
		showDenyButton: true,
		confirmButtonColor: '#006666',
		denyButtonColor: 'gray',
		confirmButtonText: 'Yes, activate it!',
		denyButtonText: `Don't activate`,
	}).then((result) => {
		if (result.isConfirmed) {
			fetch('/api/activateUser/' + id, {
				method: 'POST',
			})
			Swal.fire(
				'Activated!',
				'User has been activated.',
				'success'
			).then(() => {
				location.reload();
			});
		} else if (result.isDenied) {
			Swal.fire('No changes made.', 'User is still in-active !!  ', 'info')
		}
	})
}

function deActivateUser(id, Object) {
	Swal.fire({
		title: 'Are you sure?',
		text: "You want to De-Activate this User?",
		icon: 'warning',
		showDenyButton: true,
		confirmButtonColor: '#006666',
		denyButtonColor: 'gray',
		confirmButtonText: 'Yes, De-Activate it!',
		denyButtonText: `Don't De-activate`,
	}).then((result) => {
		if (result.isConfirmed) {
			fetch('/api/deActivateUser/' + id, {
				method: 'POST',
			})
			Swal.fire(
				'De-Activated!',
				'User has been de-activated.',
				'success'
			).then(() => {
				location.reload();
			});
		} else if (result.isDenied) {
			Swal.fire('No changes made.', 'User is still active !!  ', 'info')
		}
	})
}

/*function deleteUser(id, Object) {
	Swal.fire({
		title: 'Are you sure?',
		text: "You want to Delete this User? This operation is irreversible and this file may not be recovered later.",
		icon: 'warning',
		showDenyButton: true,
		confirmButtonColor: '#006666',
		denyButtonColor: 'gray',
		confirmButtonText: 'Yes, delete it!',
		denyButtonText: `Don't delete`,
	}).then((result) => {
		if (result.isConfirmed) {
			fetch('/api/deleteUser/' + id, {
				method: 'DELETE',
			})
			Swal.fire(
				'Deleted!',
				'User has been deleted.',
				'success'
			)
			Object.closest('tr').remove();
		} else if (result.isDenied) {
			Swal.fire('Great. Changes are not saved.', 'User not deleted  !!  ', 'info')
		}
	})
} */