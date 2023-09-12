<%--
  Created by IntelliJ IDEA.
  User: shameyang
  Date: 2023/9/10
  Time: 21:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改学生信息</title>
</head>
<body>
    <h1>修改学生信息</h1>
    <hr>
    <form action="${pageContext.request.contextPath}/student/modify" method="post">
        学号：<input type="text" name="sno" value="${student.sno}" readonly><br>
        姓名：<input type="text" name="sname" value="${student.sname}"><br>
        性别：${student.ssex}<br>
        联系电话：<input type="text" name="telephone" value="${student.telephone}"><br>
        <input type="submit" value="保存">
    </form>
</body>
</html>
