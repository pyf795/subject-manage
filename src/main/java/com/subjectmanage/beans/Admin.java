package com.subjectmanage.beans;

public class Admin {
    private int admin_id;
    private String admin_name;
    private String admin_num;
    private String admin_password;


    public int getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
    }

    public String getAdmin_name() {
        return admin_name;
    }

    public void setAdmin_name(String admin_name) {
        this.admin_name = admin_name;
    }

    public String getAdmin_num() {
        return admin_num;
    }

    public void setAdmin_num(String admin_num) {
        this.admin_num = admin_num;
    }

    public String getAdmin_password() {
        return admin_password;
    }

    public void setAdmin_password(String admin_password) {
        this.admin_password = admin_password;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "admin_id=" + admin_id +
                ", admin_name='" + admin_name + '\'' +
                ", admin_num='" + admin_num + '\'' +
                ", admin_password='" + admin_password + '\'' +
                '}';
    }
}
