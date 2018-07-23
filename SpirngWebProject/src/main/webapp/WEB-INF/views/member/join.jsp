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
	
	<base href="http://localhost:8282/">
	
</head>
<body>
<h1>
	JOIN  
</h1>

	<form method="post" id="formId">
		<label>ID : </label><input type="text" id="usr" name="id" autocomplete="off" onchange="validationId(this)"/><br>
			<div style="color:red; display:none;" id="inforId">
				아이디는 영문자와 숫자로 이루어져 있으며, 5~10자 이어야 합니다.
			</div>
			<button id="dup">중복확인</button>
		<label>PW : </label><input type="password" id="pwd" name="pw" onchange="validationPw(this)"/><br>
			<div style="color:red; display:none;" id="inforPw">
				비밀번호는 영문자와 숫자가 1개이상 포함되어 있어야 하며, 8~20자 이어야 합니다.
			</div>
		<label>email : </label><input type="email" name="email" autocomplete="off"/>
		<input type="submit" value="OK"/>
	
	</form>

	<script >
	
	$("#dup").on("click",function(){
		var id = $("#usr").val();			/* id가 id인 input 태그에 입력된 id 가져오기 */
		$.ajax({
			async:true,
			type:'POST',
			data:id,
			url:"member/dup",
			dataType:"json",
			contentType:"application/json; charset=UTF-8",
			success : function(data){
				if(data.cnt > 0){
					alert("입력하신 아이디는 이미 존재합니다.");
				}else{
					alert("입력하신 아이디는 사용 가능합니다.");
				}
			}
		});
	});
	
	
	
		function validationId(id){
			
			var inforId = document.getElementById('inforId');
			var idText = id.value;
			var idRegex = /^\w{5,10}$/;
			inforId.style.display = 'none';

			if (idText != null && idRegex.test(idText)){
				return true;
			}
			else {
				inforId.style.display = 'block';
				return false;
			}
		}
		
		function validationPw(pw){
			
			var inforPw = document.getElementById('inforPw');
			var pwText = pw.value;
			var pwRegex = /^(?=\w{8,20})(\w*((\d[a-zA-Z])|([a-zA-Z]\d))\w*)$/;
			inforPw.style.display = 'none';
			
			if (pwText != null && pwRegex.test(pwText)){
				return true;
			}
			else {
				inforPw.style.display = 'block';
				return false;
			}
		}
	
		var formId = document.getElementById('formId');
		formId.onsubmit = function(){
			
			var id = document.getElementById('usr');
			var pw = document.getElementById('pwd');
			var isOK = true;	// 제출을 할건지 말건지 결정하는 변수
			
			if (!validationId(id)){
				isOK = false;
			}

			if (!validationPw(pw)){
				isOK = false;
			}
			if(!isOK){
				return false;
			}
			return true;
			
		}
		
		
		
	</script>




</body>
</html>













