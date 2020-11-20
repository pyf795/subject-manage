package com.subjectmanage.services;

import com.subjectmanage.beans.Teacher;

import java.util.List;

public interface TeacherService {
    public Teacher selectLoginTeacher(String teacher_num, String password);

    public List<Teacher> selectAll(int startIndex, int pageSize);

    public int addTeacher(Teacher teacher);

    public int updateTeacher(Teacher teacher);

    public int deleteTeacher(int teacher_id);
}
