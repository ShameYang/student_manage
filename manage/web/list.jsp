<%--
  Created by IntelliJ IDEA.
  User: shameyang
  Date: 2023/9/10
  Time: 16:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>学生列表页面</title>
    <base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
</head>
<body>
<script>
    function del(sno) {
        if (window.confirm("确定删除该同学的信息吗？？？")) {
            document.location.href = "${pageContext.request.contextPath}/student/delete?sno=" + sno;
        }
    }
</script>
<h1>学生列表</h1>
<hr>
<table>
    <tr>
        <td>序号</td>
        <td>学号</td>
        <td>姓名</td>
        <td>操作</td>
    </tr>
    <c:forEach items="${stuList}" varStatus="stuStatus" var="student">
        <tr>
            <td>${stuStatus.count}</td>
            <td>${student.sno}</td>
            <td>${student.sname}</td>
            <td>
                <a href="student/detail?f=detail&sno=${student.sno}">详情</a>
                <a href="student/detail?f=edit&sno=${student.sno}">修改</a>
                <a href="javascript:void(0)" onclick="del(${student.sno})">删除</a>
            </td>
        </tr>
    </c:forEach>
</table>
<a href="add.jsp">新增学生</a>
<br>
<a href="user/exit">退出系统</a>
</body>
</html>
