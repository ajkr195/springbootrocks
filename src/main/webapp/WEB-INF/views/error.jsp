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
			<div id="notfound">
				<div class="notfound">
					<br>
					<h1>OOPS...</h1>
					<p class="alert alert-secondary">The page you are looking for
						might have been removed had its name changed or is temporarily
						unavailable.</p>
						<h4>Someone from our junior developers/testers will be fired for this...</h4>
						<p class="alert alert-success">But you can try accessing the home page and find the right page. If this doesn't work please contact Administrator.</p>
					<a href="/" class="btn btn-sm btn-primary">Go Home</a> <br>
					<br>
				</div>
			</div>
			<br> <br>
			<hr />
			<img id="img" src="/img/error.jpg" style='height: 100%; width: 30%;' />
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