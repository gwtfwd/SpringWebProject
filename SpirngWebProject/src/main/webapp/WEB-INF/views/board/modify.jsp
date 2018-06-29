<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
   <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   
   <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
   <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
   <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
   <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
   <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.1.0/css/all.css" integrity="sha384-lKuwvrZot6UHsBSfcMvOkWwlCMgc0TaWr+30HWe3a4ltaBwTZhyTEggF5tJv8tbt" crossorigin="anonymous">
   <title>Insert title here</title>
</head>
<body>
   <div class="container">
      <div class="row "> 
         <h3 class="offset-2 col-8">게시글 수정</h3>
      </div>
      <form method="post" enctype="multipart/form-data">
         <div class="row">      
	         <div class="offset-2 col-8">
               <div class="form-group" >
                    <label for="num">번호:</label>
                    <input type="text" class="form-control" id="num" name="number"   value="${board.number}" >
               </div>
            </div>
            <div class="offset-2 col-8">
               <div class="form-group" >
                    <label for="usr">제목:</label>
                    <input type="text" class="form-control" id="usr" name="title"   value="${board.title}" >
               </div>
            </div>
            <div class="offset-2 col-8">
               <div class="form-group">
                    <label for="pwd">작성자:</label>
                    <input type="text" class="form-control" id="pwd" name="author"  value="${board.author}" >
               </div>
            </div>
            <div class="offset-2 col-8">
	            <div class="form-group">
                    <label >첨부파일:</label>
	                    <div class="form-control" id="file" name="filename">
	                    	<c:if test="${fileName != null }">
	                    		<a href="/board/download?fileName=${board.filepath}" target="_blank">
	                   				${fileName}
	                   			</a>
	                   			<a href="/board/modify?number=${board.number}&del=1"><i class="fas fa-times"></i></a>
	                   		</c:if>	
	                   		<c:if test="${fileName == null }">
	                   			첨부파일 없음
	                    	</c:if>	
	                    	
	                    </div>
	             </div>
            </div>
            <div class="form-group">
                 <input type="text" class="form-control" name="del" style="display:none" value="${fileNmae==null?1:null }"/>
            </div>
            <div class="offset-2 col-8">
               <div class="form-group">
                    <label>첨부파일수정:</label>
	                    <input type="file" class="form-control" name="file"/>
               </div>
            </div>
            <div class="offset-2 col-8">
               <div class="form-group">
                    <label for="comment">내용:</label>
                    <textarea class="form-control" rows="6" id="comment" name="contents" >${board.contents}</textarea>
                    
               </div>
            </div>
            <div class="offset-2 col-8">
               <a>
               		<button type="submit" class="btn btn-primary">수정</button>
               </a>
               <a href="/board/list">
              		<button type="submit" class="btn btn-primary">취소</button>
               </a>
               
            </div>
         </div>
      </form>
   </div>
</body>
</html>