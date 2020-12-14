package com.subjectmanage.mapper;

import com.subjectmanage.beans.Admin;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminMapper {
    public Admin selectAdmin(int admin_id);

    public Admin selectLoginAdmin(String admin_num, String admin_password);

    public List<Admin> selectAll(int startIndex, int pageSize);


    public int addAdmin(Admin admin);

    public int updateAdmin(Admin admin);

    public int deleteAdmin(int admin_id);
}
