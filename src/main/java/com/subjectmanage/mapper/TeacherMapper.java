package com.subjectmanage.mapper;


import com.subjectmanage.beans.Teacher;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TeacherMapper {

    public Teacher selectLoginTeacher(String teacher_num, String password);

    public List<Teacher> selectAll(int startIndex, int pageSize);

    public int addTeacher(Teacher teacher);

    public int updateTeacher(Teacher teacher);

    public int deleteTeacher(int teacher_id);

}
