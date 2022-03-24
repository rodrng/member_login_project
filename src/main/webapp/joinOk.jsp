<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<%@ page import = "com.javarudals.ex.*" %>
<jsp:useBean id="dto" class="com.javarudals.ex.MemberDto"></jsp:useBean>

<jsp:setProperty property="*" name="dto"/>
<%
	MemberDao dao = MemberDao.getInstance();

	if(dao.confirmId(dto.getId()) == 1) {
%>
	<script type="text/javascript">
		alert("이미 존재하는 아이디입니다. 다시 입력해주세요.");
		history.back();//뒤로가기
	</script>
		
<%	} else {
	
	int ret = dao.insertMember(dto);//1이 반환되면 db 저장 성공
	if (ret == 1) {
%>
	<script type="text/javascript">
		alert("회원가입성공!가입을 축하합니다.");		
	</script>
<%
	} else {
%>
	<script type="text/javascript">
		alert("회원가입실패!다시 확인해주세요.");		
	</script>
<% 
	}
}
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>