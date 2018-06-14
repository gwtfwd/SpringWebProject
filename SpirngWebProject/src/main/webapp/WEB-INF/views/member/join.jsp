<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html;" charset=UTF-8>
	
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
	<title>JOIN</title>
</head>
<body>
<h1>
	JOIN  
</h1>

	<form method="post">
		<label>ID : </label><input type="text" name="id" autocomplete=off/><br>
		<label>PW : </label><input type="password" name="pw" autocomplete=off/><br>
		<label>email : </label><input type="email" name="email" autocomplete=off/>
		<input type="submit" value="OK"/>
	
	</form>

</body>
</html>