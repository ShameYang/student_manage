<%--
  Created by IntelliJ IDEA.
  User: shameyang
  Date: 2023/9/10
  Time: 21:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>新增学生</title>
    <base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
</head>
<body>
    <h1>新增学生</h1>
    <hr>
    <form action="student/add" method="post">
        学号：<input type="text" name="sno"><br>
        姓名：<input type="text" name="sname"><br>
        性别：男<input type="radio" name="ssex" value="男"> 女<input type="radio" name="ssex" value="女"><br>
        联系电话：<input type="text" name="telephone"><br>
        <input type="submit" value="保存">
    </form>
</body>
</html>
