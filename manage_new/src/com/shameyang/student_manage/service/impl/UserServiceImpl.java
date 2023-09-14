package com.shameyang.student_manage.service.impl;

import com.shameyang.student_manage.dao.UserDao;
import com.shameyang.student_manage.dao.impl.UserDaoImpl;
import com.shameyang.student_manage.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * @author ShameYang
 * @date 2023/9/14 14:55
 * @description 用户登录和登出
 */
public class UserServiceImpl implements UserService {

    @Override
    public void doExit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            // 手动销毁 session
            session.invalidate();
            // 清除 cookie
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    cookie.setMaxAge(0);
                    // 这里的关联路径要与之前的一致
                    cookie.setPath(request.getContextPath());
                    response.addCookie(cookie);
                }
            }
            response.sendRedirect(request.getContextPath());
        }
    }

    @Override
    public void doLogin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UserDao userDao = new UserDaoImpl();
        boolean success = userDao.selectByNameAndPwd(username, password);

        // 登陆成功，重定向至学生列表
        if (success) {
            // session
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            // cookie
            String f = request.getParameter("f");
            if ("1".equals(f)) {
                Cookie cookie1 = new Cookie("username", username);
                Cookie cookie2 = new Cookie("password", password);
                // 设置关联路径
                cookie1.setPath(request.getContextPath());
                cookie2.setPath(request.getContextPath());
                // 设置有效期十天
                cookie1.setMaxAge(60 * 60 * 24 * 10);
                cookie2.setMaxAge(60 * 60 * 24 * 10);
                // 发送到服务器
                response.addCookie(cookie1);
                response.addCookie(cookie2);
            }
            response.sendRedirect(request.getContextPath() + "/student/list");
        } else {
            response.sendRedirect(request.getContextPath() + "/login_error.jsp");
        }
    }
}
