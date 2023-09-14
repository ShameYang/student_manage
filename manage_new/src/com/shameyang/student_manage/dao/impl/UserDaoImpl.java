package com.shameyang.student_manage.dao.impl;

import com.shameyang.student_manage.dao.UserDao;
import com.shameyang.student_manage.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author ShameYang
 * @date 2023/9/14 15:04
 * @description UserDao 实现类
 */
public class UserDaoImpl implements UserDao {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Boolean success = false;

    @Override
    public boolean selectByNameAndPwd(String username, String password) {
        try {
            conn = DBUtil.getConnection();
            String sql = "select id, username, password from t_user where username=? and password=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if (rs.next()) {
                success = true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(conn, ps, rs);
        }
        return success;
    }
}
