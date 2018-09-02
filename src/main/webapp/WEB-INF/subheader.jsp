<div class="row">
	<div class="col-sm-12 text-center">
		<c:choose>
			<c:when test="${ user != null}">
				<p>
					hello,
					<c:out value="${user.username}" />
					- <a href="/logout">log out</a>
				</p>
			</c:when>
			<c:otherwise>
				<p>
					<a href="/login">sign in</a> - <a href="/register">sign up</a>
				</p>
			</c:otherwise>

		</c:choose>
	</div>
</div>