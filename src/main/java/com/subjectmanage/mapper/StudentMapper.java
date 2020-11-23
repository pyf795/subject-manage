package com.subjectmanage.mapper;

import com.subjectmanage.beans.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentMapper {

    public Student getStudentById(int student_id);

    public List<Student> getStudentListByGroup(int group_id);

    public Student selectLoginStudent(String student_num,String password);

    public List<Student> selectAll(int startIndex,int pageSize);

    public int addStudent(Student student);

    public int updateStudent(Student student);

    public int deleteStudent(int student_id);


}
