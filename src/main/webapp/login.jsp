<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% if(session.getAttribute("ValidMem") != null) { %>
	<jsp:forward page="main.jsp"></jsp:forward>
<% } %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 로그인</title>
</head>
<body>
	<h2>회원 로그인</h2>
	<form action="loginOk.jsp" method="post">
		아 이 디 : <input type="text" name="id" size="20"
		value="<% if(session.getAttribute("id") != null) out.println(session.getAttribute("id")); %>"><br><br>
		비밀번호 : <input type="password" name="pw" size="20"><br><br>
		<input type="submit" value="로그인"> &nbsp;&nbsp;&nbsp;
		<input type="button" value="회원가입" onclick="javascript:window.location='join.jsp'">
	</form>
</body>
</html>