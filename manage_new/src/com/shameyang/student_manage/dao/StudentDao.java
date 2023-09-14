package com.shameyang.student_manage.dao;

import com.shameyang.student_manage.bean.Student;
import com.shameyang.student_manage.exceptions.SaveException;

import java.util.List;

/**
 * @author ShameYang
 * @date 2023/9/14 12:26
 * @description 学生表对应的 DAO
 */
public interface StudentDao {
    int insert(Student student);

    int deleteBySno(String sno);

    int update(Student student);

    Student selectBySno(String sno);

    List<Student> selectAll();
}
