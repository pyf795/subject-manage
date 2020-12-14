package com.subjectmanage.services;

import com.subjectmanage.beans.Admin;
import com.subjectmanage.mapper.AdminMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Resource
    private AdminMapper adminMapper;

    @Override
    public Admin selectAdmin(int admin_id) {
        return adminMapper.selectAdmin(admin_id);
    }

    @Override
    public Admin selectLoginAdmin(String admin_num, String admin_password) {
        return adminMapper.selectLoginAdmin(admin_num, admin_password);
    }

    @Override
    public List<Admin> selectAll(int startIndex, int pageSize) {
        return adminMapper.selectAll(startIndex, pageSize);
    }

    @Override
    public int addAdmin(Admin admin) {
        return adminMapper.addAdmin(admin);
    }

    @Override
    public int updateAdmin(Admin admin) {
        return adminMapper.updateAdmin(admin);
    }

    @Override
    public int deleteAdmin(int admin_id) {
        return adminMapper.deleteAdmin(admin_id);
    }
}
