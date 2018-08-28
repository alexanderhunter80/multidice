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

		<div class="row page-header">
			<div class="col-md-3 col-sm-1"></div>
			<div class="col-md-6 col-sm-10">
				<h1 class="text-center" id="page-title">transparent chateau</h1>
			</div>
			<div class="col-md-3 col-sm-1"></div>
		</div>



		<!-- roller -->

		<form:form action="/roll" method="POST" modelAttribute="rollEvent">
			<div class="row">
				<div class="col-sm-12 text-center">
					<span>
						<form:label path="inputstring">Dice:</form:label>
					</span>
					<form:input path="inputstring" class="roll-input" />
					<button class="btn btn-secondary" type="submit">Roll</button>
				</div>
				<div class="row">
					<p class="text-danger">
						<form:errors path="inputstring" />
					</p>
				</div>
			</div>
		</form:form>

		<!-- bottom bar -->

		<footer class="page-footer">
			<div class="row">
				<div class="col-sm-12 text-center">
					<p>This site brought to you by <a href="http://www.alexhunter.io">Alex Hunter.</a></p>
				</div>
			</div>
		</footer>


	</div>

</body>
</html>