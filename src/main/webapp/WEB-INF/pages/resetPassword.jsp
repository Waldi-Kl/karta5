<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="${pageContext.request.contextPath}/css/w3.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/w3_theme.css"
	rel="stylesheet" type="text/css" />

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
					class="fa fa-home"></i> Home</a>

			</div>

		</div>
	</div>

	</header>

	<div class="w3-cell-row">

		<!-- Część centralna -->

		<div class="w3-container w3-cell w3-border">
			<p>${errorMessage}</p>
			<p id="demo"></p>
			<h1>Zmiana hasła.</h1>

				<form class="w3-container" name='reset' 
					action="${pageContext.request.contextPath}/forgotpassword/reset/" onsubmit="return validateForm()"
					method='POST'>
					<p>
						<label for="psw" class="w3-text"><b>Hasło</b></label> 
						<input
							class="w3-input w3-border" type="password" id="psw" name="password"
							pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}"
							title="Musi zawierać conajmniej jedną cyfrę, jedną małą i diżą litere , i co najmniej 8 znaków"
							required>
					</p>
					<p>
						<label for="psw2" class="w3-text"><b>Powtórz Hasło</b></label> <input
							class="w3-input w3-border" type="password" id="psw2" name="psw2"
							required>
					</p>
					<input type="hidden" name="resetToken" value="${resetToken}">
					<p>
						<input class="w3-btn w3-theme" onclick="passwordCheck" type="submit" value="Potwierdź">
					</p>

				</form>
		</div>


	</div>




	<jsp:include page="_footer.jsp" />


	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/base.js">
		
	</script>

	<script>
		
	function validateForm() {
		
	    var x = document.forms["forgot"]["psw"].value;
	    var x2 = document.forms["forgot"]["psw2"].value;
	    console.log("Zmianna text : " + x2);
	    if (x!=x2) {
	        alert("Hasła nie są jednakowe.");
	        return false;
	    }
	    console.log("Coś nie tak");
	}
	</script>


</body>
</html>