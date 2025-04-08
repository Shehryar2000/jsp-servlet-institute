<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Institute of Science & Technology</title>

<!-- Compiled and minified CSS -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">

<!-- Compiled and minified JavaScript -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>

<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">

</head>
<body
	style="background: url('images/bg_1.jpg'); background-size: cover; background-attachment: fixed;">

	<div class='container'>

		<div class='row'>

			<div class='col m6 offset-m3'>

				<div class='card' style="border-radius: 10px">

					<div class='card-content'>

						<h3 style="margin-top: 10px;" class='center-align'>Registration
							Form</h3>
						<h6 class='center-align'>Institute of Science & Technology</h6>
						<br />
						<h5 class='center-align' id='msg'></h5>

						<div class='form center-align'>

							<form action='Register' method='post' id='myform' enctype="multipart/form-data">

								<input type='text' placeholder="Enter Username" name="user_name"
									style="font-style: italic;" /> <input type='email'
									placeholder="Enter Email Address" name="user_email"
									style="font-style: italic;" /> <input type='password'
									placeholder="Enter Strong Password" name="user_pass"
									style="font-style: italic;" />

								<div class="file-field input-field">
									<div class="btn">
										<span>Upload File</span> <input name="image" type="file">
									</div>
									<div class="file-path-wrapper">
										<input class="file-path validate" type="text">
									</div>
								</div>

								<button type='submit' class='btn' style="margin-top: 20px">Sign
									Up</button>

							</form>

						</div>

						<div class='loader center-align'
							style="margin-top: 30px; display: none;">

							<div class="preloader-wrapper small active">
								<div class="spinner-layer spinner-green-only">
									<div class="circle-clipper left">
										<div class="circle"></div>
									</div>
									<div class="gap-patch">
										<div class="circle"></div>
									</div>
									<div class="circle-clipper right">
										<div class="circle"></div>
									</div>
								</div>
							</div>

							<h6>Please Wait...</h6>

						</div>

					</div>
				</div>

			</div>
		</div>
	</div>

	<script src="https://code.jquery.com/jquery-3.7.1.min.js"
		integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
		crossorigin="anonymous">
		
	</script>

	<script type="text/javascript">
		$(document).ready(function() {
			console.log("Page is Ready...");

			$("#myform").on('submit', function(event) {

				event.preventDefault();
				//				var f = $(this).serialize();

				var f = new FormData(this);
				$(".loader").show();
				$(".form").hide();

				$.ajax({

					url : "Register",
					data : f,
					type : "POST",

					success : function(data, textStatus, jqXHR) {

						$(".loader").hide();
						$(".form").show();

						console.log("Data: ", data.trim());
						
						if (data.trim() === 'done') {

							$("#msg").html("Successfully Registered");
							$("#msg").addClass("green-text");

							$('#myform')[0].reset();

						} else {

							$("#msg").html("Something Went Wrong on Server");
							$("#msg").addClass("red-text");

						}

					},

					error : function(jqXHR, textStatus, errorThrown) {

						$(".loader").hide();
						$(".form").show();

						$("#msg").html("Registration Failed");
						$("#msg").addClass("red-text");
					},

					processData : false,
					contentType : false

				})

			})

		})
	</script>

</body>
</html>