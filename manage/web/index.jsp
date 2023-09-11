<%--
  Created by IntelliJ IDEA.
  User: shameyang
  Date: 2023/9/10
  Time: 15:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
  <head>
    <title>欢迎使用学生管理系统</title>
  </head>
  <body>
    <h1>您好，请登录</h1>
    <hr>
    <form action="<%=request.getContextPath()%>/user/login" method="post">
      用户名：<input type="text" name="username"><br>
      密码：<input type="password" name="password"><br>
      <input type="submit" value="登录">
      <input type="checkbox" name="f" value="1"/>十天内免登录<br>
    </form>
  </body>
</html>
