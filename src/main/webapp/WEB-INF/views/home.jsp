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
			<div class="alert">
				<h4>
					Hello <strong>${pageContext.request.userPrincipal.name},</strong>
					Welcome Home !!
				</h4>
			</div>
			<br> <br> <img id="img" src="/img/casemgmt.jpg"
				style='height: 50%; width: 50%;' />
		</div>

	</div>

</body>
<%@ include file="../fragments/footer.jspf"%>
<script type="text/javascript">
	$(document).ready(function() {
		var tmpAnimation = 0;
		// 	  $("#idbutton").click(function(){
		var element = $("img");
		tmpAnimation = tmpAnimation + 360;

		$({
			degrees : tmpAnimation - 360
		}).animate({
			degrees : tmpAnimation
		}, {
			duration : 500,
			step : function(now) {
				element.css({
					transform : 'rotate(' + now + 'deg)'
				});
			}
		// 	    });
		});
	});
</script>


</html>