package com.shameyang.student_manage.web.action;

import com.shameyang.student_manage.bean.Student;
import com.shameyang.student_manage.utils.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ShameYang
 * @date 2023/9/10 16:36
 * @description 学生列表
 */
@WebServlet({"/student/list"})
public class StudentServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String servletPath = request.getServletPath();
        switch (servletPath) {
            case "/student/list" -> doList(request, response);
        }
    }

    /**
     * 连接数据库，查询所有学生信息，将信息收集好后跳转到 JSP 页面展示
     * @param request 请求
     * @param response 响应
     * @throws ServletException 异常
     * @throws IOException 异常
     */
    private void doList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Student> stus = new ArrayList<>();

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "select sno, sname, ssex, telephone from t_student";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String sno = rs.getString("sno");
                String sname = rs.getString("sname");
                String ssex = rs.getString("ssex");
                String telephone = rs.getString("telephone");
                // 将以上数据封装为 java 对象
                Student student = new Student();
                student.setSno(sno);
                student.setSname(sname);
                student.setSsex(ssex);
                student.setTelephone(telephone);
                // 将学生对象放到 list 集合中
                stus.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }
        // 将集合放到请求域中
        request.setAttribute("stuList", stus);
        // 转发到 JSP 页面
        request.getRequestDispatcher("/list.jsp").forward(request, response);
    }
}
