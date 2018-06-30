<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
	<title>Insert title here</title>
</head>
<body>
	<style>
		.fileDrop{
			width : 100%;
			height : 200px;
			border : solid 1px blue;
		}	
	</style>
	
	<div class="container">
		<div class="row "> 
			<h3 class="offset-2 col-8">게시판 글쓰기</h3>
		</div>
		<form method="post" enctype="multipart/form-data">
			<div class="row">		
				<div class="offset-2 col-8">
					<div class="form-group" >
			  			<label for="usr">제목:</label>
			  			<input type="text" class="form-control" id="usr" name="title">
					</div>
				</div>
				<div class="offset-2 col-8">
					<div class="form-group">
				        <label>파일</label>
				        <input type="file" class="form-control" name="file"/>
				        <div class ="fileDrop"></div>
				        <div class ="uploadedList"></div>
				    </div>
				</div>
				<div class="offset-2 col-8">
					<div class="form-group">
		  				<label for="comment">내용:</label>
		  				<textarea class="form-control" rows="6" id="comment" name="contents"></textarea>
		  				
					</div>
				</div>
				<div class="offset-2 col-8">
					<button type="submit" class="btn btn-primary">완료</button>
				</div>
				<div class="offset-2 col-8">
					<a href="/board/list" >
					  	<button class="btn btn-primary"> 취소 </button>
				 	</a>
				</div>
			</div>
		</form>
	</div>
	
	<script type="text/javascript">
	
		// i: 대소문자 구별 없이
		// 파일이름에 jsp, gif, png, jpeg 이 들어가는지 확인
		function checkImageType(fileName){
			var pattern = /jsp|gif|png|jpeg/i;
			return fileName.match(pattern);
		}
	
	
		$(".fileDrop").on("dragenter dragover", function(e){
			e.preventDefault();
		});
		$(".fileDrop").on("drop", function(e){
			e.preventDefault();
			var files = e.originalEvent.dataTransfer.files;
			var file = files[0];
			console.log(file);
			
			var formData = new FormData();
			formData.append("file", file);
			
			$.ajax({
				url : '/board/display',
				data : formData,
				dataType :'text',
				processData : false,
				contentType : false,
				type : 'POST',
				success : function(data){
  
					var str="";
					if(checkImageType(data)){
						str="<div>" + "<img src='/board/download?fileName="+ data +"'/>" + data + "</div>";
						
					}else{
						str = "<div>" + data + "</div>";
					}
					$(".uploadedList").append(str);					
				}
			})
		});

	
	</script>
</body>
</html>