package com.subjectmanage.services;

import com.subjectmanage.beans.Topic;
import com.subjectmanage.mapper.TopicMapper;
import org.apache.ibatis.annotations.One;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TopicServiceImpl implements TopicService {
    @Resource
    private TopicMapper topicMapper;

    @Override
    public Topic getTopicWithGroupById(int topic_id) {
        return topicMapper.getTopicWithGroupById(topic_id);
    }

    @Override
    public List<Topic> selectAll(int startIndex, int pageSize) {
        return topicMapper.selectAll(startIndex, pageSize);
    }

    @Override
    public int getTotal(){
        return topicMapper.getTotal();
    }

    @Override
    public List<Topic> searchTopics(String headline,String type,String grade,String teacher_name,int startIndex, int pageSize) {
        return topicMapper.searchTopics(headline, type, grade,teacher_name, startIndex, pageSize);
    }

    @Override
    public int getSearchTotal(String headline, String type, String grade,String teacher_name) {
        return topicMapper.getSearchTotal(headline, type, grade,teacher_name);
    }

    @Override
    public int addTopic(Topic topic) {
        return topicMapper.addTopic(topic);
    }

    @Override
    public int updateTopic(Topic topic) {
        return topicMapper.updateTopic(topic);
    }

    @Override
    public int deleteTopic(int topic_id) {
        return topicMapper.deleteTopic(topic_id);
    }
}
