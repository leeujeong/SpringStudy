<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>회원 아이디는 : ${member1.id}</h1>
	<h1>회원 비밀번호 : ${member1.pw }</h1>
	
	<a href="memberView1.do">회원정보보기1</a><br>
	<a href="memberView2.do">회원정보보기2</a><br>
	<br>
	<a href="boardView.do">게시글 보기</a>
</body>
</html>