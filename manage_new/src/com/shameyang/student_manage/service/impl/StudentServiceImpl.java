package com.shameyang.student_manage.service.impl;

import com.shameyang.student_manage.bean.Student;
import com.shameyang.student_manage.dao.StudentDao;
import com.shameyang.student_manage.dao.impl.StudentDaoImpl;
import com.shameyang.student_manage.service.StudentService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

/**
 * @author ShameYang
 * @date 2023/9/14 14:43
 * @description 学生信息管理业务
 */
public class StudentServiceImpl implements StudentService {
    StudentDao studentDao = new StudentDaoImpl();

    @Override
    public void doList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Student> studentList = studentDao.selectAll();
        // 将集合放到请求域中
        request.setAttribute("stuList", studentList);
        // 转发到 JSP 页面
        request.getRequestDispatcher("/list.jsp").forward(request, response);
    }

    @Override
    public void doDetail(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String sno = request.getParameter("sno");
        Student student = studentDao.selectBySno(sno);
        request.setAttribute("student", student);
        // 这样写可以转发给修改功能使用
        request.getRequestDispatcher("/" + request.getParameter("f") + ".jsp").forward(request, response);
    }

    @Override
    public void doDel(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String sno = request.getParameter("sno");
        // 删除成功，重定向至学生列表页面
        if (studentDao.deleteBySno(sno) == 1) {
            response.sendRedirect(request.getContextPath() + "/student/list");
        }
    }

    @Override
    public void doAdd(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String sno = request.getParameter("sno");
        String sname = request.getParameter("sname");
        String ssex = request.getParameter("ssex");
        String telephone = request.getParameter("telephone");
        Student student = new Student(sno, sname, ssex, telephone);
        if (studentDao.insert(student) == 1) {
            response.sendRedirect(request.getContextPath() + "/student/list");
        }
    }

    @Override
    public void doModify(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String sno = request.getParameter("sno");
        String sname = request.getParameter("sname");
        String ssex = request.getParameter("ssex");
        String telephone = request.getParameter("telephone");

        Student student = new Student(sno, sname, ssex, telephone);

        if (studentDao.update(student) == 1) {
            response.sendRedirect(request.getContextPath() + "/student/list");
        }
    }
}
