package com.subjectmanage.services;

import com.subjectmanage.beans.Admin;

import java.util.List;

public interface AdminService {
    public Admin selectAdmin(int admin_id);

    public Admin selectLoginAdmin(String admin_num, String admin_password);

    public List<Admin> selectAll(int startIndex, int pageSize);


    public int addAdmin(Admin admin);

    public int updateAdmin(Admin admin);

    public int deleteAdmin(int admin_id);
}
