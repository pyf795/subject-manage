package com.subjectmanage.mapper;

import com.subjectmanage.beans.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentMapper {
    public List<Student> getStudentListByGroup(int group_id);
}
