<%@ page import="hello.servlet.domain.member.Member" %>
<%@ page import="hello.servlet.domain.member.MemberRepository" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  // 이 안에 자바코드를 넣을 수 있다(비즈니스 로직 작성)
  MemberRepository memberRepository = MemberRepository.getInstance();

  // request, response는 자동으로 사용 가능하다.

  String username = request.getParameter("username");
  int age = Integer.parseInt(request.getParameter("age"));
  Member member = new Member(username, age);
  memberRepository.save(member);

%>

<html>
<head>
    <title>Title</title>
</head>
<body>
성공
<ul>
  <li>id=<%=member.getId()%></li>
  <li>username=<%=member.getUsername()%></li>
  <li>age=<%=member.getAge()%></li>
</ul>

<a href="/index.html">메인</a>
</body>
</html>
