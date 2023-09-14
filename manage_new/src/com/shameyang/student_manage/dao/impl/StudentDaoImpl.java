package com.shameyang.student_manage.dao.impl;

import com.shameyang.student_manage.bean.Student;
import com.shameyang.student_manage.dao.StudentDao;
import com.shameyang.student_manage.exceptions.SaveException;
import com.shameyang.student_manage.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ShameYang
 * @date 2023/9/14 12:34
 * @description StudentDao 实现类
 */
public class StudentDaoImpl implements StudentDao {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    int count = 0;

    @Override
    public int insert(Student student) {
        try {
            conn = DBUtil.getConnection();
            String sql = "insert into t_student(sno, sname, ssex, telephone) values (?, ?, ?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, student.getSno());
            ps.setString(2, student.getSname());
            ps.setString(3, student.getSsex());
            ps.setString(4, student.getTelephone());
            count = 0;
            count = ps.executeUpdate();
        } catch (SQLException e) {
           throw new RuntimeException(e);
        } finally {
            DBUtil.close(conn, ps, null);
        }
        return count;
    }

    @Override
    public int deleteBySno(String sno) {
        try {
            conn = DBUtil.getConnection();
            String sql = "delete from t_student where sno = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, sno);
            count = 0;
            count = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(conn, ps, null);
        }
        return count;
    }

    @Override
    public int update(Student student) {
        try {
            conn = DBUtil.getConnection();
            String sql = "update t_student set sname=?, telephone=? where sno=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, student.getSname());
            ps.setString(2, student.getTelephone());
            ps.setString(3, student.getSno());
            count = 0;
            count = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(conn, ps, null);
        }
        return count;
    }

    @Override
    public Student selectBySno(String sno) {
        Student student = new Student();
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

                student.setSno(sno);
                student.setSname(sname);
                student.setSsex(ssex);
                student.setTelephone(telephone);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(conn, ps, rs);
        }
        return student;
    }

    @Override
    public List<Student> selectAll() {
        List<Student> studentList = new ArrayList<>();
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

                Student student = new Student();
                student.setSno(sno);
                student.setSname(sname);
                student.setSsex(ssex);
                student.setTelephone(telephone);

                studentList.add(student);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(conn, ps, rs);
        }
        return studentList;
    }
}
