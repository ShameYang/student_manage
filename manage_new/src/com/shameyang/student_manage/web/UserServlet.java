package com.shameyang.student_manage.web;

import com.shameyang.student_manage.service.UserService;
import com.shameyang.student_manage.service.impl.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

/**
 * @author ShameYang
 * @date 2023/9/11 08:17
 * @description 用户登录
 */
@WebServlet({"/user/login", "/user/exit"})
public class UserServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserService userService = new UserServiceImpl();
        String servletPath = request.getServletPath();
        switch (servletPath) {
            case "/user/login" -> userService.doLogin(request, response);
            case "/user/exit" -> userService.doExit(request, response);
        }
    }
}
