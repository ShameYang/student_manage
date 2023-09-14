package com.shameyang.student_manage.service;

import com.shameyang.student_manage.exceptions.SaveException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * @author ShameYang
 * @date 2023/9/14 12:34
 * @description 学生信息管理业务
 */
public interface StudentService {
    void doList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException;

    void doDetail(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException;

    void doDel(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException;

    void doAdd(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException;

    void doModify(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException;
}
