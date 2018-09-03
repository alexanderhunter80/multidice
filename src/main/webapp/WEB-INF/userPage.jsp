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

		<%@include file="header.jsp"%>
		<%@include file="subheader.jsp"%>

		<div class="row">
			<div class="col-sm-12 text-center">
				<h4>All rolls by <c:out value="${ user.username }" />:</h4>
			</div>
		</div>

		<c:forEach items="${ user.rollEvents }" var="roll">
			<div class="row">
				<div class="col-sm-12">
					<div class="card result-card">
						<div class="card-body">
							<h4 class="card-title">Results</h4>
							<h6 class="card-subtitle font-italic text-subtle">
								Rolled on
								<c:out value="${ roll.createdAt }" />
								by
								<c:choose>
									<c:when test="${ roll.user != null }">
										<c:out value="${ roll.user.username }" />#<c:out
											value="${ roll.user.id }" />
									</c:when>
									<c:otherwise>
								Anonymous
							</c:otherwise>
								</c:choose>
							</h6>
							<br />
							<p class="card-text">
								Input:
								<c:out value="${ roll.inputString }" />
								<br />Raw results:
								<c:out value="${ roll.rawResults }" />
								<br /> Final results:
								<c:out value="${ roll.finalResults }" />
							</p>
						</div>
					</div>
				</div>
			</div>
		</c:forEach>
		
		<br />
		<br />
		<br />
		<br />

		<%@include file="footer.jsp"%>

	</div>

</body>
</html>