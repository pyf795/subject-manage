package com.subjectmanage;

import com.subjectmanage.beans.Student;
import com.subjectmanage.mapper.GroupMapper;
import com.subjectmanage.mapper.TopicMapper;
import com.subjectmanage.services.StudentServiceImpl;
import com.subjectmanage.services.TopicService;
import com.subjectmanage.services.TopicServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest(classes= OurprjApplication.class)
 class topicTest {
   @Resource
    private StudentServiceImpl studentService;





    @Test
    void topicTest1(){
        Student student = new Student();
        student.setPassword("1234");
        student.setStudent_gender("男");
        student.setStudent_grade("2017级");
        student.setStudent_num("2017221303003");
        student.setStudent_phone("13922222222");
        student.setStudent_name("jobs");
        studentService.addStudent(student);

    }


}
