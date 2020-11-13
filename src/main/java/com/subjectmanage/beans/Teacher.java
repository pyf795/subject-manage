package com.subjectmanage.beans;

public class Teacher {
    private int teacher_id;
    private String teacher_name;
    private String teacher_num;
    private String password;

    public int getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(int teacher_id) {
        this.teacher_id = teacher_id;
    }

    public String getTeacher_name() {
        return teacher_name;
    }

    public void setTeacher_name(String teacher_name) {
        this.teacher_name = teacher_name;
    }

    public String getTeacher_num() {
        return teacher_num;
    }

    public void setTeacher_num(String teacher_num) {
        this.teacher_num = teacher_num;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "teacher_id=" + teacher_id +
                ", teacher_name='" + teacher_name + '\'' +
                ", teacher_num='" + teacher_num + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
