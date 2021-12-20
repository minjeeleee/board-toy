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
            <form id="modi-form" action="" method="post" enctype="multipart/form-data">
                <div class="input-title-file">
                	<input type="hidden" name="bdIdx" id="bdIdx" value="${boardDetail.board.bdIdx}">
                	<input type="hidden" id="password" value="${boardDetail.board.password}">
                    <input type="text" name="title"  id="title" value="${boardDetail.board.title}" placeholder="제목을 입력해 주세요." maxlength="20" readonly>
                    <div class="fileBox">
                        <label for="ex-file">${boardDetail.board.writer}</label>
                    </div>
                </div>
                <input class="file-upload" type="hidden" name="files"  id="ex-file" multiple>
                <div class="file-list">
                	<ol id="file-list-detail">
						<c:forEach items="${boardDetail.files}" var="file">
							<li><a href="${file.downloadURL}">${file.originFileName}</a></li>
						</c:forEach>
					</ol>
                </div>
                <div class="input-content">
                    <textarea class="content" name="content" id="content" placeholder="내용" readonly>${boardDetail.board.content}</textarea>
                </div>
                <input class="submit-button" type="button" id="modify-form" value="수정하기">
                <input class="submit-button" type="hidden" id="modify" value="수정 완료">
                <input id="delete" class="submit-button" type="submit" value="삭제하기">
            </form>
        </div>
        
    </div>
    
    <script type="text/javascript">
    	let modify = document.getElementById('modify-form');
	    let del = document.getElementById('delete');
	    let password = document.getElementById('password').value;
	    let bdIdx = document.getElementById('bdIdx').value;
	    let form = document.getElementById('modi-form');
		
	    modify.addEventListener('click', () => {
	        let pw = prompt("비밀번호를 입력하세요","password");
	        if(pw == password){
	        	document.getElementById('title').removeAttribute('readonly');
	        	document.getElementById('content').removeAttribute('readonly');
	        	document.getElementById('ex-file').setAttribute('type','file');
	        	document.getElementById('file-list-detail').remove();
	        	document.getElementById('modify').setAttribute('type','submit');
	        	document.getElementById('modify-form').setAttribute('type','hidden');
	        }
	        if(pw != password){
	        	alert('비밀번호가 일치하지 않아 수정이 불가합니다.');
	        }
	    })
	    
	     document.getElementById('modify-form').addEventListener('click', () => {
	    		 form.setAttribute("action","/board/board-modify");
	     })
	    
	    del.addEventListener('click', () => {
	    	let pw = prompt("비밀번호를 입력하세요","password");
	        if(pw == password){
	        	form.setAttribute("action","/board/board-delete");
	        }else{
	        	alert('비밀번호가 일치하지 않아 수정이 불가합니다.');
	        }
    	})
    
    </script>
</body>
</html>