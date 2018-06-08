<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html;" charset=UTF-8>
	
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
	<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<div class="row"> 
			<h3>게시판</h3>
		</div>
		<div>
			<a href="/board/write" >
			  	<button class="btn btn-primary"> 글쓰기 </button>
		 	</a>
		</div>
		<div> 	
			<table class="table table-dark table-striped">
			    <thead>
					<tr>
			        	<th style="width:20%;">번호</th>
			        	<th style="width:60%;">제목</th>
			        	<th style="width:20%;">작성자</th>
			      	</tr>
			    </thead>
			    
			    <tbody>
			    	<c:forEach items="${list}" var="board">
			    	<tr>
		    			<td>${board.number}</td>
			        	<td ><a href="/board/detail?number=${board.number}" style="color:white">${board.title}</a></td>
			        	<td>${board.author}</td>
			        </tr>
			        </c:forEach>
			    </tbody>
			  </table>
		  </div> 
	</div>
	
</body>
</html>