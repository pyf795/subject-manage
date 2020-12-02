package com.subjectmanage.services;

import com.subjectmanage.beans.Topic;

import java.util.List;

public interface TopicService {
    public Topic getTopicWithGroupById(int topic_id);

    public List<Topic> searchTopics(String headline,String type,String grade,String teacher_name,int startIndex, int pageSize);

    public int getSearchTotal(String headline,String type,String grade,String teacher_name);

    public List<Topic> searchTeachTopics(String headline,String type,String grade,int teacher_id,int startIndex, int pageSize);

    public int getTeachSearchTotal(String headline,String type,String grade,int teacher_id);

    public List<Topic> selectAll(int startIndex, int pageSize);

    public List<Topic> selectTopicByTid(int teacher_id,int startIndex, int pageSize);

    public int getTotalByTid(int teacher_id);


    public int getTotal();

    public int addTopic(Topic topic);

    public int updateTopic(Topic topic);

    public int deleteTopic(int topic_id);
}
