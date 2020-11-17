package com.subjectmanage.services;

import com.subjectmanage.beans.Student;

import java.util.List;

public interface StudentService {
    public List<Student> getStudentListByGroup(int group_id);

    public Student selectLoginStudent(String student_num,String password);

    public List<Student> selectAll(int startIndex,int pageSize);

    public int addStudent(Student student);

    public int updateStudent(Student student);

    public int deleteStudent(int student_id);
}
