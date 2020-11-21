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
