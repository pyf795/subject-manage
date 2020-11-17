package com.subjectmanage.services;

import com.subjectmanage.beans.Topic;

import java.util.List;

public interface TopicService {
    public Topic getTopicWithGroupById(int topic_id);

    public List<Topic> selectAll(int startIndex, int pageSize);

    public int addTopic(Topic topic);

    public int updateTopic(Topic topic);

    public int deleteTopic(int topic_id);
}
