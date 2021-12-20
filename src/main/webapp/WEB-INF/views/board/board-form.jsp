<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/reset-css@5.0.1/reset.min.css">
    <link rel="stylesheet"  href="${contextPath}/resources/css/input.css">
    <title>board</title>
</head>

<body>
    <div class="board-wrap">
       
        <div class="wt-board">
            <form action="/board/upload" method="post" id="board-form" enctype="multipart/form-data">
                <div class="input-title-file">
                    <input type="text" name="title" placeholder="제목을 입력해 주세요." maxlength="20">
                </div>
                 <input class="file-upload"type="file" name="files"  id="ex-file" multiple>
                <div class="input-content">
                    <textarea class="content" name="content" placeholder="내용"></textarea>
                </div>
                <div class="user-check-box">
                	<div class="fileBox">
	                    <div>
	                    	<label >닉네임</label>
	                    	<input type="text" name="writer" id="writer" class="user-check">
	                    </div>
                	</div>
	                <div class="fileBox">
	                	<div>
	                    	<label >비밀번호</label>
	                    	<input type="password" name="password" id="password" class="user-check" >
	                    </div>
	                </div>
                </div>
                <input class="submit-button" type="submit" value="글쓰기">
            </form>
        </div>
        
    </div>
    
    <script type="text/javascript">
    document.getElementById('board-form').addEventListener('submit',e=>{
		   let writer = document.querySelector('#writer').value;
		   let password = document.querySelector('#password').value;
		  
		   let pwReg = /(?=.*[a-zA-Z])(?=.*[0-9])(?=.{4,})/;
		   let writerReg = /^[\w\Wㄱ-ㅎㅏ-ㅣ가-힣]{2,10}$/;
		   
		   if(!writerReg.test(writer)){
			   alert('닉네임은 2~10글자로 설정해야 합니다. '); 
			   e.preventDefault();
		   }
		   
		   if(!pwReg.test(password)){
			   alert('비밀번호는 숫자,영문 조합의 4글자 이상인 문자열입니다');
			   e.preventDefault();
		   }
		   
		  
	   })
    
    
    
    </script>
</body>
</html>