<%@ page import="java.util.List" %>
<%@ page import="com.shameyang.student_manage.bean.Student" %><%--
  Created by IntelliJ IDEA.
  User: shameyang
  Date: 2023/9/10
  Time: 16:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>学生列表页面</title>
</head>
<body>
<script>
    function del(sno) {
        if (window.confirm("确定删除该同学的信息吗？？？")) {
            document.location.href = "<%=request.getContextPath()%>/student/delete?sno=" + sno;
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
    <%
        // 从域中取出集合
        List<Student> stuList = (List<Student>) request.getAttribute("stuList");
        int i = 0;
        // 遍历集合
        for (Student student : stuList) {
    %>
    <tr>
        <td><%=++i%></td>
        <td><%=student.getSno()%></td>
        <td><%=student.getSname()%></td>
        <td>
            <a href="<%=request.getContextPath()%>/student/detail?sno=<%=student.getSno()%>">详情</a>
            <a href="">修改</a>
            <a href="javascript:void(0)" onclick="del(<%=student.getSno()%>)">删除</a>
        </td>
    </tr>
    <%
        }
    %>
</table>
<a href="<%=request.getContextPath()%>/add.jsp">新增学生</a>
</body>
</html>
