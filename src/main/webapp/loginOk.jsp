<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "com.javarudals.ex.*" %>
    
<% 
	request.setCharacterEncoding("UTF-8");

	String id = request.getParameter("id");
	String pw = request.getParameter("pw");
	
	MemberDao dao = MemberDao.getInstance();
	int checkNum = dao.userCheck(id, pw);//1:로그인성공, 0:비밀번호만 틀림, -1:아이디없음
	if(checkNum == -1) {
%>
	<script type="text/javascript">
		alert("아이디가 존재하지 않습니다.");
		history.back();//뒤로가기
	</script>
		
<%	
	} else if(checkNum == 0) {
%>		
	<script type="text/javascript">
		alert("비밀번호가 틀립니다.");
		history.back();//뒤로가기
	</script>	
<%	} else if(checkNum == 1) {	
	
	session.setAttribute("id", id);	
	session.setAttribute("ValidMem", "yes");
	response.sendRedirect("main.jsp");	
}
	
	
	
	
	
	
	
	

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a href="login.jsp">로그인 페이지로 가기</a>
</body>
</html>