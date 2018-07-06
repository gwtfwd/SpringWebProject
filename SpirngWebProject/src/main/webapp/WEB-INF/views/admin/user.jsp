<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
</head>
<body>
	<div>
		<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
			<div class="container">
				<div class="col-12">
					<div class="float-right">
						<h6 style="display: inline-flex;">
							<a href="/board/list" style="color: white;">게시판페이지</a>
						</h6>
						<span style="color: white;"> | </span>
						
						<h6 style="display: inline-flex;">
							<a href="/member/update?id=${member.id }" style="color: white;">회원정보수정</a>
						</h6>
						<span style="color: white;"> | </span>
						
						<h6 style="display: inline-flex;">
							<a href="/member/logout" style="color: white;">로그아웃</a>
						</h6>
					</div>
				</div>
			</div>
		</nav>
	</div>
	<div class="container">
		<div style="margin-bottom: 30px; margin-top: 80px; text-align: center;">
			<h2 style="color:black;"><a href="/admin/board">게시판 관리</a> | 회원 관리</h2>
		</div>
		<table class="table" style="text-align: center;">
			<thead>
				<tr>
					<th style="width: 20%;">ID</th>
					<th style="width: 40%;">EMAIL</th>
					<th style="width: 20%;">권한</th>  
					<th style="width: 20%;">권한수정</th>  
				</tr>
			</thead>
			<tbody>
				<c:forEach var="user" items="${list}" varStatus="page">
					<tr>
						<td>${user.id }</td>
						<td>${user.email }</td>
						<td>${user.admin }</td>
						<td>
							<c:if test="${user.admin.compareTo('admin') == 0 }">
								<a href="/admin/user/set?admin=user&id=${user.id}&page=${pageMaker.criteria.page }">		
									<button >user</button>
								</a>							
							</c:if>  
							<c:if test="${user.admin.compareTo('user') == 0 }">
								<a href="/admin/user/set?admin=admin&id=${user.id}&page=${pageMaker.criteria.page }">	
									<button >admin</button>
								</a>
							</c:if>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<ul class="pagination" style="justify-content:center;">
			  <c:if test="${pageMaker.prev }">
				<li class="page-item"><a class="page-link" href="/admin/user?page=${pageMaker.startPage-1}">Prev</a></li>
			  </c:if>
			  <c:forEach var="page" begin="${pageMaker.startPage }" end="${pageMaker.endPage }">
			  	<li class="page-item"><a class="page-link" href="/admin/user?page=${page }">${page }</a></li>
			  </c:forEach>
			  <c:if test="${pageMaker.next }">
			  	<li class="page-item"><a class="page-link" href="/admin/user?page=${pageMaker.endPage+1}">Next</a></li>
		   	  </c:if>	
		</ul>
	</div>
</body>
</html>