package com.subjectmanage.services;

import com.subjectmanage.beans.Teacher;
import com.subjectmanage.mapper.TeacherMapper;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.List;

@Service
public class TeacherServiceimpl implements TeacherService {

    @Resource
    private TeacherMapper teacherMapper;


    @Override
    public Teacher selectTeacher(int teacher_id) {
        return teacherMapper.selectTeacher(teacher_id);
    }

    @Override
    public Teacher selectLoginTeacher(String teacher_num, String password) {
        return teacherMapper.selectLoginTeacher(teacher_num,password);
    }

    @Override
    public List<Teacher> selectAll(int startIndex, int pageSize) {
        return teacherMapper.selectAll(startIndex, pageSize);
    }

    @Override
    public int addTeacher(Teacher teacher) {
        return teacherMapper.addTeacher(teacher);
    }

    @Override
    public int updateTeacher(Teacher teacher) {
        return teacherMapper.updateTeacher(teacher);
    }

    @Override
    public int deleteTeacher(int teacher_id) {
        return teacherMapper.deleteTeacher(teacher_id);
    }
}
