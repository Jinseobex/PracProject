<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>

<ul>
	<li><a href="login">로그인</a></li>
	<li><a href="memberSignup">회원가입</a></li>
	<li><a href="board">게시판</a></li>
</ul>

</body>
</html>
