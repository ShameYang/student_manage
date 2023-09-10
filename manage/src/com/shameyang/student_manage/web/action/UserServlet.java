package com.shameyang.student_manage.web.action;

import com.shameyang.student_manage.utils.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author ShameYang
 * @date 2023/9/10 15:47
 * @description 用户登录
 */
@WebServlet({"/user/login", "/user/exit"})
public class UserServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String servletPath = request.getServletPath();
        switch (servletPath) {
            case "/user/login" -> doLogin(request, response);
        }
    }

    /**
     * 登录功能
     * @param request 请求
     * @param response 响应
     * @throws ServletException 异常
     * @throws IOException 异常
     */
    private void doLogin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 获取用户名和密码
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // 连接数据库，验证用户名和密码
        try {
            Connection conn = DBUtil.getConnection();
//            String sql = "select * from t_student where ";
//            PreparedStatement ps = conn.prepareStatement(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
//            DBUtil.close(conn, ps, rs);
        }
    }
}
