package com.shameyang.student_manage.web;

import com.shameyang.student_manage.dao.UserDao;
import com.shameyang.student_manage.dao.impl.UserDaoImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

/**
 * @author ShameYang
 * @date 2023/9/14 14:17
 * @description 欢迎页
 */
@WebServlet("/welcome")
public class WelcomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 获取 cookie
        Cookie[] cookies = request.getCookies();
        String username = null;
        String password = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                String name = cookie.getName();
                if ("username".equals(name)) {
                    username = cookie.getValue();
                } else if ("password".equals(name)) {
                    password = cookie.getValue();
                }
            }
        }

        if (username != null && password != null) {
            UserDao userDao = new UserDaoImpl();
            boolean success = userDao.selectByNameAndPwd(username, password);
            if (success) {
                HttpSession session = request.getSession();
                session.setAttribute("username", username);
                response.sendRedirect(request.getContextPath() + "/student/list");
            } else {
                response.sendRedirect(request.getContextPath() + "/index.jsp");
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        }
    }
}
