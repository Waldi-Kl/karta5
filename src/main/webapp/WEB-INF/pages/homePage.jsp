<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

	<header class="w3-container w3-theme">
	<div class="w3-bar">
		<div class="w3-row">
			<!-- 	------------------------------------------ linki -------------------------  -->
			<div class="w3-col s9 w3-center">
				<a href="${pageContext.request.contextPath}/"
					class="w3-bar-item w3-button w3-padding-16"><i
					class="fa fa-home"></i> Home</a> <a
					href="${pageContext.request.contextPath}/admin"
					class="w3-bar-item w3-button w3-padding-16">Administracja</a>
				<div class="w3-dropdown-hover">
					<button class="w3-button w3-padding-16 w3-theme">
						Karta <i class="fa fa-caret-down"></i>
					</button>
					<div class="w3-dropdown-content w3-card-4 w3-bar-block">
						<a href="${pageContext.request.contextPath}/cardInfo/fishing"
							class="w3-bar-item w3-button">Wędkarska</a> <a
							href="${pageContext.request.contextPath}/cardInfo/hunting"
							class="w3-bar-item w3-button">Łowiectwa</a> <a
							href="${pageContext.request.contextPath}/cardInfo/guarding"
							class="w3-bar-item w3-button">Strażnikow</a>
					</div>
				</div>

			</div>

			<!-- 	------------------------------------------ login info  -------------------------  -->
			<div class="w3-col s3 w3-center">
				<div class="w3-container w3-cell w3-cell-middle">
				<p>Login: ${pageContext.request.userPrincipal.name}</p>							  
				</div>
				<div class="w3-container w3-cell">
					<c:if test="${pageContext.request.userPrincipal.name != null}">
						<a href="${pageContext.request.contextPath}/logout"
							class="w3-button w3-padding-16"><i
							class="fa fa-sign-out"></i> Log out</a>
					</c:if>
				</div>
			</div>
		</div>
	</div>

	</header>

	<div class="w3-cell-row">


		<!-- Część centralna -->
		<div class="w3-container w3-cell ">
			<div>
				<h1>Witaj w aplikacji Karta Wędkarska</h1>
				<h3>Wybierz rodzaj zadania.</h3>

				<!--  Tu jakaś treść  -->
				<div class="w3-border">

					<!--  Tutaj jakaś tresć -->
					<div class="w3-container w3-cell"></div>

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