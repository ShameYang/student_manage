package com.shameyang.student_manage.web.action;

import com.shameyang.student_manage.bean.Student;
import com.shameyang.student_manage.utils.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

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
@WebServlet({"/student/list", "/student/detail", "/student/delete",
        "/student/add", "/student/modify"})
public class StudentServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session != null & session.getAttribute("username") != null) {
            String servletPath = request.getServletPath();
            switch (servletPath) {
                case "/student/list" -> doList(request, response);
                case "/student/detail" -> doDetail(request, response);
                case "/student/delete" -> doDel(request, response);
                case "/student/add" -> doAdd(request, response);
                case "/student/modify" -> doModify(request, response);
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
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

    /**
     * 详情功能
     * @param request 请求
     * @param response 响应
     * @throws ServletException 异常
     * @throws IOException 异常
     */
    private void doDetail(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String sno = request.getParameter("sno");
        Student student = new Student();
        student.setSno(sno);
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "select sno, sname, ssex, telephone from t_student where sno=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, sno);
            rs = ps.executeQuery();
            if (rs.next()) {
                String sname = rs.getString("sname");
                String ssex = rs.getString("ssex");
                String telephone = rs.getString("telephone");

                student.setSname(sname);
                student.setSsex(ssex);
                student.setTelephone(telephone);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }

        request.setAttribute("student", student);

        // 这样写可以转发给修改功能使用
        request.getRequestDispatcher("/" + request.getParameter("f") + ".jsp").forward(request, response);
    }

    /**
     * 删除功能
     * @param request 请求
     * @param response 响应
     * @throws ServletException 异常
     * @throws IOException 异常
     */
    private void doDel(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String sno = request.getParameter("sno");
        Connection conn = null;
        PreparedStatement ps = null;
        // 记录是否执行
        int count = 0;
        try {
            conn = DBUtil.getConnection();
            String sql = "delete from t_student where sno=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, sno);
            count = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, null);
        }

        // 删除成功，重定向至学生列表页面
        if (count == 1) {
            response.sendRedirect(request.getContextPath() + "/student/list");
        }
    }

    /**
     * 新增学生
     * @param request 请求
     * @param response 响应
     * @throws ServletException 异常
     * @throws IOException 异常
     */
    private void doAdd(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String sno = request.getParameter("sno");
        String sname = request.getParameter("sname");
        String ssex = request.getParameter("ssex");
        String telephone = request.getParameter("telephone");
        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;

        try {
            conn = DBUtil.getConnection();
            String sql = "insert into t_student(sno, sname, ssex, telephone) " +
                    "VALUES (?, ?, ?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, sno);
            ps.setString(2, sname);
            ps.setString(3, ssex);
            ps.setString(4, telephone);
            count = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, null);
        }

        if (count == 1) {
            response.sendRedirect(request.getContextPath() + "/student/list");
        }
    }

    /**
     * 修改学生信息
     * @param request 请求
     * @param response 响应
     * @throws ServletException 异常
     * @throws IOException 异常
     */
    private void doModify(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String sno = request.getParameter("sno");
        String sname = request.getParameter("sname");
        String telephone = request.getParameter("telephone");

        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;
        try {
            conn = DBUtil.getConnection();
            String sql = "update t_student set sname=?, telephone=? where sno=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, sname);
            ps.setString(2, telephone);
            ps.setString(3, sno);
            count = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, null);
        }

        if (count == 1) {
            response.sendRedirect(request.getContextPath() + "/student/list");
        }
    }
}
