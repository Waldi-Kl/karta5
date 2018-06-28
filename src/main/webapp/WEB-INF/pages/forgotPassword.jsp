<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
    <h1 >reset</h1>
 
    <label >email</label>
    <input id="email" name="email" type="email" value="" />
    <button type="submit" onclick="resetPass()"
      >reset</button>
 
<a href="@{/registration.html}">
    registration
</a>
<a href="@{/login}">login</a>
 
<script src="jquery.min.js"></script>

<a href="http://localhost:8080/Karta5/homepage">HOme</a>

</body>
</html>