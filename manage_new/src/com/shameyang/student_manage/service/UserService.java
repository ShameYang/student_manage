package com.shameyang.student_manage.service;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * @author ShameYang
 * @date 2023/9/14 14:54
 * @description 用户登录业务
 */
public interface UserService {
    void doExit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException;

    void doLogin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException;
}
