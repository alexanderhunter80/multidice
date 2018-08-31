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
			<div class="col-md-3 col-sm-1 text-right"></div>
		</div>

		<div class="row">
			<div class="col-sm-12 text-center">
				<c:choose>
					<c:when test="${ user != null}">
						<p>
							hello,
							<c:out value="${user.username}" /> - 
							<a href="/logout">log out</a>
						</p>
					</c:when>
					<c:otherwise>
						<p>
							<a href="">sign in</a> - <a href="">sign up</a>
						</p>
					</c:otherwise>

				</c:choose>
			</div>
		</div>

		<!-- roller -->

		<form:form action="/roll" method="POST" modelAttribute="rollEvent">
			<div class="row">
				<div class="col-sm-12 text-center">
					<form:input path="inputstring" class="roll-input"
						placeholder="Example: 1d20 + 5" />
					<button class="btn btn-secondary" type="submit">Roll</button>
				</div>
				<div class="row">
					<p class="text-danger">
						<form:errors path="inputstring" />
					</p>
				</div>
			</div>
		</form:form>

		<!-- instructions -->

		<div class="row">
			<div class="col-sm-12 text-center">
				<p class="text-subtle">
					Input any number of dice and integers, separated by + symbols.
					Whitespace is ignored.<br /> For dice, use the format "NdS" where
					N is quantity and S is number of sides (or a special die
					identifier) - e.g. 1d20, 6d6, 4dF.
				</p>
			</div>
		</div>

		<div class="row">
			<div class="col-sm-12">
				<div class="card result-card">
					<div class="card-body">
						<div class="row">
							<div class="col-sm-10">
								<h4 class="card-title">Advanced Methods</h4>
							</div>
							<div class="col-sm-2 text-right">
								<p><a href="">[show/hide]</a></p>
							</div>
						</div>
						<div class="row">
							<div class="col-md-4 col-sm-6 col-xs-12">
								<p>Col 1</p>
							</div>
							<div class="col-md-4 col-sm-6 col-xs-12">
								<p>Col 2</p>
							</div>
							<div class="col-md-4 col-sm-6 col-xs-12">
								<p>Col 3</p>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<!-- bottom bar -->

		<footer class="page-footer">
			<div class="row">
				<div class="col-sm-12 text-center">
					<p>
						This site brought to you by <a href="http://www.alexhunter.io">Alex
							Hunter.</a>
					</p>
				</div>
			</div>
		</footer>


	</div>

</body>
</html>