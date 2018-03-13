<!-- <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%> -->


<header class="w3-container w3-theme">
	<div class="w3-bar">
		<div class="w3-row">
  <div class="w3-col s10 w3-center">
    <a href="${pageContext.request.contextPath}/" class="w3-bar-item w3-button w3-padding-16"><i
			class="fa fa-home"></i> Home</a>
		<a href="${pageContext.request.contextPath}/admin"
			class="w3-bar-item w3-button w3-padding-16">Administracja</a>
		<a href="${pageContext.request.contextPath}/userInfo" class="w3-bar-item w3-button w3-padding-16">Użytkownik</a>
		<div class="w3-dropdown-hover">
			<button class="w3-button w3-padding-16 w3-theme">
				Karta <i class="fa fa-caret-down"></i>
			</button>
			<div class="w3-dropdown-content w3-card-4 w3-bar-block">
				<a href="${pageContext.request.contextPath}/cardInfo/fishing" class="w3-bar-item w3-button">Wędkarska</a>
				<a href="${pageContext.request.contextPath}/cardInfo/hunting" class="w3-bar-item w3-button">Łowiectwa</a>
				<a href="${pageContext.request.contextPath}/cardInfo/guarding" class="w3-bar-item w3-button">Strażnikow</a>
			</div>
		</div>
		<c:if test="${pageContext.request.userPrincipal.name != null}">
		<a href="${pageContext.request.contextPath}/logout" class="w3-bar-item w3-button w3-padding-16"><i
			class="fa fa-sign-out"></i> Log out</a>
			</c:if>
  </div>
  <div class="w3-col s2 w3-center">
    <p>Login: ${pageContext.request.userPrincipal.name}</p>
  </div>
</div>
	</div>

	</header>
	
