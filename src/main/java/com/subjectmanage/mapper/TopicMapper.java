package com.subjectmanage.mapper;

import com.subjectmanage.beans.topic;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TopicMapper {
    public topic getTopicWithGroupById(int topic_id);
}
