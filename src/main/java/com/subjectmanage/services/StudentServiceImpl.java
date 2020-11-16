package com.subjectmanage.services;

import com.subjectmanage.beans.Student;
import com.subjectmanage.mapper.StudentMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Resource
    private StudentMapper studentMapper;
    @Override
    public List<Student> getStudentListByGroup(int group_id) {
        return studentMapper.getStudentListByGroup(group_id);
    }

    @Override
    public Student selectLoginStudent(String student_num, String password) {
        return studentMapper.selectLoginStudent(student_num,password);
    }
}
