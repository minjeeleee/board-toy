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
    <link rel="stylesheet"  href="${contextPath}/resources/css/style.css">
    <title>board</title>
</head>

<body>
    <div class="board-wrap">
        <div class="table-inner">
            <table class="test-board">
                    <caption>게시물 목록</caption>
            
                    <colgroup>
                    <col style="width:88px">
                    <col>
                    <col style="width:118px">
                    <col style="width:80px">
                    <col style="width:68px">
                    <col style="width:68px">
                    </colgroup>
            
                    <thead>
                    <tr id="normalTableTitle">
                        <th>no</th>
                        <th scope="col"><span class="article_title">제목</span></th>
                        <th scope="col" class="th_name">작성자</th>
                        <th scope="col">작성일</th>
                        <th scope="col">조회</th>
                    </tr>
                    </thead>
            
                    <tbody>
                    	<c:forEach var="list" items="${boardList}" varStatus="status">
                            <tr class="board">
                                <td class="td_num">${status.count}</td>
                                <td class="td_title">
                                <a href="/board/board-detail?bdIdx=${list.bdIdx}">
                                    ${list.title}
                                </a>
                                </td>
                                <td class="td_name">${list.writer}</td>
                                <td class="td_date">${list.regDate}</td>
                                <td class="td_view">${list.views}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                
                <div class="button-wrap">
                    <a class="wt-button" href="/board/board-form">글쓰기</a>
                </div>
                <div class="paging">
                    <a class="page-num red-border" href="#">1</a>
                    <a class="page-num " href="#">2</a>
                </div>
        </div>
    </div>
</body>
</html>