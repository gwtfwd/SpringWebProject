<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

	<form>
		<label>ID : </label><input type="text" name="id" autocomplete=off/><br>
		<label>PW : </label><input type="password" name="pw" autocomplete=off/>
		<input type="submit"/>
	
	</form>


<P>  The time on the server is ${serverTime}. </P>
</body>
</html>