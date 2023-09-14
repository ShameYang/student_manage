package com.shameyang.student_manage.web;

import com.shameyang.student_manage.service.StudentService;
import com.shameyang.student_manage.service.impl.StudentServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

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
        StudentService studentService = new StudentServiceImpl();
        String servletPath = request.getServletPath();
        switch (servletPath) {
            case "/student/list" -> studentService.doList(request, response);
            case "/student/detail" -> studentService.doDetail(request, response);
            case "/student/delete" -> studentService.doDel(request, response);
            case "/student/add" -> studentService.doAdd(request, response);
            case "/student/modify" -> studentService.doModify(request, response);
        }
    }
}
