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

		<%@include file="header.jsp" %>

		<!-- registration form -->

		<form:form action="/register" method="POST" modelAttribute="user">
			<div class="row" style="margin-top: 2%;">
				<!-- name -->
				<div class="col-sm-3">
					<p>
						<form:label path="username">Display Name:</form:label>
					</p>
					<p class="text-danger">
						<form:errors path="username" />
					</p>
				</div>
				<div class="col-sm-9">
					<p>
						<form:input path="username" />
					</p>
				</div>
			</div>
			<div class="row">
				<!-- email -->
				<div class="col-sm-3">
					<p>
						<form:label path="email">Email:</form:label>
					</p>
					<p class="text-danger">
						<form:errors path="email" />
					</p>
				</div>
				<div class="col-sm-9">
					<p>
						<form:input path="email" />
					</p>
				</div>
			</div>

			<div class="row">
				<!-- password -->
				<div class="col-sm-3">
					<p>
						<form:label path="password">Password:</form:label>
					</p>
					<p class="text-danger">
						<form:errors path="password" />
					</p>
				</div>
				<div class="col-sm-9">
					<p>
						<form:input type="password" path="password" />
					</p>
				</div>
			</div>
			<div class="row">
				<!-- confirm -->
				<div class="col-sm-3">
					<p>
						<form:label path="passConfirm">Confirm Password:</form:label>
					</p>
					<p class="text-danger">
						<form:errors path="passConfirm" />
					</p>
				</div>
				<div class="col-sm-9">
					<p>
						<form:input type="password" path="passConfirm" />
					</p>
				</div>
			</div>
			<div class="row">
				<!-- button -->
				<button type="submit" class="btn btn-primary">Register</button>
			</div>
		</form:form>

		<%@include file="footer.jsp"%>


	</div>

</body>
</html>