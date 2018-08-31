<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Transparent Chateau Dice Roller</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	type="text/css" />
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="/css/generic.css" type="text/css" />
</head>
<body>

	<div class="container">
	
		<!-- fix this shit to use email instead of username - username should be displayname only -->

		<form action="/login" method="POST">
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
			<div class="row">
				<!-- email -->
				<div class="col-sm-3">
					<p>
						<label for="username">Username:</label>
					</p>
				</div>
				<div class="col-sm-9">
					<p>
						<input type="text" name="username" />
					</p>
				</div>
			</div>
			<div class="row">
				<!-- password -->
				<div class="col-sm-3">
					<p>
						<label for="password">Password:</label>
					</p>
				</div>
				<div class="col-sm-9">
					<p>
						<input type="password" name="password" />
					</p>
				</div>
			</div>
			<div class="row">
				<!-- button -->
				<button type="submit" class="btn btn-primary">Login</button>
			</div>
		</form>

	</div>

</body>
</html>