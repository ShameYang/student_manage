package com.shameyang.student_manage.dao;

import com.shameyang.student_manage.bean.User;

import java.util.List;

/**
 * @author ShameYang
 * @date 2023/9/14 14:57
 * @description 用户表对应的 DAO
 */
public interface UserDao {
    default int insert(User user) {
        return 0;
    }

    default int deleteById(Integer id) {
        return 0;
    }

    default int update(User user) {
        return 0;
    }

    boolean selectByNameAndPwd(String username, String password);

    default List<User> selectAll() {
        return null;
    }
}
