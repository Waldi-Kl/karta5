<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="d"%>
<%@ page isELIgnored="false"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet"
	href="https://www.w3schools.com/lib/w3-theme-black.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<title>${title}</title>
</head>
<body>
	<jsp:include page="_menuAdmin.jsp" />


	<!-- Treść strony -->
	<div class="w3-cell-row">


		<!-- Część centralna -->
		<div class="w3-container  ">

		<div class="w3-container">
			<div class="w3-container ">
				<label>Szukaj:</label>
			</div>

			<div class="w3-container w3-cell">
				<input class="w3-input w3-border" type="text" placeholder="Nazwisko"
					id="adminInput" onkeyup="findUserFunction()">
			</div>

			<div class="w3-container w3-cell">
				<button class="w3-button w3-theme w3-cell"
					onclick="document.getElementById('adminInput').value = '';findUserFunction()">Wyczyść</button>
			</div>
		</div>
			

			<!--  Tu jakaś treść  -->
			<div class="w3-container">
			<button class="w3-button w3-blue">+Dodaj</button>
				<table class="w3-table w3-striped w3-border" id="userTable">
					<tr>
						<th>Imie</th>
						<th>Nazwisko</th>
						<th>Login</th>
						<th>E-mail</th>
						<th></th>
					</tr>


					<c:forEach var="user" items="${users}">
						<tr>
							<td>${user.name}</td>
							<td>${user.surname}</td>
							<td>${user.login}</td>
							<td>${user.email}</td>
							<td><button class="w3-button w3-theme">Edycja</button></td>
						</tr>
					</c:forEach>
				</table>
				
			</div>
		</div>
	</div>

	<jsp:include page="_footer.jsp" />

	<!-- Script for Sidebar, Tabs, Accordions, Progress bars and slideshows -->
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/base.js">
		
	</script>
</body>
</html>
