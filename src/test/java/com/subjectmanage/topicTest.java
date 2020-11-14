package com.subjectmanage;

import com.subjectmanage.mapper.GroupMapper;
import com.subjectmanage.mapper.TopicMapper;
import com.subjectmanage.services.TopicService;
import com.subjectmanage.services.TopicServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest(classes= OurprjApplication.class)
 class topicTest {
   /* @Resource
    private TopicService topicService;
*/




    @Test
    void topicTest1(){
        //System.out.println(topicService.getTopicWithGroupById(1));

    }
}
