package com.shameyang.student_manage.bean;

import java.util.Objects;

/**
 * @author ShameYang
 * @date 2023/9/14 12:20
 * @description 封装学生信息
 */
public class Student {
    private String sno;
    private String sname;
    private String ssex;
    private String telephone;

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getSsex() {
        return ssex;
    }

    public void setSsex(String ssex) {
        this.ssex = ssex;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Student() {
    }

    public Student(String sno, String sname, String ssex, String telephone) {
        this.sno = sno;
        this.sname = sname;
        this.ssex = ssex;
        this.telephone = telephone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(sno, student.sno) && Objects.equals(sname, student.sname) && Objects.equals(ssex, student.ssex) && Objects.equals(telephone, student.telephone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sno, sname, ssex, telephone);
    }
}
