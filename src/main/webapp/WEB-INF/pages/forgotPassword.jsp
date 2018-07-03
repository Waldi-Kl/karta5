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

			<!-- 	------------------------------------------ login info  -------------------------  -->
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
			</div>
		</div>
	</div>

	</header>

	<div class="w3-cell-row">

		<!-- Część centralna -->
		<div class="w3-container w3-cell ">
			<h1>reset</h1>

			<label>email</label> <input id="email"
				name="email" type="email" value="" />
			<button type="submit" onclick="resetPass()">reset</button>

			<a href="@{/registration.html}"> registration </a> 
			<a href="@{/login}">login</a>
		</div>
	</div>



	<jsp:include page="_footer.jsp" />

	<!-- Script for Sidebar, Tabs, Accordions, Progress bars and slideshows -->

	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/base.js">
		
	</script>

	<script src="jquery.min.js"></script>
	<script javascript">
var serverContext = [[@{/}]];
function resetPass(){
    var email = $("#email").val();
    $.post(serverContext + "user/resetPassword",{email: email} ,
      function(data){
          window.location.href = 
           serverContext + "login?message=" + data.message;
    })
    .fail(function(data) {
        if(data.responseJSON.error.indexOf("MailError") > -1)
        {
            window.location.href = serverContext + "emailError.html";
        }
        else{
            window.location.href = 
              serverContext + "login?message=" + data.responseJSON.message;
        }
    });
}
 
</script>

</body>
</html>