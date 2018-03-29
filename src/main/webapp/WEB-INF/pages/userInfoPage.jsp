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
		<div class="w3-container w3-cell ">

			<div>
				<h2>Administracja użytkownikami</h2>
			</div>
			<!--  Tu jakaś treść  -->
			<div class="w3-container  ">

				<div class="w3-border">
					<div class="w3-bar w3-black">
						<button
							class="w3-black w3-bar-item w3-button testbtn w3-padding-16"
							onclick="openTabs(event,'UserInfo')">Dane użytkownika</button>
						<button
							class="w3-black w3-bar-item w3-button testbtn w3-padding-16"
							onclick="openTabs(event,'UserRole')">Role systemowe</button>
					</div>

					<div id="UserInfo" class="w3-container tab w3-animate-opacity">

						<!--  Tu jakaś treść  -->
						<div class="w3-container">
							<!--<table class="w3-table form-horizontal w3-striped w3-border"
								id="userTable">
								<tr>
									<th>Imię</th>
									<th>Nazwisko</th>
									<th>Login</th>
									<th>E-mail</th>
									<th></th>
								</tr>
								<tr>
									<td>${user.name}</td>
									<td>${user.surname}</td>
									<td>${user.login}</td>
									<td>${user.email}</td>
								<td><a href="#" class="w3-button w3-theme" role="button">COS</a></td>

									  href="${pageContext.request.contextPath}/userInfo/user/${user.login}"
								</tr>
							</table> -->
							<hr>


							<div class="w3-card">
								<div class="w3-container w3-black">
									<h3>Dane do zmiany</h3>
								</div>
								<form action="">

									<div class="w3-row">
										<div class="w3-col" style="width: 150px">
											<b><label class="w3-text-black">Imię:</label></b>
										</div>
										<div class="w3-rest">
											<input type="text" class="w3-input w3-border-1" id="name"
												value="${user.name}" name="name">
										</div>
									</div>

									<div class="w3-row">
										<div class="w3-col" style="width: 150px">
											<b><label class="w3-text-black" for="surname">Nazwisko:</label></b>
										</div>
										<div class="w3-rest">
											<input type="text" class="w3-input w3-border-1" id="surname"
												value="${user.surname}" name="surname">
										</div>
									</div>
									<div class="w3-row">
										<div class="w3-col" style="width: 150px">
											<b><label class="w3-text-black" for="surname">Email:</label></b>
										</div>
										<div class="w3-rest">
											<input type="text" class="w3-input w3-border-1" id="email"
												value="${user.email}" name="surname">
										</div>
									</div>

									<div class="w3-row">
										<div class="w3-col" style="width: 150px">
											<b><label class="w3-text-black" for="surname">Hasło:</label></b>
										</div>
										<div class="w3-rest">
											<input type="password" class="w3-input w3-border-1" id="pass"
												value="xxxxxxx" name="pass">
										</div>
									</div>


									<div class="container">
										<a href="#" class="w3-button w3-theme" role="button">Potwierdź</a>

									</div>
								</form>
							</div>
							<hr>

						</div>
					</div>
					<div id="UserRole" class="w3-container tab w3-animate-opacity">
						<p>Info o rolach</p>
					</div>

				</div>
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