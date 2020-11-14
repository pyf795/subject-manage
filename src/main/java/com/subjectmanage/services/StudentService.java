package com.subjectmanage.services;

import com.subjectmanage.beans.Student;

import java.util.List;

public interface StudentService {
    public List<Student> getStudentListByGroup(int group_id);
}
