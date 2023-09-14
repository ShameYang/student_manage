<%--
  Created by IntelliJ IDEA.
  User: shameyang
  Date: 2023/9/10
  Time: 17:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>学生详细信息</title>
  </head>
  <body>
    <h1>学生详细信息</h1>
    <hr>
    学号：${student.sno}<br>
    姓名：${student.sname}<br>
    性别：${student.ssex}<br>
    联系电话：${student.telephone}<br>
    <input type="button" value="后退" onclick="window.history.back()">
  </body>
</html>
