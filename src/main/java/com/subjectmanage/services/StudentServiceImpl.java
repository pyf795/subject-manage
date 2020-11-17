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

    @Override
    public List<Student> selectAll(int startIndex, int pageSize) {
        return studentMapper.selectAll(startIndex,pageSize);
    }

    @Override
    public int addStudent(Student student) {
        return studentMapper.addStudent(student);
    }

    @Override
    public int updateStudent(Student student) {
        return studentMapper.updateStudent(student);
    }

    @Override
    public int deleteStudent(int student_id) {
        return studentMapper.deleteStudent(student_id);
    }
}
