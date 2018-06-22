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
		<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
			<div class="container">
				<div class="col-12">
					<form class="form-inline" style="display:inline-block;">
					  <select class="form-control" name="type" >
					    <option value="0" <c:out value="${type==0? 'selected':''}"/>>선택</option>
					    <option value="1" <c:out value="${type==1? 'selected':''}"/>>제목</option>
					    <option value="2" <c:out value="${type==2? 'selected':''}"/>>저자</option>
					    <option value="3" <c:out value="${type==3? 'selected':''}"/>>내용</option>
					  </select>
					    <input class="form-control mr-sm-2" type="text" placeholder="Search" value="${search }" name="search">
					    <button class="btn btn-success" type="submit">Search</button>
			  		</form>
			 	 </div>
			</div>
		</nav>
	<div class="container">	
		<div class="row"> 
			<h3>게시판</h3><br><br>
		</div>
		<div>
			<a href="/board/write" >
			  	<button class="btn btn-primary" > 글쓰기 </button>
		 	</a>

		 	<c:if test="${admin}">
				<h6 style="display: inline-flex;"><a href="/admin/board" style=" float:right;">관리자페이지</a></h6>
				<span > | </span>
			</c:if>	
			<a href="/board/mylist" >내가쓴글 </a>

			<a href="/member/logout" style="float:right;">로그아웃 </a>
		 	<span style="float:right;"> | </span>
			<a href="/member/update" style="float:right;">회원정보수정</a>
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
			  <ul class="pagination" style="justify-content:center;">
				  <c:if test="${pageMaker.prev }">
					<li class="page-item"><a class="page-link" href="/board/list?page=${pageMaker.startPage-1}&search=${search}&type=${type}">Prev</a></li>
				  </c:if>
				  <c:forEach var="page" begin="${pageMaker.startPage }" end="${pageMaker.endPage }">
				  	<li class="page-item"><a class="page-link" href="/board/list?page=${page }&search=${search}&type=${type}">${page }</a></li>
				  </c:forEach>
				  <c:if test="${pageMaker.next }">
				  	<li class="page-item"><a class="page-link" href="/board/list?page=${pageMaker.endPage+1}&search=${search}&type=${type}">Next</a></li>
			   	  </c:if>	
			   </ul>
		  </div> 
	</div>
</body>
</html>