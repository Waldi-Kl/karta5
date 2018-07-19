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

			<!-- 	------------------------------------------ login info  -------------------------  
			<div class="w3-col s3 w3-center">
				<div class="w3-container w3-cell w3-cell-middle">
					<p>Login: ${pageContext.request.userPrincipal.name}</p>
				</div>
				<div class="w3-container w3-cell">
					<c:if test="${pageContext.request.userPrincipal.name != null}">
						<a href="${pageContext.request.contextPath}/logout"
							class="w3-button w3-padding-16"><i class="fa fa-sign-out"></i>
							Log out</a>
					</c:if>
				</div> 
			</div> -->
			
		</div>
	</div>

	</header>

	<div class="w3-cell-row">

		<!-- Część centralna -->

		<div class="w3-container w3-cell w3-border">
		<p>${errorMessage}</p>
		<p>${successMessage}</p>
		<h1>Zmiana hasła.</h1>
			<form class="w3-container" name='forgot'
				action="${pageContext.request.contextPath}/userInfo/forgot/"
				method='POST'>
				<p>
					<label class="w3-text"><b>E-mail</b></label> <input id="email"
						class="w3-input w3-border" type="email" name="email" value="" />
				</p>

				<p>
					<button class="w3-btn w3-theme" name="submit" type="submit">Wyślij</button>
				</p>
			</form>
		</div>
	</div>



	<jsp:include page="_footer.jsp" />

	<!-- Script for Sidebar, Tabs, Accordions, Progress bars and slideshows -->

	<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
	<!-- Aby zadziałał Jquery musi być ten zapis -->

	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/base.js">
		
	</script>

	<!-- <script src="jquery.min.js"></script>
	<script type = "text/javascript" language = "javascript">
var serverContext = '[[@{/}]]';
function resetPass(){
	console.log("Poszedł resetPass().Zzmienna serverContext: " + serverContext);
	var email = $("#email").val();
	
	var text = '{ "employees" : [' +
	'{ "firstName":"John" , "lastName":"Doe" },' +
	'{ "firstName":"Anna" , "lastName":"Smith" },' +
	'{ "firstName":"Peter" , "lastName":"Jones" } ]}'; 
	var data= JSON.parse(text);
	console.log("email to: " + email);
	console.log("data to: " + data);
    $.post(serverContext + "resetPassword",{email: email} ,
      function(data){
          window.location.href = 
           serverContext + "login?message=" + data.message;
          console.log(" window.location.href to: " +  window.location.href);
    })
    .fail(function(data) {
        if(data.responseJSON.error.indexOf("MailError") > -1)
        {
            window.location.href = serverContext + "emailError.html";
            console.log("FAUL  window.location.href to: " +  window.location.href);
        }
        else{
            window.location.href = 
              serverContext + "login?message=" + data.responseJSON.message;
            console.log("ELSE window.location.href to: " +  window.location.href);
        }
    });
} 
 
</script>-->

</body>
</html>