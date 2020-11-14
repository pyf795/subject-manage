package com.subjectmanage.services;

import com.subjectmanage.beans.topic;
import com.subjectmanage.mapper.TopicMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TopicServiceImpl implements TopicService {
    @Resource
    private TopicMapper topicMapper;

    @Override
    public topic getTopicWithGroupById(int topic_id) {
        return topicMapper.getTopicWithGroupById(topic_id);
    }
}
